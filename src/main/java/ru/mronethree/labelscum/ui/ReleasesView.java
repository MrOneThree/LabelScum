package ru.mronethree.labelscum.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mronethree.labelscum.domain.Release;
import ru.mronethree.labelscum.service.ReleaseService;

/**
 * @author Kirill Popov
 */
@Route(value = "releases", layout = MainLayout.class)
@PageTitle("Релизы | Label Scum")
public class ReleasesView extends VerticalLayout {
    @Autowired
    private final ReleaseService releaseService;
    public ReleasesView(ReleaseService releaseService) {
        this.releaseService = releaseService;
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

    private Component getReleasesGrid() {
        Grid<Release> releaseGrid = new Grid<>(Release.class, false);
//        releaseGrid.addColumn(Release::getId).setHeader("ID");
        releaseGrid.addColumn(Release::getCatalog).setHeader("Каталог");
        releaseGrid.addColumn(Release::getReleaseName).setHeader("Название");
        releaseGrid.addColumn(Release::getReleaseDate).setHeader("Дата релиза");
        releaseGrid.addColumn(ReleasesView::getArtistsString).setHeader("Артисты");


        releaseGrid.setItems(releaseService.fetchAll());

        return releaseGrid;
    }
}
