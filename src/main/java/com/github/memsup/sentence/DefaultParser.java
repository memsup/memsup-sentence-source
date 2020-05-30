package com.github.memsup.sentence;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DefaultParser implements Parser {

    @Override
    public final Set<String> parse(final String source) {

        final Pattern pattern = Pattern.compile("\\s+[^.!?]*[.!?]");
        final Matcher matcher = pattern.matcher(source);
        final Set<String> stringSet = new HashSet<>(100);

        while (matcher.find()) stringSet.add(matcher.group());

        return stringSet;
    }
}
