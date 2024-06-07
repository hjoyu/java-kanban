import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{
     List<Integer> taskIds = new ArrayList<>();

    public Epic(String name, String description,TaskStatus status) {
        super(name, description,status);
    }

    /*public Epic(int id, String name, String description,TaskStatus status) {
        super(id, name, description,status);
    }*/
}
