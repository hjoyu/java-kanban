

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
        return ("id = " + id +", " +taskName+", "+ description +", "+ status + "// ");
    }
}
