package com.pdfbox;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

// 如何向PDF文档添加“ Author, Title, Date, and Subject等属性。
public class AddingDocumentAttributes {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        document.addPage(new PDPage());
        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setAuthor("qinwenjing");
        pdd.setTitle("sample document");
        pdd.setCreator("pdf examples");
        pdd.setSubject("example document");
        Calendar date = new GregorianCalendar();
        date.set(2020, 4, 8);
        pdd.setCreationDate(date);
        pdd.setKeywords("sample, first example, my pdf");
        try {
            document.save("./doc_attributes.pdf");
            System.out.println("Properties added successfully ");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
