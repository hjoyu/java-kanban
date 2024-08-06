package ru.practicum.sprint4.service;

import ru.practicum.sprint4.exceptions.ManagerSaveException;
import ru.practicum.sprint4.model.*;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private final File file;
    private static final String HEADER = "id, type, name, status, description, epic \n";

    public FileBackedTaskManager(File file) {
        this.file = file;
    }

    private void save() {
        try {
            if (Files.exists(file.toPath())) {
                Files.delete(file.toPath());
            }
            Files.createFile(file.toPath());
        } catch (IOException e) {
            throw new ManagerSaveException("Не удалось найти файл для сохранения", e);
        }

        try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
            fileWriter.write(HEADER);

            for (Integer key : tasks.keySet()) {
                fileWriter.write(toString(tasks.get(key)) + "\n");
            }

            for (Integer key : epics.keySet()) {
                fileWriter.write(toString(epics.get(key)) + "\n");
            }

            for (Integer key : subTasks.keySet()) {
                fileWriter.write(toString(subTasks.get(key)) + "\n");
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка сохранения", e);
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager fileManager = new FileBackedTaskManager(file);
        fileManager.load();
        return fileManager;
    }

    public void load() {
        int maxId = 0;
        if (file.exists()) {
            try (BufferedReader bf = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
                bf.readLine();
                while (true) {
                    String line = bf.readLine();
                    if (line == null || line.isEmpty()) {
                        break;
                    }

                    Task task = fromString(line);
                    int id = task.getId();
                    switch (task.getType()) {
                        case TASK:
                            tasks.put(id, task);
                            break;

                        case EPIC:
                            Epic epic = (Epic) task;
                            epics.put(id, epic);
                            break;

                        case SUBTASK:
                            SubTask subTask = (SubTask) task;
                            subTasks.put(id, subTask);
                            Epic subEpic = epics.get(subTasks.get(id).getEpicId());
                            subEpic.addSubTaskIds(id);
                            break;
                    }
                    if (maxId < id) {
                        maxId = id;
                    }
                }
            } catch (IOException e) {
                throw new ManagerSaveException("Не удалось получить данные из файла", e);
            }
        }
        nextId = maxId;
    }

    private Task fromString(String value) {
        String[] columns = value.split(",");
        int id = Integer.parseInt(columns[0]);
        TasksType type = TasksType.valueOf(columns[1]);
        String name = columns[2];
        TaskStatus status = TaskStatus.valueOf(columns[3]);
        String description = columns[4];

        Task task;
        switch (type) {
            case TASK:
                task = new Task(id, name, description, status);
                break;

            case EPIC:
                task = new Epic(id, name, description);
                task.setTaskStatus(status);
                break;

            case SUBTASK:
                task = new SubTask(id, name, description, status, Integer.parseInt(columns[5]));
                break;

            default:
                throw new ManagerSaveException("Ошибка, неизвестный тип задачи " + type);
        }
        return task;
    }

    private static String toString(Task task) {
        String epicId;
        if (task.getType().equals(TasksType.SUBTASK)) {
            epicId = Integer.toString(((SubTask) task).getEpicId());
        } else {
            epicId = "";
        }

        return task.getId() + "," +
                task.getType() + "," +
                task.getTaskName() + "," +
                task.getTaskStatus() + "," +
                task.getDescription() + "," +
                epicId;
    }

    @Override
    public int createTask(Task task) {
        super.createTask(task);
        save();
        return task.getId();
    }

    @Override
    public int createEpic(Epic epic) {
        super.createEpic(epic);
        save();
        return epic.getId();
    }

    @Override
    public int createSubTask(SubTask subtask) {
        super.createSubTask(subtask);
        save();
        return subtask.getId();
    }

    @Override
    public void clearAllTasks() {
        super.clearAllTasks();
        save();
    }

    @Override
    public void clearAllEpics() {
        super.clearAllEpics();
        save();
    }

    @Override
    public void clearAllSubTasks() {
        super.clearAllSubTasks();
        save();
    }

    @Override
    public void clearTaskById(int id) {
        super.clearTaskById(id);
        save();
    }

    @Override
    public void clearEpicById(int id) {
        super.clearEpicById(id);
        save();
    }

    @Override
    public void clearSubTaskById(Integer id) {
        super.clearSubTaskById(id);
        save();
    }

    @Override
    public void update(Task newTask) {
        super.update(newTask);
        save();
    }

    @Override
    public void update(Epic newEpic) {
        super.update(newEpic);
        save();
    }

    @Override
    public void update(SubTask newSubtask) {
        super.update(newSubtask);
        save();
    }
}
