package com.example.final_project_trello.services;

import com.example.final_project_trello.models.Folder;
import com.example.final_project_trello.models.TaskCategory;
import com.example.final_project_trello.repositories.FolderRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FolderService {
    @Autowired
    private FolderRepos folderRepos;
    @Autowired
    private TaskCategoryService taskCategoryService;

    public List<Folder> getAllFolders() {
        return folderRepos.findAll();
    }

    public void addFolder(Folder folder) {
        folderRepos.save(folder);
    }

    public Folder getFolderById(Long id) {
        return folderRepos.findById(id).orElse(null);
    }

    public void setCategories(Long id, TaskCategory category) {
        Folder folder = getFolderById(id);
        Set<TaskCategory> categories = folder.getCategories();
        categories.add(category);
        folder.setCategories(categories);
        addFolder(folder);
    }

    public void deleteCategoryFromFolder(Long folderId, Long taskCategory) {
        Folder folder = getFolderById(folderId);
        if(folder==null)
            return;
        Set<TaskCategory> taskCategories = folder.getCategories();
        Set<TaskCategory> newCategories=new HashSet<>();
        for(TaskCategory category:taskCategories){
            if(category.getId()!=taskCategory){
                newCategories.add(category);
            }
        }
        folder.setCategories(newCategories);
        folderRepos.save(folder);
    }
}
