package ru.mronethree.labelscum.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.mronethree.labelscum.domain.Artist;
import ru.mronethree.labelscum.domain.DistributorReport;
import ru.mronethree.labelscum.domain.ReleaseExpense;
import ru.mronethree.labelscum.service.ArtistService;
import ru.mronethree.labelscum.service.CbrCurrencyToDateService;
import ru.mronethree.labelscum.service.DistributorReportService;
import ru.mronethree.labelscum.service.ReleaseService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

/**
 * @author Kirill Popov
 */
@Route(value = "", layout = MainLayout.class)
@PageTitle("Артисты | Label Scum")
public class ArtistsView extends VerticalLayout {
    @Autowired
    private final ArtistService artistService;
    @Autowired
    private final ReleaseService releaseService;
    @Autowired
    private final DistributorReportService distributorReportService;
    @Autowired
    private final CbrCurrencyToDateService currencyService;
    public ArtistsView(ArtistService artistService, ReleaseService releaseService, DistributorReportService distributorReportService, CbrCurrencyToDateService currencyService) {
        this.artistService = artistService;
        this.releaseService = releaseService;
        this.distributorReportService = distributorReportService;
        this.currencyService = currencyService;
        this.addGridLayout();
    }

    private void addGridLayout() {
        this.add(
                this.getArtistsGrid()
        );
    }

    private Component getArtistsGrid() {
        Grid<Artist> artistGrid = new Grid<>(Artist.class, false);
        artistGrid.addColumn(Artist::getAlias).setHeader("Артист");
        artistGrid.addColumn(Artist::getFirstName).setHeader("Имя");
        artistGrid.addColumn(Artist::getLastName).setHeader("Фамилия");

        artistGrid.addItemDoubleClickListener(e -> this.openArtistDialog(e.getItem()));

        artistGrid.setItems(artistService.fetchAll());

        return artistGrid;
    }

    private void openArtistDialog(Artist artist) {
        VerticalLayout dialogLayout = new VerticalLayout();
        Dialog artistDialog = new Dialog(dialogLayout);
        HorizontalLayout generalInfo = this.getGeneralInfoLayout(artist);
        VerticalLayout balanceLayout = this.getBalanceLayout(artist);
        dialogLayout.add(
                generalInfo,
                balanceLayout
        );
        artistDialog.open();
    }

    private HorizontalLayout getGeneralInfoLayout(Artist artist) {
        HorizontalLayout generalInfo = new HorizontalLayout();
        TextField artistAlias = new TextField("Алиас");
        artistAlias.setValue(artist.getAlias());
        artistAlias.setReadOnly(true);
        TextField artistFirstName = new TextField("Имя");
        artistFirstName.setValue(Optional.ofNullable(artist.getFirstName()).orElse(""));
        artistFirstName.setReadOnly(true);
        TextField artistLastName = new TextField("Фамилия");
        artistLastName.setValue(Optional.ofNullable(artist.getLastName()).orElse(""));
        artistLastName.setReadOnly(true);
        generalInfo.add(
                artistAlias,
                artistFirstName,
                artistLastName
        );
        return generalInfo;
    }

    private VerticalLayout getBalanceLayout(Artist artist) {
        VerticalLayout layout = new VerticalLayout();

        HorizontalLayout headerAndYearPick = new HorizontalLayout();
        headerAndYearPick.setWidthFull();
        headerAndYearPick.setJustifyContentMode(JustifyContentMode.BETWEEN);
        headerAndYearPick.setAlignItems(Alignment.BASELINE);
        Set<Integer> artistActiveYears = new HashSet<>();
        artistActiveYears.addAll(releaseService.fetchByArtistWithExpenses(artist).stream().map(r -> r.getReleaseDate().getYear()).toList());
        artistActiveYears.addAll(distributorReportService.fetchByArtist(artist).stream().map(dr -> dr.getDate().getYear()).toList());

        Select<Integer> yearPick = new Select<>();
        yearPick.setLabel("Отчетный год");
        yearPick.setItems(artistActiveYears);

        headerAndYearPick.add(
                new H3("Баланс артиста"),
                yearPick
        );
        VerticalLayout balanceLayout = new VerticalLayout();
        if (!yearPick.isEmpty()){
            balanceLayout.removeAll();
            balanceLayout.add(getBalanceGrid(artist, yearPick.getValue()));
        }

        yearPick.addValueChangeListener(e -> {
            balanceLayout.removeAll();
            balanceLayout.add(getBalanceGrid(artist, e.getValue()));
        });

        layout.add(
                headerAndYearPick,
                balanceLayout
        );
        return layout;
    }
    private Grid<ArtistMonthlyReport> getBalanceGrid(Artist artist, Integer year){
        List<ArtistMonthlyReport> yearReport = this.getYearReport(artist, year);
        BigDecimal totalYearBalance = yearReport.stream()
                .map(m -> m.totalRevenueRUB.subtract(m.totalExpRUB))
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        Grid<ArtistMonthlyReport> balanceGrid = new Grid<>(ArtistMonthlyReport.class, false);
        balanceGrid.addColumn(r -> r.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)).setHeader("Месяц");
        balanceGrid.addColumn(ArtistMonthlyReport::totalRevenueGBP).setHeader("Пост. GBP");
        balanceGrid.addColumn(ArtistMonthlyReport::totalRevenueRUB).setHeader("Пост. RUB");
        balanceGrid.addColumn(ArtistMonthlyReport::totalExpRUB).setHeader("Расх. RUB");
        balanceGrid.addColumn(ArtistMonthlyReport::expenseSource)
                .setHeader("Источник расх.")
                .setFooter("Итого:");
        balanceGrid.addColumn(r -> r.totalRevenueRUB.subtract(r.totalExpRUB))
                .setHeader("Баланс")
                .setFooter(totalYearBalance.toString());

        balanceGrid.setItems(yearReport);
        return balanceGrid;
    }

    private List<ArtistMonthlyReport> getYearReport(Artist artist, Integer year) {
        List<DistributorReport> revenuesReport = distributorReportService.fetchByArtistAndYear(artist, year);
//        TODO: need more optimal solution
        revenuesReport.forEach(rr -> rr.setExchangeRateToRUBToDate(currencyService.fetchByDate(rr.getDate()).stream().filter(ex -> ex.getCurrency() == rr.getCurrency()).findFirst().get().getRateToRub()));
        Set<ReleaseExpense> expensesReport = new HashSet<>();
        releaseService.fetchByArtistWithExpenses(artist).stream()
                .filter(r -> r.getReleaseDate().getYear() == year)
                .forEach(r -> expensesReport.addAll(r.getReleaseExpenses()));
        List<ArtistMonthlyReport> finalReport = new ArrayList<>();
        Arrays.stream(Month.values()).forEach(m -> finalReport.add(
                new ArtistMonthlyReport(
                        m,
                        revenuesReport.stream()
                                .filter(rr -> rr.getDate().getMonth() == m)
                                .map(DistributorReport::getAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .setScale(2, RoundingMode.HALF_EVEN),
                        revenuesReport.stream()
                                .filter(rr -> rr.getDate().getMonth() == m)
                                .map(rr -> rr.getAmount().multiply(rr.getExchangeRateToRUBToDate()))
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .setScale(2, RoundingMode.HALF_EVEN),
                        expensesReport.stream()
                                .filter(er -> er.getRelease().getReleaseDate().getMonth() == m)
                                .map(ReleaseExpense::getAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .setScale(2, RoundingMode.HALF_EVEN),
                        StringUtils.collectionToDelimitedString(expensesReport.stream()
                                .filter(er -> er.getRelease().getReleaseDate().getMonth() == m)
                                .map(er -> er.getRelease().getCatalog())
                                .toList(), ",")

                )
        ));
        return finalReport;
    }

    private record ArtistMonthlyReport(Month month, BigDecimal totalRevenueGBP, BigDecimal totalRevenueRUB, BigDecimal totalExpRUB, String expenseSource){}
}
