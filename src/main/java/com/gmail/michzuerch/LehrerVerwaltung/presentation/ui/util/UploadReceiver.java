package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util;

import com.vaadin.ui.Upload;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class UploadReceiver implements Upload.Receiver {
    private ByteArrayOutputStream outputStream;

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        return new ByteArrayOutputStream() {

        };
    }

    public byte[] getBytes() {
        return outputStream.toByteArray();
    }
}

