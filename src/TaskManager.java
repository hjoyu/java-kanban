import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class TaskManager {
    HashMap<Integer,Task> tasks = new HashMap<>();
    HashMap<Integer,Epic> epics = new HashMap<>();
    HashMap<Integer,SubTask> subTasks = new HashMap<>();
    static int nextId;


    //--------------получение всех задач-------------
    public List<Task> getAllTasks(){
    return new ArrayList<>(tasks.values());//возвращает значения tasks
    }
    public List<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());//возвращает значения tasks
    }
    public List<SubTask> getAllSubTasks(){
        return new ArrayList<>(subTasks.values());//возвращает значения tasks
    }

//-----------------очистка задач-------------------
    public void clearTask(){
        tasks.clear();
        System.out.println("Список задач очищен.");
    }

    public void clearEpics(){
        epics.clear();
        System.out.println("Список эпиков очищен.");
    }

    public void clearSubTasks(){
        subTasks.clear();
        System.out.println("Список подзадач очищен.");
    }

    //----------------------Создание задач----------------------//
    public Task createTask( Task newTask){
        newTask.id=nextId;
        nextId++;
        tasks.put(newTask.id,newTask);//в мапу tasks кладем id задачи и сам task
        System.out.println(newTask.toString());
        return newTask;
    }

    public Epic createEpic( Epic epic){
        epic.id=nextId;
        nextId++;
        epics.put(epic.id, epic);//в мапу epics кладем id эпика и сам epic
        System.out.println(epic.toString());
        syncEpic(epic);
        return epic;
    }

    private void syncEpic(Epic epic){
        for (Integer subTaskId: epic.taskIds){
            SubTask subTask = subTasks.get(subTaskId);
            subTask.epicId=epic.id;
        }
    }

    public SubTask createSubTask(SubTask subTask){
        subTask.id=nextId;
        nextId++;
        subTasks.put(subTask.id,subTask);//в мапу subTasks кладем id подзадачи и subTask
        System.out.println(subTask.toString());
        return subTask;
    }

    //------------обновление задач------
    public void update(Task newTask){
        tasks.put(newTask.id,newTask);
    }

    public void update(Epic epic){
        epics.put(epic.id,epic);
        syncEpic(epic);
    }

    public void update(SubTask subTask){
        subTasks.put(subTask.id,subTask);
        syncEpic(epics.get(subTask.epicId));
    }

//----------------получение задач по id------------
public void getTaskById(int nextId){
    //ArrayList<>

}




    //changeID(){}

   // removeTask(){}




}
