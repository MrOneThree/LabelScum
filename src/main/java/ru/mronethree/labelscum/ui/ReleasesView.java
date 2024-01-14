package ru.mronethree.labelscum.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mronethree.labelscum.domain.Release;
import ru.mronethree.labelscum.domain.ReleaseExpense;
import ru.mronethree.labelscum.service.ReleaseExpenseService;
import ru.mronethree.labelscum.service.ReleaseService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kirill Popov
 */
@Route(value = "releases", layout = MainLayout.class)
@PageTitle("Релизы | Label Scum")
public class ReleasesView extends VerticalLayout {
    @Autowired
    private final ReleaseService releaseService;
    @Autowired
    private final ReleaseExpenseService expenseService;
    public ReleasesView(ReleaseService releaseService, ReleaseExpenseService expenseService) {
        this.releaseService = releaseService;
        this.expenseService = expenseService;
        this.addGridLayout();
    }

    private static String getArtistsString(Release r) {
        return StringUtils.join(r.getReleaseArtists().stream()
                .map(a -> a.getArtist().getAlias())
                .toList(), ',');
    }

    private void addGridLayout() {
        this.add(
                this.getReleasesGrid()
        );
    }

    private static HorizontalLayout getHorizontalLayout(Release release) {
        HorizontalLayout generalInfo = new HorizontalLayout();
        TextField releaseLabel = new TextField("Лейбл");
        releaseLabel.setValue(release.getLabel().getName());
        releaseLabel.setReadOnly(true);
        TextField releaseCatalog = new TextField("Каталог");
        releaseCatalog.setValue(release.getCatalog());
        releaseCatalog.setReadOnly(true);
        TextField releaseName = new TextField("Название релиза");
        releaseName.setValue(release.getReleaseName());
        releaseName.setReadOnly(true);
        TextField releaseDate = new TextField("Дата релиза");
        releaseDate.setValue(release.getReleaseDate().toString());
        releaseDate.setReadOnly(true);
        generalInfo.add(
                releaseLabel,
                releaseCatalog,
                releaseName,
                releaseDate
        );
        return generalInfo;
    }

    private Component getReleasesGrid() {
        Grid<Release> releaseGrid = new Grid<>(Release.class, false);
        releaseGrid.addColumn(Release::getCatalog).setHeader("Каталог");
        releaseGrid.addColumn(Release::getReleaseName).setHeader("Название");
        releaseGrid.addColumn(Release::getReleaseDate).setHeader("Дата релиза");
        releaseGrid.addColumn(ReleasesView::getArtistsString).setHeader("Артисты");

        releaseGrid.addItemDoubleClickListener(e -> this.openReleaseDialog(e.getItem()));


        releaseGrid.setItems(releaseService.fetchAll());

        return releaseGrid;
    }

    private void openReleaseDialog(Release release) {
        VerticalLayout dialogLayout = new VerticalLayout();
        Dialog releaseDialog = new Dialog(dialogLayout);
        HorizontalLayout generalInfo = getHorizontalLayout(release);
        Grid<ReleaseExpense> expenseGrid = getReleaseExpenseGrid(release);
        dialogLayout.add(
                generalInfo,
                new H2("Расходы релиза"),
                expenseGrid
        );
        releaseDialog.open();
    }

    private Grid<ReleaseExpense> getReleaseExpenseGrid(Release release) {
        //TODO: Account for currency. Only RUBs now
        List<ReleaseExpense> releaseExpenses = expenseService.fetchByRelease(release);
        BigDecimal totalReleaseExpenses = releaseExpenses.stream()
                .map(e -> e.getAmount().multiply(BigDecimal.valueOf(e.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Grid<ReleaseExpense> expenseGrid = new Grid<>(ReleaseExpense.class, false);
        expenseGrid.addColumn(ReleaseExpense::getExpenseType).setHeader("Тип расходов").setAutoWidth(true);
        expenseGrid.addColumn(ReleaseExpense::getAmount).setHeader("Сумма");
        expenseGrid.addColumn(ReleaseExpense::getCurrency).setHeader("Валюта");
        expenseGrid.addColumn(ReleaseExpense::getQuantity).setHeader("Количество").setFooter("Итого:");
        expenseGrid.addColumn(e -> e.getAmount().multiply(BigDecimal.valueOf(e.getQuantity())))
                                                        .setHeader("Итого")
                                                        .setPartNameGenerator(e -> "font-weight-bold")
                                                        .setFooter(totalReleaseExpenses.toString())
                                                        .setFooterPartName("font-weight-bold");
        expenseGrid.setItems(releaseExpenses);
        return expenseGrid;
    }
}
