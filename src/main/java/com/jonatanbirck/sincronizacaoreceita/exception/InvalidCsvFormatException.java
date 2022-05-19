package com.jonatanbirck.sincronizacaoreceita.exception;

import java.io.IOException;

public class InvalidCsvFormatException extends IOException {

    public InvalidCsvFormatException() {
        super("csv contains invalid data");
    }

    public InvalidCsvFormatException(String message) {
        super(message);
    }

}
