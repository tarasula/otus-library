package ru.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;
import ru.spring.service.CashServiceImpl;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands implements Quit.Command {

    private final CashServiceImpl cashService;

    /**
     * Add Notes shell method
     *
     * @param currency
     * @param value
     * @param number
     * @return String - OK or ERROR
     */
    @ShellMethod(value = "Add notes", key = {"add", "a", "+"})
    public String addNotes(String currency, int value, int number) {
        return cashService.addNotes(currency, value, number);
    }

    /**
     * Get cash shell method
     * @param currency
     * @param amount
     * @return String OK or ERROR
     */
    @ShellMethod(value = "Get Cash", key = {"get", "g", "-"})
    public String getCash(String currency, int amount) {
        return cashService.getCash(currency, amount);
    }

    /**
     * Print current cash method
     */
    @ShellMethod(value = "Print Cash", key = {"print", "p", "?"})
    public void printCash() {
        cashService.printCash();
    }

}
