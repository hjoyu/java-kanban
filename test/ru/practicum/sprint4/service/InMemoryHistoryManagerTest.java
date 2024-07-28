package ru.practicum.sprint4.service;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.practicum.sprint4.model.Task;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    private final HistoryManager historyManager = new InMemoryHistoryManager();
    static List<Task> history;
    static Task task;
    static Task task2;

    @BeforeAll
    static void beforeAll(){
        task = new Task(0,"Test 1 addNewTask description", "Test addNewTask");
        task2 = new Task(1,"Test 2 addNewTask description", "Test addNewTask");
    }

    @Test
    void add(){
        historyManager.add(task);
        historyManager.add(task2);
        history = historyManager.getHistory();
        assertEquals(2,history.size(), "Длина списка не равна 2");
    }

    @Test
    void sizeShouldEqualsOne(){
        historyManager.add(task);
        historyManager.add(task2);
        historyManager.remove(task2.getId());//проверили метод remove
        history = historyManager.getHistory();
        assertEquals(1,history.size(), "Длина списка не равна 1");
    }
}