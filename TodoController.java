package com.example.todo.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
public class TodoController {

    private final TodoRepository repo;

    public TodoController(TodoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Todo> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return repo.save(todo);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo updated) {
        Todo todo = repo.findById(id).orElseThrow();
        todo.setTitle(updated.getTitle());
        todo.setDescription(updated.getDescription());
        todo.setCompleted(updated.isCompleted());
        return repo.save(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}