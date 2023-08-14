package com.example.final_project_trello.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "folders")
@Getter
@Setter
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @ManyToMany
    Set<TaskCategory> categories; // Many To Many
}
