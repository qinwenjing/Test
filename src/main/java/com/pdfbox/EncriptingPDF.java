package com.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class EncriptingPDF {
    public static void main(String[] args) {
        File file = new File("./new.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            AccessPermission ap = new AccessPermission();
            StandardProtectionPolicy spp = new StandardProtectionPolicy("1234", "1234", ap);
            spp.setEncryptionKeyLength(128);
            spp.setPermissions(ap);
            document.protect(spp);
            System.out.println("Document encrypted");
            document.save(file);
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
