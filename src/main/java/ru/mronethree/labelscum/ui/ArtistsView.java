package ru.mronethree.labelscum.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mronethree.labelscum.domain.Artist;
import ru.mronethree.labelscum.service.ArtistService;

/**
 * @author Kirill Popov
 */
@Route(value = "", layout = MainLayout.class)
@PageTitle("Артисты | Label Scum")
public class ArtistsView extends VerticalLayout {
    @Autowired
    private final ArtistService artistService;
    public ArtistsView(ArtistService artistService) {
        this.artistService = artistService;
        this.addGridLayout();
    }

    private void addGridLayout() {
        this.add(
                this.getArtistsGrid()
        );
    }

    private Component getArtistsGrid() {
        Grid<Artist> artistGrid = new Grid<>(Artist.class, false);
//        artistGrid.addColumn(Artist::getId).setHeader("ID");
        artistGrid.addColumn(Artist::getAlias).setHeader("Артист");
        artistGrid.addColumn(Artist::getFirstName).setHeader("Имя");
        artistGrid.addColumn(Artist::getLastName).setHeader("Фамилия");

        artistGrid.setItems(artistService.fetchAll());

        return artistGrid;
    }
}
