package com.github.memsup.sentence;

import com.github.memsup.sentence.filter.DefaultFilter;
import com.github.memsup.sentence.filter.Filter;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        final Filter filter = new DefaultFilter();
        final Reader reader = new DefaultReader();
        final Parser parser = new DefaultParser();
        final Sentence sentence = new DefaultSentence();

        final Set<String> portableFiles = filter.setsOfPortableDocumentFiles();

        portableFiles.forEach(e -> {
            final String source = reader.getSource(e);
            final Set<String> sentences = parser.parse(source);
            sentence.save(sentences);
        });

    }
}
