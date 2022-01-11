package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@RequiredArgsConstructor
@Data
public class Author {
    private long id;
    private String name;
}
