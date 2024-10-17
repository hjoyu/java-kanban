package ru.practicum.sprint4.service;

import ru.practicum.sprint4.model.Epic;
import ru.practicum.sprint4.model.SubTask;
import ru.practicum.sprint4.model.Task;
import ru.practicum.sprint4.model.TaskStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    protected final HashMap<Integer, Task> tasks;
    protected final HashMap<Integer, Epic> epics;
    protected final HashMap<Integer, SubTask> subTasks;
    protected int nextId;
    private final HistoryManager historyManager;
    protected final Set<Task> prioritized;

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
        nextId = 0;
        historyManager = Managers.getDefaultHistory();
        prioritized = new TreeSet<>(taskComparator);
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
        for (Task task : tasks.values()) {
            historyManager.remove(task.getId());
        }
        tasks.clear();
    }

    @Override
    public void clearAllEpics() {
        for (Epic epic : epics.values()) {
            historyManager.remove(epic.getId());
        }
        epics.clear();
        subTasks.clear();
    }

    @Override
    public void clearAllSubTasks() {
        for (SubTask subTask : subTasks.values()) {
            historyManager.remove(subTask.getId());
        }
        for (Epic epic : epics.values()) {
            epic.getTaskIds().clear();
            epic.setTaskStatus(TaskStatus.NEW);
        }
        subTasks.clear();
    }

    //----------------------Создание задач----------------------//
    @Override
    public Task createTask(Task newTask) {
        if (!valid(newTask)) {
            System.out.println("Задача пересекается по времени с имеющимися задачами");
            return null;
        }
        int taskId = generateId();
        newTask.setId(taskId);
        tasks.put(taskId, newTask);
        prioritized.add(newTask);
        return newTask;//в мапу tasks кладем id задачи и сам task
    }

    @Override
    public int createEpic(Epic epic) {
        int epicId = generateId();
        epic.setId(epicId);
        epics.put(epic.getId(), epic);
        return epicId;//в мапу epics кладем id эпика и сам epic
    }

    @Override
    public SubTask createSubTask(SubTask subTask) {
        if (!valid(subTask)) {
            System.out.println("Подзадача пересекается по времени с имеющимися задачами");
            return null;
        }
        int subTaskId = generateId();
        subTask.setId(subTaskId);
        Epic epic = epics.get(subTask.getEpicId());
        if (epic == null) {
            System.out.println("Данного эпика не существует");
            return null;
        } else {
            subTasks.put(subTaskId, subTask);
            epic.addSubTaskIds(subTaskId);
            syncEpic(epic);
        }
        createEpicTime(epic);
        prioritized.add(subTask);
        return subTask;
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
        if (task == null) {
            return null;
        }
        historyManager.add(task);
        return task;
    }

    @Override
    public Epic getEpicById(int nextId) {
        Epic epic = epics.get(nextId);
        if (epic == null) {
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
        historyManager.remove(nextId);
        for (Integer taskId : epic.getTaskIds()) {
            subTasks.remove(taskId);
        }
        epic.getTaskIds().clear();
        epics.remove(nextId);
    }

    @Override
    public void clearSubTaskById(Integer nextId) {
        SubTask subTask = subTasks.get(nextId);
        historyManager.remove(nextId);
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
        for (SubTask subtask : epicSubtasks) {
            if (subtask.getTaskStatus() == TaskStatus.NEW) {
                countNew++;
            } else if (subtask.getTaskStatus() == TaskStatus.DONE) {
                countDone++;
            }

            if (countNew == epicSubtasks.size()) {
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

    Comparator<Task> taskComparator = (o1, o2) -> {
        if (o1.getId() == o2.getId()) {
            return 0;
        }
        if (o1.getStartTime() == null) {
            return 1;
        }
        if (o2.getStartTime() == null) {
            return -1;
        }
        if (o1.getStartTime().isBefore(o2.getStartTime())) {
            return -1;
        } else if (o1.getStartTime().isAfter(o2.getStartTime())) {
            return 1;
        } else {
            return o1.getId() - o2.getId();
        }
    };

    @Override
    public List<Task> getPrioritizedTasks() {
        return new ArrayList<>(prioritized);
    }

    private boolean valid(Task task) {
        if (prioritized.isEmpty()) {
            return true;
        }
        LocalDateTime start = task.getStartTime();
        LocalDateTime finish = task.getEndTime();
        if (start == null) {
            return true;
        }
        for (Task prioritizedTask : prioritized) {
            LocalDateTime begining = prioritizedTask.getStartTime();
            LocalDateTime end = prioritizedTask.getEndTime();
            if (start.isEqual(begining) || start.isEqual(end) || finish.isEqual(begining) || finish.isEqual(end)) {
                return false;
            }
            if (start.isAfter(begining) && start.isBefore(end)
                    || finish.isAfter(begining) && finish.isBefore(end)
                    || start.isBefore(begining) && finish.isAfter(end)) {
                return false;
            }
        }
        return true;
    }

    private void createEpicTime(Epic epic) {
        List<Integer> subTaskList = epic.getTaskIds();
        if (subTaskList.isEmpty()) {
            epic.setDuration(null);
            epic.setStartTime(null);
            epic.setEndTime(null);
            return;
        }
        for (Integer subTaskId : subTaskList) {
            LocalDateTime subTaskStartTime = subTasks.get(subTaskId).getStartTime();
            LocalDateTime subTaskEndTime = subTasks.get(subTaskId).getEndTime();
            Duration subTaskDuration = subTasks.get(subTaskId).getDuration();
            if (epic.getStartTime() == null) {
                epic.setStartTime(subTaskStartTime);
                epic.setEndTime(subTaskEndTime);
                if (epic.getDuration() == null) {
                    epic.setDuration(subTaskDuration);
                } else {
                    epic.setDuration(epic.getDuration().plus(subTaskDuration));
                }
            } else {
                if (epic.getStartTime().isAfter(subTaskStartTime)) {
                    epic.setStartTime(subTaskStartTime);
                    if (epic.getDuration() == null) {
                        epic.setEndTime(subTaskEndTime);
                    } else {
                        epic.setDuration(epic.getDuration().plus(subTaskDuration));
                    }
                }
                if (subTaskEndTime.isAfter(epic.getEndTime())) {
                    epic.setEndTime(subTaskEndTime);
                    if (epic.getDuration() == null) {
                        epic.setEndTime(subTaskEndTime);
                    } else {
                        epic.setDuration(epic.getDuration().plus(subTaskDuration));
                    }
                }
            }
        }
    }
}


