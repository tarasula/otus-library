package ru.spring.service;

public interface CashService {

    /**
     * Adding note method
     *
     * @param currency - banknote currency
     * @param value - banknote value
     * @param number - count of banknote
     * @return String - response OK or ERROR
     */
    String addNotes(String currency, int value, int number);

    /**
     * Get cash method
     *
     * @param currency - banknote currency
     * @param amount - amount of cash
     * @return String - response OK or ERROR
     */
    String getCash(String currency, int amount);

    /**
     * Print current cash method
     */
    void printCash();
}
