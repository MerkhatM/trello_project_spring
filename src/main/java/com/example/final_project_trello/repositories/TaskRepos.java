package com.example.final_project_trello.repositories;

import com.example.final_project_trello.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepos extends JpaRepository<Task, Long> {
}
