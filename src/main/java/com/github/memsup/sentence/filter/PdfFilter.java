package com.github.memsup.sentence.filter;

import java.io.File;
import java.io.FilenameFilter;

public class PdfFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".pdf");
    }
}
