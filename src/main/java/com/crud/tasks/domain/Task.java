package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedQuery(
        name = "Task.retrieveTask",
        query = "FROM tasks WHERE id = :ID"
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private long Id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;
}
