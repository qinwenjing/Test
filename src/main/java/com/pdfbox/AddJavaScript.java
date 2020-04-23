package com.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;

public class AddJavaScript {
    public static void main(String[] args) {
        File file = new File("./scripttest.pdf");
        try {
            PDDocument document = PDDocument.load(file);
            String javaScript = "app.alert( {cMsg: 'this is an example', nIcon: 3,"
                    + " nType: 0, cTitle: 'PDFBox Javascript example’} );";
            PDActionJavaScript pdActionJavaScript = new PDActionJavaScript(javaScript);
            document.getDocumentCatalog().setOpenAction(pdActionJavaScript);
            document.save(file);
            System.out.println("Data added to the given PDF");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
