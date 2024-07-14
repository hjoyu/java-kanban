package ru.practicum.sprint4.service;

import ru.practicum.sprint4.model.Epic;
import ru.practicum.sprint4.model.SubTask;
import ru.practicum.sprint4.model.Task;
import ru.practicum.sprint4.model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    public final HashMap<Integer, Task> tasks;
    public final HashMap<Integer, Epic> epics;
    public final HashMap<Integer, SubTask> subTasks;
    private int nextId;
    private final HistoryManager historyManager;

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
        nextId = 0;
        historyManager=Managers.getDefaultHistory();
    }

    //--------------получение всех задач-------------
    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());//возвращает значения tasks
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());//возвращает значения tasks
    }

    @Override
    public ArrayList<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());//возвращает значения tasks
    }

    //-----------------очистка всех задач-------------------
    @Override
    public void clearAllTasks() {
        for (Task task: tasks.values()){
            historyManager.remove(task.getId());
        }
        tasks.clear();
    }

    @Override
    public void clearAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public void clearAllSubTasks() {
        for (Epic epic : epics.values()) {
            epic.getTaskIds().clear();
            epic.setTaskStatus(TaskStatus.NEW);
        }
        subTasks.clear();
    }

    //----------------------Создание задач----------------------//
    @Override
    public int createTask(Task newTask) {
        int taskId = generateId();
        newTask.setId(taskId);
        tasks.put(taskId, newTask);
        return taskId;//в мапу tasks кладем id задачи и сам task
    }

    @Override
    public int createEpic(Epic epic) {
        int epicId = generateId();
        epic.setId(epicId);
        epics.put(epic.getId(), epic);
        return epicId;//в мапу epics кладем id эпика и сам epic
    }

    @Override
    public int createSubTask(SubTask subTask) {
        int subTaskId = generateId();
        subTask.setId(subTaskId);
        Epic epic = epics.get(subTask.getEpicId());
        if (epic != null) {
            subTasks.put(subTaskId, subTask);
            epic.addTaskIds(subTaskId);
            syncEpic(epic);
        }
        return subTaskId;
    }

    //------------обновление задач------
    @Override
    public void update(Task newTask) {
        if ((newTask == null) || (!tasks.containsKey(newTask.getId()))) {
            System.out.println("tasks==null");
            return;
        }
        tasks.replace(newTask.getId(), newTask);
    }

    @Override
    public void update(Epic epic) {
        if ((epic == null) || (!epics.containsKey(epic.getId()))) {
            System.out.println("epic==null");
            return;
        }
        epics.replace(epic.getId(), epic);
    }

    @Override
    public void update(SubTask subTask) {
        if ((subTask == null) || (!subTasks.containsKey(subTask.getId()))) {
            System.out.println("subTask==null");
            return;
        }
        Epic epic = epics.get(subTask.getEpicId());
        if (epic == null) {
            return;
        }
        subTasks.replace(subTask.getId(), subTask);
        syncEpic(epic);
    }

    //----------------получение задач по id------------
    @Override
    public Task getTaskById(int nextId) {
        Task task = tasks.get(nextId);
        if (task == null){
            return null;
        }
        historyManager.add(task);
        return task;
    }

    @Override
    public Epic getEpicById(int nextId) {
        Epic epic = epics.get(nextId);
        if (epic == null){
            return null;
        }
        historyManager.add(epic);
        return epic;

    }

    @Override
    public SubTask getSubTaskById(int nextId) {
        SubTask subTask = subTasks.get(nextId);
        if (subTask == null) {
            return null;
        }
        historyManager.add(subTask);
        return subTask;
    }

    //----------------удаление задач по id-------
    @Override
    public void clearTaskById(int nextId) {
        tasks.remove(nextId);
        historyManager.remove(nextId);
    }

    @Override
    public void clearEpicById(int nextId) {
        Epic epic = epics.get(nextId);
        for (Integer taskId : epic.getTaskIds()){
            subTasks.remove(taskId);
        }
        epic.getTaskIds().clear();
        epics.remove(nextId);
    }

    @Override
    public void clearSubTaskById(Integer nextId) {
        SubTask subTask = subTasks.get(nextId);
        Epic epic = epics.get(subTask.getEpicId());
        epic.getTaskIds().remove(nextId);
        syncEpic(epic);
        subTasks.remove(nextId);
    }

    @Override
    public List<SubTask> getSubTaskByEpic(int nextId) {
        ArrayList<SubTask> newSubTasks = new ArrayList<>();
        Epic epic = epics.get(nextId);
        for (Integer subTaskId : epic.getTaskIds()) {
            newSubTasks.add(subTasks.get(subTaskId));
        }
        return newSubTasks;
    }

    @Override
    public void syncEpic(Epic epic) {
        if (!epics.containsKey(epic.getId())) {
            return;
        }

        List<SubTask> epicSubtasks = new ArrayList<>();

        int countNew = 0;
        int countDone = 0;

        for (int i = 0; i < epic.getTaskIds().size(); i++) {
            epicSubtasks.add(subTasks.get(epic.getTaskIds().get(i)));
        }
        for (SubTask subtask : epicSubtasks){//проходимся по сабтаскам и проверяем их статус
            if (subtask.getTaskStatus() == TaskStatus.NEW) {
                countNew++;
            } else if (subtask.getTaskStatus() == TaskStatus.DONE) {
                countDone++;
            }

            if (countNew == epicSubtasks.size()){//исходя из проверенных статусов сабтасков выставляем статус эпика
                epic.setTaskStatus(TaskStatus.NEW);
            } else if (countDone == epicSubtasks.size()) {
                epic.setTaskStatus(TaskStatus.DONE);
            } else {
                epic.setTaskStatus(TaskStatus.IN_PROGRESS);
            }
        }
    }

    @Override
    public int generateId() {
        return ++nextId;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}

