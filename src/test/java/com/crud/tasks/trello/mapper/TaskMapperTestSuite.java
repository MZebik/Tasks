package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void shouldFetchEmptyList() {
        // Given
        List<Task> taskList = new ArrayList<>();
        // When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        // Then
        assertEquals(0, taskDtos.size());
        assertThat(taskDtos).isNotNull();
    }
    @Test
    void shouldReturnTaskDto() {
        // Given
        Task task = new Task(1l, "test", "test");
        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        // Then
        assertEquals(1l, taskDto.getId());
    }
    @Test
    void shouldReturnTask() {
        // Given
        TaskDto taskDto = new TaskDto(1l, "test", "test");
        // When
        Task task = taskMapper.mapToTask(taskDto);
        // Then
        assertEquals(1l, task.getId());
    }
}
