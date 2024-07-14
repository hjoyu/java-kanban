package ru.practicum.sprint4.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryList {
    private final Map<Integer,Node> history=new HashMap<>();
    private Node head;
    private Node tail;

    public void linkLast(Task task) {//добавление задачи в конец
        Node oldTail = tail;
        Node newNode = new Node(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.setNext(newNode);
        }
        int id = task.getId();
        history.put(id, newNode);
    }

    public List<Task> getTasks(){//получаем список задач
        List<Task> list = new ArrayList<>();
        Node listElement=head;
        while(listElement != null){
            list.add(listElement.getTask());
            listElement=listElement.getNext();
        }
        return list;
    }

    public void removeNode(Node node){
        Node next=node.getNext();
        Node prev=node.getPrev();
        if (prev==null){//если нет предыдущей ноды, то next нода становится в начало
            head=next;
        } else {
            prev.setNext(next);
            node.setPrev(null);
        }
        if (next==null) {//если следующая нода пустая
            tail = prev;
        } else {
            next.setPrev(prev);
            node.setNext(null);
        }
        node.setTask(null);
    }

    public Map<Integer, Node> getHistoryMap(){
        return history;
    }
}