package ru.practicum.sprint4.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.practicum.sprint4.model.Task;
import static org.junit.jupiter.api.Assertions.*;
import static ru.practicum.sprint4.service.InMemoryHistoryManager.HISTORY_LIMIT;

class InMemoryHistoryManagerTest {

    private final HistoryManager historyManager = new InMemoryHistoryManager();
    static List<Task> history;
    static Task task;

    @BeforeAll
    static void beforeAll(){
        task = new Task("Test addNewTask description", "Test addNewTask");
        history=new ArrayList<>();
    }

    @Test
    void add(){
        historyManager.add(task);
        history = historyManager.getHistory();
        assertEquals(1,history.size(), "Lлина списка не равна 1");
    }

    @Test
    void shouldReturn10Tasks(){
        for (int i = 0; i < 12; i++) {
            historyManager.add(task);
        }
        history = historyManager.getHistory();
        assertEquals(HISTORY_LIMIT, history.size(), "Длина списка не соответствует заданому лимиту");
    }



}