package com.example.final_project_trello.services;

import com.example.final_project_trello.models.Comment;
import com.example.final_project_trello.repositories.CommentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepos commentRepos;

    @Autowired
    private TaskService taskService;

    public List<Comment> getTaskComments(Long task_id) {
        return commentRepos.findAll().stream()
                .filter(comment -> comment.getTask().getId() == task_id).toList();
    }

    public void addComment(String comment, Long task_id) {
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setTask(taskService.getTaskById(task_id));
        commentRepos.save(comment1);
    }

    public void deleteComment(Long commentId, Long taskId) {
        Comment comment = commentRepos.findById(commentId).orElse(null);
        if (comment == null)
            return;
        if (comment.getTask().getId() == taskId) {
            comment.setTask(null);
            commentRepos.delete(comment);
        }
    }

}
