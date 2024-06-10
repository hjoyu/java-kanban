

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();


        taskManager.createTask(new Task("Задача 1", "Помыть машину"));
        taskManager.createTask(new Task("Задача 2", "Подмести пол"));
        taskManager.createTask(new Task("Задача 3", "Вытрехнуть ковер"));
        System.out.println("Вывод всех созданных задач:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("Вывод всех задач по id:");
        System.out.println(taskManager.getTaskById(1));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getTaskById(3));

        System.out.println("Обновление задач:");
        Task task4 = new Task("Задача 1","Мою машину");
        task4.setId(1);
        task4.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskManager.update(task4);
        Task task5 = new Task("Задача 3","Вытрехнул ковер");
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

        taskManager.createEpic(new Epic("Эпик 4", "Проверка работы эпика"));
        taskManager.createEpic(new Epic("Эпик 5", "Проверка работы эпика"));
        taskManager.createEpic(new Epic("Эпик 6", "Проверка работы эпика"));

        taskManager.createSubTask(new SubTask("ПодЭпик 4.1", "проверка первой подзадачи",4));
        taskManager.createSubTask(new SubTask("ПодЭпик 4.2", "проверка второй подзадачи",4));
        taskManager.createSubTask(new SubTask("ПодЭпик 5.1", "проверка первой подзадачи 5 эпика",5));
        taskManager.createSubTask(new SubTask("ПодЭпик 5.2", "проверка второй подзадачи 5 эпика",5));

        System.out.println("Все подзадачи: " + taskManager.getAllSubTasks());
        System.out.println("Все эпики: " + taskManager.getAllEpics());

        System.out.println("вывели Эпик с id-4: " + taskManager.getEpicById(4));
        System.out.println("вывели Cабтаск с id-7: " + taskManager.getSubTaskById(7));

        System.out.println("\n Получаем сабтаски 4-го эпика: " + taskManager.getSubTaskByEpic(4));

        System.out.println("обновление SubTask:");
        SubTask subTask = new SubTask("Подзадача 4.1", "проверка исправленной подзадачи",4);
        subTask.setId(7);
        subTask.setTaskStatus(TaskStatus.DONE);
        taskManager.update(subTask);
        SubTask subTask1 = new SubTask("Подзадача 4.2", "проверка исправленной подзадачи",4);
        subTask1.setId(8);
        subTask1.setTaskStatus(TaskStatus.DONE);
        taskManager.update(subTask1);
        System.out.println("Все подзадачи: " + taskManager.getAllSubTasks());
        System.out.println("Все эпики: " + taskManager.getAllEpics());

        System.out.println("Обновление Epic:");
        Epic epic = new Epic("Эпик 6", "Проверка исправления эпика");
        epic.setId(6);
        taskManager.update(epic);
        System.out.println("изменили эпик 6: " + taskManager.getEpicById(6));

        System.out.println("\n Удаление по ID: удалили эпик 5 и подзадачу 4.2");
        taskManager.clearEpicById(5);
        taskManager.clearSubTaskById(8);
        System.out.println("Все подзадачи: " + taskManager.getAllSubTasks());
        System.out.println("Все эпики: " + taskManager.getAllEpics());

        System.out.println("\n Удаление эпиков и подзадач");
        taskManager.clearAllEpics();
        System.out.println(taskManager.getAllSubTasks());
        System.out.println(taskManager.getAllEpics());
    }
}
