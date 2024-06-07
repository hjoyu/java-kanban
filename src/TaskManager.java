import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class TaskManager {
    HashMap<Integer,Task> tasks = new HashMap<>();
    HashMap<Integer,Epic> epics = new HashMap<>();
    HashMap<Integer,SubTask> subTasks = new HashMap<>();
    static int id;

    public List<Task> getAllTasks(){
    return new ArrayList<>(tasks.values());//возвращает значения tasks
    }
    public List<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());//возвращает значения tasks
    }
    public List<SubTask> getAllSubTasks(){
        return new ArrayList<>(subTasks.values());//возвращает значения tasks
    }

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

    //removeList(){}

   // getTask(){}

    public Task createTask( Task newTask){
        newTask.setId(generateId());
        tasks.put(newTask.getId(),newTask);//в мапу tasks кладем id задачи и сам task
        System.out.println(newTask.toString());
                return newTask;
    }

    public Epic createEpic( Epic epic){
        epic.setId(generateId());
        epics.put(epic.getId(),epic);//в мапу epics кладем id эпика и сам epic
        System.out.println(epic.toString());
        return epic;
    }

    public SubTask createSubTask(SubTask subTask){
        subTask.setId(generateId());
        subTasks.put(subTask.getId(),subTask);//в мапу subTasks кладем id подзадачи и subTask
        System.out.println(subTask.toString());
        return subTask;
    }


    static int generateId(){
        id++;
        return id;
    }



    //changeID(){}

   // removeTask(){}

   // getSubTasks(){}


}
