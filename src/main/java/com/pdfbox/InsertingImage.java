package com.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class InsertingImage {
    public static void main(String[] args) {
        File file = new File("./new.pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDImageXObject pdImage = PDImageXObject.createFromFile("./00.png", doc);

            PDPage page = doc.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.drawImage(pdImage, 70, 250);
            contentStream.close();
            doc.save("./new.pdf");
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
