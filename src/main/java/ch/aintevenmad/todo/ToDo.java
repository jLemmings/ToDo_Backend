package ch.aintevenmad.todo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class ToDo {
    @Id
    @GeneratedValue
    private long id;
    private @NonNull
    String taskName;
    private Date dueDate;
    private String extraNote;
    private boolean taskCompleted;

    public ToDo(String taskName, Date dueDate, boolean taskCompleted) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.taskCompleted = taskCompleted;
    }
    public ToDo(String taskName, Date dueDate, String extraNote, boolean taskCompleted){
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.extraNote = extraNote;
        this.taskCompleted = taskCompleted;
    }
}
