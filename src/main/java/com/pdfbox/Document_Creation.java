package com.pdfbox;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Document_Creation {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        try {
            System.out.println("PDF created");
            PDPage blankPage = new PDPage();
            document.addPage(blankPage);
            document.save("./writetest.pdf");
            document.close();
            // 这时候直接打开创建的文件会发现打不开
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
