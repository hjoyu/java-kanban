

public class Task {
    private int id;
    private String taskName;
    private String description;
    TaskStatus status;



    public Task(String name, String description){
        taskName=name;
        this.description = description;
        status = TaskStatus.NEW;
    }

    public void setId(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getTaskName(){return taskName;}

    public void SetTaskName(String taskName){this.taskName=taskName;}

    public void setDescription(String description){this.description=description;}

    public String getDescription(){return description;}

    public void setTaskStatus(TaskStatus status) {this.status = status;}

    public TaskStatus getTaskStatus(){return status;}

    @Override
    public String toString(){
        return ("id = " + id +
                ", имя задачи: " +taskName+
                ", описание: "+ description +
                ", статус: "+ status + "// ");
    }



}
