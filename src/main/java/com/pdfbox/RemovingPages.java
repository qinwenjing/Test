package com.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public class RemovingPages {
    public static void main(String[] args) {
        File file = new File("./sample.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            System.out.println(document.getNumberOfPages());
            document.removePage(5);
            document.save("./sample.pdf");
            System.out.println(document.getNumberOfPages());
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
