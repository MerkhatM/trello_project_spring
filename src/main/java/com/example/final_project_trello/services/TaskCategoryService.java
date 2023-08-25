package com.example.final_project_trello.services;

import com.example.final_project_trello.models.Folder;
import com.example.final_project_trello.models.TaskCategory;
import com.example.final_project_trello.repositories.TaskCategoryRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryService {
    @Autowired
    private TaskCategoryRepos taskCategoryRepos;
    @Autowired
    private FolderService folderService;

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
        TaskCategory taskCategory=getTaskCategoryById(id);
        List<Folder> folders=folderService.getAllFolders();
        for (Folder f:
             folders) {
            f.getCategories().remove(taskCategory);
            folderService.addFolder(f);
        }
        taskCategoryRepos.deleteById(id);
    }

}
