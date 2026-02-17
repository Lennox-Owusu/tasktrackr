package com.amalitech.tasktrackr;

import com.amalitech.tasktrackr.model.Task;
import com.amalitech.tasktrackr.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TaskRepositoryTest {

    @Test
    void saveTask_shouldStoreAndRetrieveTask() {
        TaskRepository repo = new TaskRepository();

        Task task = new Task();
        task.setId("123");
        task.setTitle("Test Task");
        task.setDescription("Testing repository");
        task.setStatus("OPEN");

        repo.save(task);

        assertThat(repo.findById("123")).isPresent();
        assertThat(repo.findById("123").get().getTitle()).isEqualTo("Test Task");
    }
}