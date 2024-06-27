package ru.practicum.sprint4.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

@Test
    void getDefault(){
assertNotNull(Managers.getDefault(), "Ошибка, не возвращает TaskManager");
}

@Test
    void getDefaultHistory(){
    assertNotNull(Managers.getDefaultHistory(), "Ошибка, не возвращает HistoryManager");
}
}