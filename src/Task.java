

public class Task {
    private String taskName;
    private String description;
    TaskStatus status;
     int id;


    public Task(String name, String description, TaskStatus status){
        taskName=name;
        this.description = description;
        this.status = status;
    }

    public Task(int id, String name, String description, TaskStatus status){
        this.id=id;
        taskName=name;
        this.description = description;
        this.status = status;
    }

    public Task(int id, String name, String description) {
        this.id=id;
        taskName=name;
        this.description = description;
    }

    public Task(String name, String description) {
        taskName=name;
        this.description = description;
    }


    public void setId(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getTaskName(){
        return taskName;
    }

    @Override
    public String toString(){
        return (id +", " +taskName+", "+ description +", "+ status);
    }
}
