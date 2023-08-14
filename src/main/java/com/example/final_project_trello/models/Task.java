package com.example.final_project_trello.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String description; // TEXT

    int status; // 0 - todo, 1 - intest, 2 - done, 3 - failed

    @ManyToOne
    Folder folder; // Many To One
}
