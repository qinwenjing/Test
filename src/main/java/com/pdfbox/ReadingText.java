package com.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReadingText {
    public static void main(String[] args) {
        File file = new File("./doc_attributes.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String text = pdfTextStripper.getText(document);
            System.out.println(text);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
