package com.example.final_project_trello.repositories;

import com.example.final_project_trello.models.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepos extends JpaRepository<TaskCategory, Long> {
}
