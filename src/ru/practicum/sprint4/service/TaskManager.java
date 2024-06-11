package ru.practicum.sprint4.service;

import ru.practicum.sprint4.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, SubTask> subTasks;
    private int nextId;

    public TaskManager(){
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
        nextId=0;
    }


    //--------------получение всех задач-------------
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());//возвращает значения tasks
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());//возвращает значения tasks
    }

    public List<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());//возвращает значения tasks
    }

    //-----------------очистка всех задач-------------------
    public void clearAllTasks() {
        tasks.clear();
    }

    public void clearAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    public void clearAllSubTasks() {
        //первый вариант реализации метода
        /*for(Integer id: subTasks.keySet()) {
           SubTask subTask = subTasks.get(id);
            Epic epic = epics.get(subTask.getEpicId());
            epic.getTaskIds().clear();
            epic.setTaskStatus(TaskStatus.NEW);
        }*/
        //второй вариант реализации метода
        for(Integer id: epics.keySet()) {
            Epic epic = epics.get(id);
            epic.getTaskIds().clear();
            epic.setTaskStatus(TaskStatus.NEW);
        }
        subTasks.clear();
    }

    //----------------------Создание задач----------------------//
    public void createTask(Task newTask) {
        newTask.setId(generateId());
        tasks.put(newTask.getId(), newTask);//в мапу tasks кладем id задачи и сам task
    }

    public void createEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);//в мапу epics кладем id эпика и сам epic
    }

    private void syncEpic(Epic epic) {
        if (!epics.containsKey(epic.getId())) {return;}//если в мапе нет нужного ключа,то

        ArrayList<SubTask> epicSubtasks = new ArrayList<>();

        int countNew = 0;
        int countDone = 0;

        for (int i = 0; i < epic.getTaskIds().size(); i++) {
            epicSubtasks.add(subTasks.get(epic.getTaskIds().get(i)));//из мапы subTasks достаем сабтаск по i-тому айди в списке эпика
        }
        for (SubTask subtask : epicSubtasks) {//проходимся по сабтаскам и проверяем их статус
            if (subtask.getTaskStatus() == TaskStatus.NEW) {
                countNew++;
            } else  if (subtask.getTaskStatus() == TaskStatus.DONE) {
                countDone++;
            }

            if (countNew == epicSubtasks.size()) {//исходя из проверенных статусов сабтасков выставляем статус эпика
                epic.setTaskStatus(TaskStatus.NEW);
            } else if (countDone == epicSubtasks.size()) {
                epic.setTaskStatus(TaskStatus.DONE);
            } else {
                epic.setTaskStatus(TaskStatus.IN_PROGRESS);
            }
        }
    }


    public void createSubTask(SubTask subTask) {
        subTask.setId(generateId());
        Epic epic = epics.get(subTask.getEpicId());
        if (epic != null) {
            subTasks.put(subTask.getId(), subTask);
            epic.addTaskIds(subTask.getId());
            syncEpic(epic);
        }
    }

    //------------обновление задач------
    public void update(Task newTask){
        tasks.replace(newTask.getId(),newTask);
    }

    public void update(Epic epic){
        epics.replace(epic.getId(),epic);
    }

    public void update(SubTask subTask){
        if ((subTask == null)||(!subTasks.containsKey(subTask.getId()))){
            System.out.println("subTask==null");
            return;}
        Epic epic = epics.get(subTask.getEpicId());
        if (epic==null){
            return;}
        subTasks.replace(subTask.getId(),subTask);
        syncEpic(epic);
    }

//----------------получение задач по id------------
public Task getTaskById(int nextId){
    return tasks.get(nextId);
}

    public Epic getEpicById(int nextId){
        return epics.get(nextId);
    }

    public Task getSubTaskById(int nextId){
        return subTasks.get(nextId);
    }

    //----------------удаление задач по id-------
    public void clearTaskById(int nextId){
        tasks.remove(nextId);
    }

    public void clearEpicById(int nextId){
        Epic epic=epics.get(nextId);
            for (Integer taskId : epic.getTaskIds()) {//проходимся по айди из списка айди
                subTasks.remove(taskId);
            }
            epic.getTaskIds().clear();
        epics.remove(nextId);
    }

    public void clearSubTaskById(Integer nextId){
        SubTask subTask=subTasks.get(nextId);
        Epic epic=epics.get(subTask.getEpicId());//получили эпик к которому привязан сабтаск
        epic.getTaskIds().remove(nextId);
        syncEpic(epic);
        subTasks.remove(nextId);
    }

    private int generateId(){return ++nextId;}

    public List<SubTask> getSubTaskByEpic(int nextId){
        ArrayList<SubTask> newSubTasks=new ArrayList<>();
        Epic epic = epics.get(nextId);
        for(Integer subTaskid: epic.getTaskIds()){
            newSubTasks.add(subTasks.get(subTaskid));
        }
        return newSubTasks;
    }
}
