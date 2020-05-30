package com.github.memsup.sentence;

import java.util.Set;

public interface Parser {
    Set<String> parse(String source);
}
