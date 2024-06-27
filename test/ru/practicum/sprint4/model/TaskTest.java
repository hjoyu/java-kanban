package ru.practicum.sprint4.model;

import ru.practicum.sprint4.service.InMemoryTaskManager;
import ru.practicum.sprint4.service.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TaskTest {

    TaskManager taskManager = new InMemoryTaskManager();

    @Test
    public void epicEqualsById() {
        Task task1 = new Task("Задача 1", "Проверка задачи 1");
        Task task2 = new Task("Задача 2", "Проверка задачи 2");

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        task2.setId(1);
        assertEquals(task1, task2, "Ошибка, разные задачи");
    }
}