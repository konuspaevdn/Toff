package edu.hw5.Task3.HelpParsers;

import java.time.LocalDate;

public abstract class Parser {
    protected Parser next = null;

    public abstract LocalDate get(String str);

    public void setNext(Parser next) {
        this.next = next;
    }
}
