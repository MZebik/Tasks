package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    DbService service;
    @Mock
    TaskRepository repository;

    @Test
    void shouldReturnEmptyList() {
        // Given
        when(service.getAllTasks()).thenReturn(new ArrayList<>());
        // When
        List<Task> testList = service.getAllTasks();
        // Then
        assertThat(testList).isNotNull();
    }

    @Test
    void shouldReturnTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1l, "test", "test");
        when(repository.findById(any())).thenReturn(Optional.of(task));
        // When
        Task result = service.getTask(1l);
        // Then
        assertEquals(1l, result.getId());
    }

    @Test
    void shouldSaveTask() {
        //Given
        Task task = new Task(1l, "test", "test");
        when(repository.save(any(Task.class))).thenReturn(task);
        // When
        Task testTask = service.saveTask(task);
        // Then
        assertEquals("test", testTask.getTitle());
        verify(repository).save(task);
    }

    @Test
    void shouldDeleteTask() {
        //Given
        doNothing().when(repository).deleteById(any(Long.class));
        // When
        service.deleteById(1l);
        // Then
        verify(repository).deleteById(1l);
    }
}