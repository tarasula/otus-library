package ru.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.spring.domain.Note;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ATM service providing multiple transactions
 *
 * @author Andrey Orlov
 */
@Service
@RequiredArgsConstructor
public class CashServiceImpl implements CashService {

    private Map<String, Note> noteList = new HashMap<>();
    private final List<Integer> notes = Arrays.asList(10, 20, 50, 100, 200);

    /**
     * ATM transaction error response
     */
    public static final String ATM_TRANSACTION_ERROR_RESPONSE = "ERROR";

    /**
     * ATM transaction success response
     */
    public static final String ATM_TRANSACTION_OK_RESPONSE = "OK";

    @Override
    public String addNotes(String currency, int value, int number) {
        if (StringUtils.isAllUpperCase(currency)
                && number > 0
                && notes.contains(value)) {
            noteList.put(currency, getNote(currency, value, number));
            return ATM_TRANSACTION_OK_RESPONSE;
        }
        return ATM_TRANSACTION_ERROR_RESPONSE;
    }

    @Override
    public String getCash(String currency, int amount) {
        Note note = noteList.get(currency);
        int currentAmount = note.getValue() * note.getNumber();
        if (currentAmount < amount) {
            return ATM_TRANSACTION_ERROR_RESPONSE;
        }
        note.setNumber((currentAmount - amount) / note.getValue());
        System.out.println( note.getValue() + " " + amount/note.getValue());
        return "OK";
    }

    @Override
    public void printCash() {
        noteList.values().forEach(System.out::println);
    }

    private Note getNote(String currency, int value, int number) {
        return new Note()
                .setCurrency(currency)
                .setNumber(number)
                .setValue(value);
    }
}
