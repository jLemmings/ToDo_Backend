package ch.aintevenmad.todo;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ToDoController {
    private ToDoRepository repository;

    public ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todos")
    List<ToDo> all() {
        return repository.findAll();
    }

    @PostMapping("/todos")
    ToDo newToDo(@RequestBody ToDo newToDo) {
        return repository.save(newToDo);
    }

    @GetMapping("/todos/{id}")
    ToDo one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException(id));
    }

    @PutMapping("/todos/{id}")
    ToDo replaceToDo(@RequestBody ToDo newToDo, @PathVariable Long id) {

        return repository.findById(id)
                .map(toDo -> {
                    toDo.setTaskName(newToDo.getTaskName());
                    toDo.setDueDate(newToDo.getDueDate());
                    toDo.setExtraNote(newToDo.getExtraNote());
                    toDo.setTaskCompleted(newToDo.getTaskCompleted());
                    return repository.save(toDo);
                })
                .orElseGet(() -> {
                    newToDo.setId(id);
                    return repository.save(newToDo);
                });
    }

    @DeleteMapping("/todos/{id}")
    void deleteToDo(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/deleteall")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteAll() {
        repository.deleteAll();
    }

    @GetMapping("/init")
    @CrossOrigin(origins = "http://localhots:4200")
    public void createDefaults() {
        Date date = new Date();
        repository.save(new ToDo("PMB", date, false));
        repository.save(new ToDo("GMDU", date, false));
        repository.save(new ToDo("INMA", date, true));
        repository.save(new ToDo("SLGP", date, false));
    }


}