package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.field;

import com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.UploadReceiver;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JasperXmlField extends CustomField<byte[]> implements Upload.SucceededListener {
    UploadReceiver uploadReceiver = new UploadReceiver();
    private byte[] fieldValue;
    private String filename = new String();
    private StreamResource streamResource = new StreamResource(new JasperSource(), "Jasper.jrxml");
    private Upload upload = new Upload("Jasperreport", uploadReceiver);
    //private UploadComponent upload = new UploadComponent();
    private TextArea textArea = new TextArea("JXRML");
    private Button btnDownloadJasperSource = new Button("Download");

    @Override
    protected Component initContent() {
        upload.setImmediateMode(true);
        upload.addSucceededListener(this);

        //upload.setReceivedCallback(this::upload);
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.setMargin(false);

        textArea.setWidth(600, Unit.PIXELS);
        textArea.setRows(12);
        textArea.setWordWrap(false);

        FileDownloader fileDownloader = new FileDownloader(streamResource);
        fileDownloader.extend(btnDownloadJasperSource);

        layout.addComponent(new HorizontalLayout(upload, btnDownloadJasperSource));
        layout.addComponent(textArea);
        textArea.addValueChangeListener(
                event -> fireEvent(new ValueChangeEvent<byte[]>(this,
                        event.getOldValue().getBytes(), event.isUserOriginated())));
        return layout;
    }

    @Override
    public byte[] getEmptyValue() {
        btnDownloadJasperSource.setEnabled(false);
        return new byte[0];
    }

    private void upload(String s, Path path) {
        try {
            byte[] uploaded = Files.readAllBytes(Paths.get(path.toUri()));
            setFilename(s);
            streamResource.setFilename(getFilename());
            System.err.println("Uploaded Bytes: " + uploaded);
            doSetValue(uploaded);
            btnDownloadJasperSource.setEnabled(true);
            fireEvent(new ValueChangeEvent<byte[]>(this, getValue(), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doSetValue(byte[] value) {
        streamResource = new StreamResource(new JasperSource(), getFilename());
        this.fieldValue = value;
        textArea.setValue(new String(value));
    }


    @Override
    public byte[] getValue() {
        return fieldValue;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        System.err.println("Succeeded:" + uploadReceiver.getBytes().length);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public class JasperSource implements StreamResource.StreamSource {
        @Override
        public InputStream getStream() {
            return new ByteArrayInputStream(fieldValue);
        }
    }
}
