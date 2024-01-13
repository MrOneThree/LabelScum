package ru.mronethree.labelscum.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * @author Kirill Popov
 */
@PageTitle("Label Scum")
public class MainLayout extends AppLayout {
    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Label Scum");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);
        addToNavbar(toggle, title);
    }

    private SideNav getSideNav() {
        SideNav sideNav = new SideNav();
        sideNav.addItem(
                new SideNavItem("Артисты", ArtistsView.class, VaadinIcon.USER.create()),
                new SideNavItem("Релизы", ReleasesView.class, VaadinIcon.RECORDS.create()),
                new SideNavItem("Отчеты дистрибьюторов", DistributorReportsView.class, VaadinIcon.ABACUS.create()),
                new SideNavItem("Управление", "/settings", VaadinIcon.AUTOMATION.create())
        );
        return sideNav;
    }
}
