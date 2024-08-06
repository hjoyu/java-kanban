package ru.practicum.sprint4.exceptions;

public class ManagerSaveException extends RuntimeException {

    public ManagerSaveException(String message) {
        super(message);
    }

    public ManagerSaveException(String message, Exception e) {
        super(message);
        if (e.getCause() != null) {
            System.out.println("Причина: " + e.getCause());
        }
    }
}
