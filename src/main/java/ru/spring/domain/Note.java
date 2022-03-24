package ru.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    /**
     * Currency of notes
     */
    private String currency;

    /**
     * Value of note
     */
    private int value;

    /**
     * Count of notes
     */
    private int number;

    @Override
    public String toString() {
        return currency + " " + value + " " + number;
    }
}
