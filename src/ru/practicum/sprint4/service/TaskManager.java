package ru.practicum.sprint4.service;

import ru.practicum.sprint4.model.Epic;
import ru.practicum.sprint4.model.SubTask;
import ru.practicum.sprint4.model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    //--------------получение всех задач-------------
    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<SubTask> getAllSubTasks();

    //-----------------очистка всех задач-------------------
    void clearAllTasks();

    void clearAllEpics();

    void clearAllSubTasks();

    //----------------------Создание задач----------------------//
    int createTask(Task newTask);

    int createEpic(Epic epic);

    int createSubTask(SubTask subTask);

    //------------обновление задач------
    void update(Task newTask);

    void update(Epic epic);

    void update(SubTask subTask);

    //----------------получение задач по id------------
    Task getTaskById(int nextId);

    Epic getEpicById(int nextId);

    SubTask getSubTaskById(int nextId);

    //----------------удаление задач по id-------
    void clearTaskById(int nextId);

    void clearEpicById(int nextId);

    void clearSubTaskById(Integer nextId);

    List<SubTask> getSubTaskByEpic(int nextId);

    void syncEpic(Epic epic);

    int generateId();

    ArrayList<Task> getHistory();
}
