package com.gmail.michzuerch.TeachersAssistant.presentation.ui.util.field;

import com.gmail.michzuerch.TeachersAssistant.presentation.ui.util.validator.HtmlValidator;
import com.vaadin.data.Validator;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.*;
import org.apache.commons.io.IOUtils;
import server.droporchoose.UploadComponent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlField extends CustomField<byte[]> {
    private byte[] fieldValue;
    private String filename = new String();
    private StreamResource streamResource = new StreamResource(new HtmlSource(), "report.html");
    private UploadComponent upload = new UploadComponent();
    private TextArea textArea = new TextArea("HTML");
    private Button btnDownloadHtmlSource = new Button("Download");

    @Override
    protected Component initContent() {
        upload.setReceivedCallback(this::upload);
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.setMargin(false);

        textArea.setWidth(600, Unit.PIXELS);
        textArea.setRows(12);
        textArea.setWordWrap(false);

        FileDownloader fileDownloader = new FileDownloader(streamResource);
        fileDownloader.extend(btnDownloadHtmlSource);

        layout.addComponent(new HorizontalLayout(upload, btnDownloadHtmlSource));
        layout.addComponent(textArea);
        textArea.addValueChangeListener(new ValueChangeListener<String>() {
            @Override
            public void valueChange(ValueChangeEvent<String> event) {
                doSetValue(textArea.getValue().getBytes());
            }
        });
        return layout;
    }

    @Override
    public byte[] getEmptyValue() {
        byte[] emptyValue = new byte[0];
        try {
            emptyValue = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("/EmptyFieldValues/Report.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emptyValue;
    }

    @Override
    public Validator<byte[]> getDefaultValidator() {
        return new HtmlValidator();
    }

    private void upload(String filename, Path path) {
        try {
            byte[] uploaded = Files.readAllBytes(path);
            setFilename(filename);
            streamResource.setFilename(getFilename());
            System.err.println("Uploaded Bytes: " + uploaded.length);
            doSetValue(uploaded);
            btnDownloadHtmlSource.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doSetValue(byte[] value) {
        streamResource = new StreamResource(new HtmlSource(), getFilename());
        byte[] oldValue = getValue();
        this.fieldValue = value;
        textArea.setValue(new String(value));
        if (fieldValue.length == 0) {
            btnDownloadHtmlSource.setEnabled(false);
        } else {
            btnDownloadHtmlSource.setEnabled(true);
        }
        fireEvent(new ValueChangeEvent<byte[]>(this, oldValue, true));
    }

    @Override
    public byte[] getValue() {
        return fieldValue;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public class HtmlSource implements StreamResource.StreamSource {
        @Override
        public InputStream getStream() {
            return new ByteArrayInputStream(fieldValue);
        }
    }
}