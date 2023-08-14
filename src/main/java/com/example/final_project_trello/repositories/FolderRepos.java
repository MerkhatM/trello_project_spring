package com.example.final_project_trello.repositories;

import com.example.final_project_trello.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepos extends JpaRepository<Folder, Long> {
}
