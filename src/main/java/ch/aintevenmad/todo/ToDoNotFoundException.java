package ch.aintevenmad.todo;

public class ToDoNotFoundException extends RuntimeException {
    ToDoNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
