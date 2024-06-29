package ru.practicum.sprint4.model;

import ru.practicum.sprint4.service.InMemoryTaskManager;
import ru.practicum.sprint4.service.TaskManager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EpicTest {

    TaskManager taskManager = new InMemoryTaskManager();

    @Test
    public void epicEqualsById() {
        Epic epic1 = new Epic("эпик 1", "проверка эпика 1");
        Epic epic2 = new Epic("эпик 2", "проверка эпика 2");

        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
        epic2.setId(epic1.getId());
        assertEquals(epic1, epic2, "Ошибка, разные Эпики");
    }
}