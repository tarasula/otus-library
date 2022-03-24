package ru.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({CashServiceImpl.class})
class BookServiceImplTest {

    @Autowired
    private CashServiceImpl cashService;

    @Test
    void getAddNotesTest() {
        String getCashResult = cashService.addNotes("USD", 100, 30);
        assertEquals(getCashResult, "OK");
    }

    @Test
    void getCashServiceTest() {
        String getCashResult = cashService.getCash("USD", 2000);
        assertEquals(getCashResult, "OK");
    }

    @Test
    void getAddNotesValueErrorTest() {
        String getCashResult = cashService.addNotes("USD", 115, 30);
        assertEquals(getCashResult, "ERROR");
    }

    @Test
    void getAddNotesCurrencyErrorTest() {
        String getCashResult = cashService.addNotes("usd", 100, 30);
        assertEquals(getCashResult, "ERROR");
    }
}
