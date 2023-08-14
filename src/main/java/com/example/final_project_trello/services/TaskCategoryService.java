package com.example.final_project_trello.services;

import com.example.final_project_trello.models.TaskCategory;
import com.example.final_project_trello.repositories.TaskCategoryRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryService {
    @Autowired
    private TaskCategoryRepos taskCategoryRepos;

    public List<TaskCategory> getAllTaskCategories() {
        return taskCategoryRepos.findAll();
    }

    public TaskCategory getTaskCategoryById(Long id) {
        return taskCategoryRepos.findById(id).orElse(null);
    }

    public void addTaskCategory(TaskCategory taskCategory) {
        taskCategoryRepos.save(taskCategory);
    }

    public void removeCategory(Long id) {
        List<TaskCategory> taskCategories = getAllTaskCategories();
        taskCategories.remove(getTaskCategoryById(id));
        taskCategoryRepos.saveAll(taskCategories);
    }

}
