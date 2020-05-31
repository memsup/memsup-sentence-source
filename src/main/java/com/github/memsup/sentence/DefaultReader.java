package com.github.memsup.sentence;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;

public final class DefaultReader implements Reader {

    @Override
    public String getSource(final String fileName) {

        final StringBuilder builder = new StringBuilder();

        System.out.println("fileName = " + fileName);

        try {
            final PdfReader reader = new PdfReader(fileName);

            for (int i = 1; i < reader.getNumberOfPages(); i++)
                builder.append(PdfTextExtractor.getTextFromPage(reader, i));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
