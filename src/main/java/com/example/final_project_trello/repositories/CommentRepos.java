package com.example.final_project_trello.repositories;

import com.example.final_project_trello.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepos extends JpaRepository<Comment, Long> {
}
