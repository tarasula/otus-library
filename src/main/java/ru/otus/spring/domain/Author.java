package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Accessors(chain = true)
@RequiredArgsConstructor
@Data
@Entity
public class Author {
    @Id
    private long id;
    private String name;
}
