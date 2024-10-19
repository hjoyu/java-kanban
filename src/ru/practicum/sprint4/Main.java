package ru.practicum.sprint4;

import ru.practicum.sprint4.service.*;
import ru.practicum.sprint4.model.*;

import java.time.Duration;
import java.time.LocalDateTime;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        FileBackedTaskManager fileManager = Managers.getDefaultFileManager();
        Task task1 = new Task("Задача 2", "Подмести пол");
        task1.setStartTime(LocalDateTime.now());
        task1.setDuration(Duration.ofMinutes(30));

        fileManager.createTask(task1);
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
        SubTask subtask = new SubTask("Test addNewSubtask", "Test addNewSubtask description", 2);
        subtask.setStartTime(LocalDateTime.now().plusHours(1));
        subtask.setDuration(Duration.ofMinutes(15));

        fileManager.createEpic(epic);
        fileManager.createSubTask(subtask);
    }
}
