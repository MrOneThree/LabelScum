package ru.mronethree.labelscum.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mronethree.labelscum.domain.Curr;
import ru.mronethree.labelscum.domain.DistributorReport;
import ru.mronethree.labelscum.domain.MusicDistributor;
import ru.mronethree.labelscum.service.DistributorReportService;
import ru.mronethree.labelscum.service.MusicDistributorService;
import ru.mronethree.labelscum.utils.CsvParsingUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * @author Kirill Popov
 */
@Route(value = "distreport", layout = MainLayout.class)
@PageTitle("Отчеты дистрибьюторов | Label Scum")
public class DistributorReportsView extends VerticalLayout {
    @Autowired
    private final MusicDistributorService distributorService;
    @Autowired
    private final DistributorReportService reportService;
    @Autowired
    private final CsvParsingUtils csvParsingUtils;

    private InputStream filestream;

    public DistributorReportsView(MusicDistributorService distributorService, DistributorReportService reportService, CsvParsingUtils csvParsingUtils) {
        this.distributorService = distributorService;
        this.reportService = reportService;
        this.csvParsingUtils = csvParsingUtils;
        this.update();
    }

    private void update() {
        this.removeAll();
        this.addButtonsBar();
        this.addGridLayout();
    }

    private void addButtonsBar() {
        this.add(
                this.getButtonsBar()
        );
    }

    private Component getButtonsBar() {
        HorizontalLayout bar = new HorizontalLayout();
        bar.add(
                this.getUploadButton()
        );

        return bar;
    }

    private void addGridLayout() {
        this.add(
                this.getDRGrid()
        );
    }

    private Component getDRGrid() {
        Grid<DistributorReport> grid = new Grid<>(DistributorReport.class, false);
        grid.addColumn(DistributorReport::getId).setHeader("ID");
        grid.addColumn(DistributorReport::getDate).setHeader("Дата отчета");
        grid.addColumn(r -> r.getArtist().getAlias()).setHeader("Артист");
        grid.addColumn(DistributorReport::getAmount).setHeader("Сумма");
        grid.addColumn(DistributorReport::getCurrency).setHeader("Валюта");

        grid.setItems(reportService.fetchAll());
        return grid;
    }

    private Component getUploadButton() {
        Button uploadButton = new Button(VaadinIcon.UPLOAD.create());
        uploadButton.addClickListener(e -> this.podUploadDialog());
        return uploadButton;
    }

    private void podUploadDialog() {
        Dialog dialog = new Dialog();
        HorizontalLayout paramFields = new HorizontalLayout();

        Select<MusicDistributor> distributorSelect = this.getMusicDistributorSelect();
        DatePicker datePicker = this.getDatePicker();
        Select<Curr> currSelect = this.getCurrencySelect();
        paramFields.add(
                distributorSelect,
                datePicker,
                currSelect
        );
        Button confirmButton = getConfirmButton(distributorSelect, currSelect, datePicker, dialog);

        Upload upload = getUpload(confirmButton);

        dialog.add(
                paramFields,
                upload,
                confirmButton
        );
        dialog.open();
    }

    private Select<Curr> getCurrencySelect() {
        Select<Curr> select = new Select<>();
        select.setEmptySelectionAllowed(false);
        select.setLabel("Валюта");
        select.setItems(Curr.values());
        return select;
    }

    private Button getConfirmButton(Select<MusicDistributor> distributorSelect, Select<Curr> currSelect, DatePicker datePicker, Dialog dialog) {
        Button confirmButton = new Button("Загрузить");
        confirmButton.setEnabled(false);
        confirmButton.addClickListener(e -> {
            if (!distributorSelect.isEmpty() && !currSelect.isEmpty() && !datePicker.isEmpty() && Objects.nonNull(filestream)){
                List<DistributorReport> reports =
                        csvParsingUtils.parseDistributorReport(filestream, distributorSelect.getValue(), datePicker.getValue(), currSelect.getValue());
                reports.forEach(reportService::save);
                dialog.close();
                this.update();
            }
        });
        return confirmButton;
    }

    private Upload getUpload(Button confirmButton) {
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload upload = new Upload();
        upload.setMaxFiles(1);
        upload.setAutoUpload(true);
        upload.setReceiver(memoryBuffer);
        upload.setAcceptedFileTypes(".csv");
        upload.addFailedListener(e -> new Notification(e.getReason().getMessage()));
        upload.addSucceededListener(event -> {
            InputStream stream = memoryBuffer.getInputStream();
            this.setFilestream(stream);
            confirmButton.setEnabled(true);
        });
        return upload;
    }

    private void setFilestream(InputStream stream) {
        this.filestream = stream;
    }


    private DatePicker getDatePicker() {
        DatePicker datePicker = new DatePicker("Дата отчета");
        DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
        singleFormatI18n.setDateFormat("yyyy-MM-dd");
        datePicker.setRequired(true);
        datePicker.setI18n(singleFormatI18n);
        return datePicker;
    }

    private Select<MusicDistributor> getMusicDistributorSelect() {
        Select<MusicDistributor> select = new Select<>();
        select.setEmptySelectionAllowed(false);
        select.setLabel("Дистрибьютор");
        select.setItemLabelGenerator(MusicDistributor::getName);
        select.setItems(distributorService.fetchAll());
        return select;
    }
}
