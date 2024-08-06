package ru.practicum.sprint4.service;

import java.io.File;

public class Managers {
    private static final File file = new File("file.csv");

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static FileBackedTaskManager getDefaultFileManager() {
        return FileBackedTaskManager.loadFromFile(file);
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
