package ru.practicum.sprint4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.sprint4.exceptions.ManagerSaveException;
import ru.practicum.sprint4.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FileBackedTaskManagerTest {
    File file;
    Task task;
    Epic epic;
    SubTask subtask;

    @BeforeEach
    void beforeEach() throws IOException {
        file = File.createTempFile("BackedTest", ".csv");
        task = new Task("Test addNewTask","Test addNewTask description");
        epic = new Epic("Test addNewEpic","Test addNewEpic description");
        subtask = new SubTask("Test addNewSubtask", "Test addNewSubtask description", 2);
    }

    @Test
    void saveNewTaskInFile() {
        FileBackedTaskManager fileManager = new FileBackedTaskManager(file);
        Task task1 = new Task("Task", "Task description");
        fileManager.createTask(task1);
        assertTrue(Files.exists(file.toPath()));
    }

    @Test
    void loadFromFile() {
        FileBackedTaskManager fileManager = new FileBackedTaskManager(file);

        fileManager.createTask(task);
        fileManager.createEpic(epic);
        fileManager.createSubTask(subtask);

        assertEquals(1, fileManager.tasks.size(),"неверное количество задач");
        assertEquals(1, fileManager.epics.size(),"неверное количество задач");
        assertEquals(1, fileManager.subTasks.size(),"неверное количество задач");

        FileBackedTaskManager fileManager2 = FileBackedTaskManager.loadFromFile(file);

       assertEquals(fileManager.getAllTasks(), fileManager2.getAllTasks(), "Количество задач не совпадает");
       assertEquals(fileManager.getAllEpics(), fileManager2.getAllEpics(), "Количество эпиков не совпадает");
       assertEquals(fileManager.getAllSubTasks(), fileManager2.getAllSubTasks(), "Количество сабтасков не совпадает");
    }
}
