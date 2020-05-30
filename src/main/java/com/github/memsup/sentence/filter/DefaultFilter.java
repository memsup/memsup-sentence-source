package com.github.memsup.sentence.filter;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public final class DefaultFilter implements Filter {

    private PdfFilter pdfFilter;

    public DefaultFilter() {
        pdfFilter = new PdfFilter();
    }

    @Override
    public final Set<String> setsOfPortableDocumentFiles() {

        final Set<String> fileNameSet = new HashSet<>(15);

        final File dir = new File(".");
        final File[] files = dir.listFiles(pdfFilter);

        for (File file : files) fileNameSet.add(file.getName());

        return fileNameSet;
    }
}
