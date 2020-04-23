package com.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class LoadingExistingDocument {
    public static void main(String[] args) {
//        File file = new File("/Users/qinwenjing/Documents/Projects/Test/src/main/java/com/pdfbox/Document_Creation"
//                + ".java");
      // File file = new File("/Users/qinwenjing/Documents/标签平台疑问.docx");
        File file = new File("/Users/qinwenjing/Documents/en_test.pdf");
        try {
            PDDocument pdDocument = PDDocument.load(file);
            System.out.println("PDF loaded");
            pdDocument.addPage(new PDPage());
            pdDocument.save("./en_test.pdf");
            pdDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
