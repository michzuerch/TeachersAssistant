package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.field;

import com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.validator.JavascriptValidator;
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

public class JavascriptField extends CustomField<byte[]> {
    private byte[] fieldValue;
    private String filename = new String();
    private StreamResource streamResource = new StreamResource(new JavascriptSource(), "Javascript.js");
    private UploadComponent upload = new UploadComponent();
    private TextArea textArea = new TextArea("Javascript");
    private Button btnDownloadJavascriptSource = new Button("Download");

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
        fileDownloader.extend(btnDownloadJavascriptSource);

        layout.addComponent(new HorizontalLayout(upload, btnDownloadJavascriptSource));
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
            emptyValue = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("/EmptyFieldValues/Report.js"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emptyValue;
    }

    @Override
    public Validator<byte[]> getDefaultValidator() {
        return new JavascriptValidator();
    }

    private void upload(String filename, Path path) {
        try {
            byte[] uploaded = Files.readAllBytes(path);
            setFilename(filename);
            streamResource.setFilename(getFilename());
            System.err.println("Uploaded Bytes: " + uploaded.length);
            doSetValue(uploaded);
            btnDownloadJavascriptSource.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doSetValue(byte[] value) {
        streamResource = new StreamResource(new JavascriptSource(), getFilename());
        byte[] oldValue = getValue();
        this.fieldValue = value;
        textArea.setValue(new String(value));
        if (fieldValue.length == 0) {
            btnDownloadJavascriptSource.setEnabled(false);
        } else {
            btnDownloadJavascriptSource.setEnabled(true);
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

    public class JavascriptSource implements StreamResource.StreamSource {
        @Override
        public InputStream getStream() {
            return new ByteArrayInputStream(fieldValue);
        }
    }
}
