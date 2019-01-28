package ch.aintevenmad.todo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class ToDo {
    @Id
    @GeneratedValue
    private long id;
    private @NonNull
    String taskName;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dueDate;
    private String extraNote;
    private boolean taskCompleted;

    public ToDo(String taskName, boolean taskCompleted) {
        this.taskName = taskName;
        this.taskCompleted = taskCompleted;
    }

    public ToDo(String taskName, LocalDate dueDate, boolean taskCompleted) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.taskCompleted = taskCompleted;
    }
    public ToDo(String taskName, LocalDate dueDate, String extraNote, boolean taskCompleted){
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.extraNote = extraNote;
        this.taskCompleted = taskCompleted;
    }

    public boolean getTaskCompleted() {
        return taskCompleted;
    }
}
