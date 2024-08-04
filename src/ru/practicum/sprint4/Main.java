package ru.practicum.sprint4;

import ru.practicum.sprint4.service.*;
import ru.practicum.sprint4.model.*;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File file = new File("file.csv");
        FileBackedTaskManager fileManager = FileBackedTaskManager.loadFromFile(file);

        fileManager.createTask(new Task("Задача 1", "Помыть машину"));
        fileManager.createTask(new Task("Задача 2", "Подмести пол"));
        fileManager.createTask(new Task("Задача 3", "Вытрехнуть ковер"));

        System.out.println("Вывод всех созданных задач:");
        System.out.println(fileManager.getAllTasks());
        System.out.println("Вывод всех задач по id:");
        System.out.println(fileManager.getTaskById(1));
        System.out.println(fileManager.getTaskById(2));
        System.out.println(fileManager.getTaskById(3));
        for (int i = 0; i < 13; i++) {
            fileManager.createTask(new Task("Задача " + i, "проверка..."));
            fileManager.getTaskById(i + 3);
        }

        System.out.println("\n история");
        System.out.println(fileManager.getHistory());
        System.out.println("Размер истории: " + fileManager.getHistory().size());

        System.out.println("\n Обновление задач:");
        Task task4 = new Task("Задача 1", "Мою машину");
        task4.setId(1);
        task4.setTaskStatus(TaskStatus.IN_PROGRESS);
        fileManager.update(task4);
        Task task5 = new Task("Задача 3", "Вытрехнул ковер");
        task5.setId(2);
        task5.setTaskStatus(TaskStatus.DONE);
        fileManager.update(task5);
        System.out.println(fileManager.getAllTasks());

        System.out.println("удалили вторую задачу");
        fileManager.clearTaskById(2);
        System.out.println(fileManager.getAllTasks());
        System.out.print("Удалили все задачи: ");
        fileManager.clearAllTasks();
        System.out.println(fileManager.getAllTasks());

        System.out.println("-----------------------");

        Epic epic1 = new Epic("Эпик 4", "Проверка работы эпика");
        Epic epic2 = new Epic("Эпик 5", "Проверка работы эпика");
        Epic epic3 = new Epic("Эпик 6", "Проверка работы эпика");
        fileManager.createEpic(epic1);
        fileManager.createEpic(epic2);
        fileManager.createEpic(epic3);


        SubTask subTask1 = new SubTask("ПодЭпик 4.1", "проверка первой подзадачи", epic1.getId());
        SubTask subTask2 = new SubTask("ПодЭпик 4.2", "проверка второй подзадачи", epic1.getId());
        SubTask subTask3 = new SubTask("ПодЭпик 5.1", "проверка первой подзадачи 5 эпика", epic2.getId());
        SubTask subTask4 = new SubTask("ПодЭпик 5.2", "проверка второй подзадачи 5 эпика", epic2.getId());
        fileManager.createSubTask(subTask1);
        fileManager.createSubTask(subTask2);
        fileManager.createSubTask(subTask3);
        fileManager.createSubTask(subTask4);

        System.out.println("\n Все подзадачи: " + fileManager.getAllSubTasks());
        System.out.println("\n Все эпики: " + fileManager.getAllEpics());

        System.out.println("\nвывели Эпик с id- " + epic1.getId() +
                ": " + fileManager.getEpicById(epic1.getId()));
        System.out.println("вывели Cабтаск с id- " + subTask1.getId() +
                ": " + fileManager.getSubTaskById(subTask1.getId()));

        System.out.println("\n Получаем сабтаски 4-го эпика: " + fileManager.getSubTaskByEpic(epic1.getId()));

        System.out.println("\n обновление Сабтасков:");
        SubTask subTask = new SubTask("Подзадача 4.1", "проверка исправленной подзадачи", epic1.getId());
        subTask.setId(subTask1.getId());
        subTask.setTaskStatus(TaskStatus.IN_PROGRESS);
        fileManager.update(subTask);
        SubTask subTask5 = new SubTask("Подзадача 4.2", "проверка исправленной подзадачи", epic1.getId());
        subTask5.setId(subTask2.getId());
        subTask5.setTaskStatus(TaskStatus.IN_PROGRESS);
        fileManager.update(subTask5);
        System.out.println("\n Все подзадачи: " + fileManager.getAllSubTasks());
        System.out.println("\n Все эпики: " + fileManager.getAllEpics());

        System.out.println("\n Обновление Эпика:");
        Epic epic = new Epic("Эпик 6", "Проверка исправления эпика");
        epic.setId(epic3.getId());
        fileManager.update(epic);
        System.out.println("изменили эпик 6: " + fileManager.getEpicById(epic3.getId()));

        /*----------проверка метода удаления всех подзадач-----
        fileManager.clearAllSubTasks();
        System.out.println("\n список всех эпиков: " + fileManager.getAllEpics());
        System.out.println("\n список всех сабтасков: " + fileManager.getAllSubTasks());*/

        System.out.println("\n Удаление по ID: удалили эпик 5 и подзадачу 4.2");
        fileManager.clearEpicById(epic2.getId());
        fileManager.clearSubTaskById(subTask2.getId());
        System.out.println("Все подзадачи: " + fileManager.getAllSubTasks());
        System.out.println("Все эпики: " + fileManager.getAllEpics());

        System.out.println("\n Удаление эпиков и подзадач");
        fileManager.clearAllEpics();
        System.out.println(fileManager.getAllSubTasks());
        System.out.println(fileManager.getAllEpics());
        Epic lastEpic = new Epic("Эпик ласт", "Проверка работы файла");
        fileManager.createEpic(lastEpic);

    }

}
