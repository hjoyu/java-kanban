package ru.practicum.sprint4;

import ru.practicum.sprint4.service.*;
import ru.practicum.sprint4.model.*;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

        taskManager.createTask(new Task("Задача 1", "Помыть машину"));
        taskManager.createTask(new Task("Задача 2", "Подмести пол"));
        taskManager.createTask(new Task("Задача 3", "Вытрехнуть ковер"));

        System.out.println("Вывод всех созданных задач:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("Вывод всех задач по id:");
        System.out.println(taskManager.getTaskById(1));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getTaskById(3));
        for (int i = 0; i < 13; i++) {
            taskManager.createTask(new Task("Задача " + i, "проверка..."));
            taskManager.getTaskById(i+3);
        }
       // System.out.println(historyManager.getHistory());

        System.out.println("\n история");
        System.out.println(taskManager.getHistory());
        System.out.println("Размер истории: " + taskManager.getHistory().size());

        System.out.println("\n Обновление задач:");
        Task task4 = new Task("Задача 1", "Мою машину");
        task4.setId(1);
        task4.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskManager.update(task4);
        Task task5 = new Task("Задача 3", "Вытрехнул ковер");
        task5.setId(2);
        task5.setTaskStatus(TaskStatus.DONE);
        taskManager.update(task5);
        System.out.println(taskManager.getAllTasks());

        System.out.println("удалили вторую задачу");
        taskManager.clearTaskById(2);
        System.out.println(taskManager.getAllTasks());
        System.out.print("Удалили все задачи: ");
        taskManager.clearAllTasks();
        System.out.println(taskManager.getAllTasks());

        System.out.println("-----------------------");

        Epic epic1 = new Epic("Эпик 4", "Проверка работы эпика");
        Epic epic2 = new Epic("Эпик 5", "Проверка работы эпика");
        Epic epic3 = new Epic("Эпик 6", "Проверка работы эпика");
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
        taskManager.createEpic(epic3);


        SubTask subTask1 = new SubTask("ПодЭпик 4.1", "проверка первой подзадачи", epic1.getId());
        SubTask subTask2 = new SubTask("ПодЭпик 4.2", "проверка второй подзадачи", epic1.getId());
        SubTask subTask3 = new SubTask("ПодЭпик 5.1", "проверка первой подзадачи 5 эпика", epic2.getId());
        SubTask subTask4 = new SubTask("ПодЭпик 5.2", "проверка второй подзадачи 5 эпика", epic2.getId());
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        taskManager.createSubTask(subTask3);
        taskManager.createSubTask(subTask4);

        System.out.println("\n Все подзадачи: " + taskManager.getAllSubTasks());
        System.out.println("\n Все эпики: " + taskManager.getAllEpics());

        System.out.println("\nвывели Эпик с id- "+ epic1.getId() +
                ": " + taskManager.getEpicById(epic1.getId()));
        System.out.println("вывели Cабтаск с id- " + subTask1.getId() +
                ": " + taskManager.getSubTaskById(subTask1.getId()));

        System.out.println("\n Получаем сабтаски 4-го эпика: " + taskManager.getSubTaskByEpic(epic1.getId()));

        System.out.println("\n обновление Сабтасков:");
        SubTask subTask = new SubTask("Подзадача 4.1", "проверка исправленной подзадачи", epic1.getId());
        subTask.setId(subTask1.getId());
        subTask.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskManager.update(subTask);
        SubTask subTask5 = new SubTask("Подзадача 4.2", "проверка исправленной подзадачи", epic1.getId());
        subTask5.setId(subTask2.getId());
        subTask5.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskManager.update(subTask5);
        System.out.println("\n Все подзадачи: " + taskManager.getAllSubTasks());
        System.out.println("\n Все эпики: " + taskManager.getAllEpics());

        System.out.println("\n Обновление Эпика:");
        Epic epic = new Epic("Эпик 6", "Проверка исправления эпика");
        epic.setId(epic3.getId());
        taskManager.update(epic);
        System.out.println("изменили эпик 6: " + taskManager.getEpicById(epic3.getId()));

        /*----------проверка метода удаления всех подзадач-----
        taskManager.clearAllSubTasks();
        System.out.println("\n список всех эпиков: " + taskManager.getAllEpics());
        System.out.println("\n список всех сабтасков: " + taskManager.getAllSubTasks());*/

        System.out.println("\n Удаление по ID: удалили эпик 5 и подзадачу 4.2");
        taskManager.clearEpicById(epic2.getId());
        taskManager.clearSubTaskById(subTask2.getId());
        System.out.println("Все подзадачи: " + taskManager.getAllSubTasks());
        System.out.println("Все эпики: " + taskManager.getAllEpics());

        System.out.println("\n Удаление эпиков и подзадач");
        taskManager.clearAllEpics();
        System.out.println(taskManager.getAllSubTasks());
        System.out.println(taskManager.getAllEpics());

    }

}
