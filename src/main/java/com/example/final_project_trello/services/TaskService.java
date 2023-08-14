package com.example.final_project_trello.services;

import com.example.final_project_trello.models.Task;
import com.example.final_project_trello.repositories.TaskRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepos taskRepos;
    @Autowired
    private FolderService folderService;

    public List<Task> getAllTasks() {
        return taskRepos.findAll();
    }

    public void addTask(Task task, Long folderId) {
        task.setStatus(0);
        task.setFolder(folderService.getFolderById(folderId));
        taskRepos.save(task);
    }

    public void editTask(Task task) {
        taskRepos.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepos.findById(id).orElse(null);
    }

    public void removeTask(Long id) {
        taskRepos.deleteById(id);
    }

    public List<Task> getFolderTasks(Long folderId) {
        List<Task> tasks = getAllTasks();
        return tasks.stream().filter(task -> task.getFolder().getId() == folderId).toList();
    }
}
