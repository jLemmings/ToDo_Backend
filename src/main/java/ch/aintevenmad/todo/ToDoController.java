package ch.aintevenmad.todo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@RestController
public class ToDoController {
    private ToDoRepository repository;

    public ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todo")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<ToDo> allToDo() {
        return new ArrayList<>(repository.findAll());
    }

    @GetMapping("/first")
    @CrossOrigin(origins = "http://localhost:4200")
    public ToDo firstToDo() {
        return repository.findAll().get(0);
    }

    @GetMapping("/delete")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteToDo(ToDo toDo) {
        repository.delete(toDo);
    }

    @GetMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ToDo addToDO(String taskName, String extraNote) {
        Date date = new Date();
        ToDo toDo = new ToDo(taskName, date, extraNote, false);
        repository.save(toDo);
        return toDo;
    }

    @GetMapping("/countcompletedtasks")
    @CrossOrigin(origins = "http://localhost:4200")
    public int countCompletedTasks() {
        return repository.findByTaskCompletedIsTrue().size();
    }

    @GetMapping("/completedtasks")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<ToDo> completedTasks() {
        return repository.completedTasks();
    }

    @GetMapping("/incompletetasks")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<ToDo> uncompletedTasks() {
        return repository.findByTaskCompletedIsFalse();
    }

    @GetMapping("/deleteall")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteAll() {
        repository.deleteAll();
    }

    @GetMapping("/loaddefaults")
    @CrossOrigin(origins = "http://localhots:4200")
    public void createDefaults() {
        Date date = new Date();
        repository.save(new ToDo("PMB", date, false));
        repository.save(new ToDo("GMDU", date, false));
        repository.save(new ToDo("INMA", date, true));
        repository.save(new ToDo("SLGP", date, false));
    }

    @GetMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteByID(@PathVariable long id){
        repository.deleteById(id);
    }


}