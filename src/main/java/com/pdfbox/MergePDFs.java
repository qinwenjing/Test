package com.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

public class MergePDFs {
    public static void main(String[] args) {
        File file1 = new File("./sample1.pdf");
        File file2 = new File("./sample2.pdf");
        File file3 = new File("./sample3.pdf");
        try {
            PDDocument doc1 = PDDocument.load(file1);
            PDDocument doc2 = PDDocument.load(file2);
            PDDocument doc3 = PDDocument.load(file3);
            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
            pdfMergerUtility.setDestinationFileName("./mergesample.pdf");
            pdfMergerUtility.addSource(file1);
            pdfMergerUtility.addSource(file2);
           // pdfMergerUtility.addSource(file3);
            pdfMergerUtility.mergeDocuments();
            System.out.println("Documents merged");

            doc1.close();
            doc2.close();
            doc3.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
