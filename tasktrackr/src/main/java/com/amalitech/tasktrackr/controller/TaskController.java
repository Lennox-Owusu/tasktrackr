package com.amalitech.tasktrackr.controller;

import com.amalitech.tasktrackr.model.Task;
import com.amalitech.tasktrackr.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        String id = UUID.randomUUID().toString();
        task.setId(id);
        task.setStatus("OPEN");

        repository.save(task);

        log.info("Created task with ID: {}", id);
        return task;
    }

    @GetMapping
    public Iterable<Task> getAllTasks() {
        log.info("Fetching all tasks");
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        log.info("Updating task with ID: {}", id);   // <-- NEW LOG LINE

        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedTask.getTitle());
                    existing.setDescription(updatedTask.getDescription());
                    repository.save(existing);
                    return existing;
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String id) {
        log.info("Deleting task with ID: {}", id);   // <-- NEW LOG LINE

        if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Task not found");
        }
        repository.delete(id);
    }
}