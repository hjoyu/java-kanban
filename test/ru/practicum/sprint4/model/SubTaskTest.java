package ru.practicum.sprint4.model;

import ru.practicum.sprint4.service.InMemoryTaskManager;
import ru.practicum.sprint4.service.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {

    TaskManager taskManager = new InMemoryTaskManager();

    @Test
    public void taskEqualsById() {
        SubTask subtask1 = new SubTask("Подзадача 1", "проверка 1 подзадачи", 4);
        SubTask subtask2 = new SubTask("Подзадача 2", "проверка 2 подзадачи", 4);

        taskManager.createSubTask(subtask1);
        taskManager.createSubTask(subtask2);
        subtask2.setId(1);
        assertEquals(subtask1, subtask2, "Ошибка, разные подзадачи");
    }
}