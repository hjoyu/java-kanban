public class SubTask extends Task {


    public SubTask(String name, String description, TaskStatus status) {
        super(name, description, status);
    }

    public SubTask(int id, String name, String description, TaskStatus status) {
        super(id, name, description, status);
    }

    //айди подзадачи совпадает с айди эпика. при завершении всех подзадач завершается и эпик
}
