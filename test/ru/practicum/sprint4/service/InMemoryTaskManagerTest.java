package ru.practicum.sprint4.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.sprint4.model.Epic;
import ru.practicum.sprint4.model.Task;
import ru.practicum.sprint4.model.SubTask;

import java.util.ArrayList;

class InMemoryTaskManagerTest {
    private static TaskManager taskManager;
    private static Task task;
    private static Epic epic;
    private static SubTask subtask;

    @BeforeEach
    void beforeEach() {
        taskManager = new InMemoryTaskManager();
        task = new Task("Test addNewTask description", "Test addNewTask");
        epic = new Epic("Test addNewEpic description", "Test addNewEpic");
        subtask = new SubTask("Test addNewSubtask description", "Test addNewSubtask", 1);
    }

    @Test
    void addNewTask() {
        int taskId = taskManager.createTask(task);

        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final ArrayList<Task> tasks = taskManager.getAllTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.getFirst(), "Задачи не совпадают.");
    }

    @Test
    void addNewEpic() {
        final int epicId = taskManager.createEpic(epic);
        final Epic savedEpic = taskManager.getEpicById(epicId);

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают");

        final ArrayList<Epic> epics = taskManager.getAllEpics();

        assertNotNull(epics, "Задачи не возвращаются");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.getFirst(), "Задачи не совпадают");
    }

    @Test
    void addNewSubtask() {
        taskManager.createEpic(epic);

        final int subtaskId = taskManager.createSubTask(subtask);
        final SubTask savedSubtask = taskManager.getSubTaskById(subtaskId);

        assertNotNull(savedSubtask, "Задача не найдена.");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают");

        final ArrayList<SubTask> subtasks = taskManager.getAllSubTasks();

        assertNotNull(subtasks, "Задачи не возвращаются");
        assertEquals(1, subtasks.size(), "Неверное количество задач.");
        assertEquals(subtask, subtasks.getFirst(), "Задачи не совпадают");
    }

    @Test
    void deleteTasksAndShouldReturnEmptyList() {
        taskManager.clearAllTasks();
        ArrayList<Task> tasks = taskManager.getAllTasks();
        assertTrue(tasks.isEmpty(), "Список не пуст");
    }

    @Test
    void deleteEpicsAndShouldReturnEmptyList() {
        taskManager.clearAllEpics();
        ArrayList<Epic> epics = taskManager.getAllEpics();
        assertTrue(epics.isEmpty(), "Список не пуст");
    }

    @Test
    void deleteSubtasksAndShouldReturnEmptyList() {
        taskManager.clearAllSubTasks();
        ArrayList<SubTask> subtasks = taskManager.getAllSubTasks();
        assertTrue(subtasks.isEmpty(), "Список не пуст");
    }
}