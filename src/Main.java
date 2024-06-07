import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();


        Task task1 = taskManager.createTask(new Task("Задача 1", "Помыть машину", TaskStatus.NEW));
        Task task2 = taskManager.createTask(new Task("Задача 2", "Подмести пол", TaskStatus.NEW));
        Task task3 = taskManager.createTask(new Task("Задача 3", "Вытрехнуть ковер", TaskStatus.NEW));
        Epic epic1 = taskManager.createEpic(new Epic("Задача 4", "Проверка работы программы", TaskStatus.NEW));
        Epic epic2 = taskManager.createEpic(new Epic("Задача 5", "Проверка работы программы", TaskStatus.NEW));
        Epic epic3 = taskManager.createEpic(new Epic("Задача 6", "Проверка работы программы", TaskStatus.NEW));
        SubTask subTask1=taskManager.createSubTask(new SubTask("Задача 3", "проверка первой подзадачи",TaskStatus.NEW));
        System.out.println("Имеются задачи: " + taskManager.getAllTasks());
        System.out.println("Имеются эпики: " + taskManager.getAllEpics());
        System.out.println("Имеются Подзадачи: " + taskManager.getAllSubTasks());
        taskManager.clearTask();
        taskManager.clearEpics();
        taskManager.clearSubTasks();
    }
}
