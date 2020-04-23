package com.pdfbox;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

public class SplitPages {
    public static void main(String[] args) {
        File file = new File("./sample.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            Splitter spitter = new Splitter();
            List<PDDocument> pages = spitter.split(document);
            ListIterator<PDDocument> iterator = pages.listIterator();
            int i = 1;
            while (iterator.hasNext()) {
                PDDocument pd = iterator.next();
                pd.save("./sample" + i++ + ".pdf");
                pd.close();
            }
            System.out.println("Multiple PDF’s created");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
