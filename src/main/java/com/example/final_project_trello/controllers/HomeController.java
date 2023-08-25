package com.example.final_project_trello.controllers;

import com.example.final_project_trello.models.Folder;
import com.example.final_project_trello.models.Task;
import com.example.final_project_trello.models.TaskCategory;
import com.example.final_project_trello.services.CommentService;
import com.example.final_project_trello.services.FolderService;
import com.example.final_project_trello.services.TaskCategoryService;
import com.example.final_project_trello.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private FolderService folderService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TaskCategoryService taskCategoryService;
    @Autowired
    private TaskService taskService;


    @GetMapping("/folders")
    public String homePage(Model model) {
        model.addAttribute("folders", folderService.getAllFolders());
        return "folders";
    }

    @PostMapping("/addFolder")
    public String addFolder(Folder folder) {
        folderService.addFolder(folder);
        return "redirect:/folders";
    }

    @GetMapping("/folderDetails/{id}")
    public String folderDetails(@PathVariable Long id, Model model) {
        model.addAttribute("folder", folderService.getFolderById(id));
        model.addAttribute("categories", taskCategoryService.getAllTaskCategories());
        model.addAttribute("tasks", taskService.getFolderTasks(id));
        return "folderDetails";
    }

    @PostMapping("/addTask")
    public String addTask(Task task, @RequestParam Long folderId) {
        taskService.addTask(task, folderId);
        return "redirect:/folderDetails/" + folderId;
    }

    @PostMapping("/setCategoryToFolder")
    public String setCategoryToFolder(@RequestParam(name = "folder_id") Long id, @RequestParam(name = "category_id") Long categoryId, @RequestParam Long folder_id) {
        folderService.setCategories(id, taskCategoryService.getTaskCategoryById(categoryId));
        return "redirect:/folderDetails/" + folder_id;
    }

    @GetMapping("/taskDetails/{id}")
    public String taskDetails(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        model.addAttribute("comments", commentService.getTaskComments(id));
        return "taskDetails";
    }

    @PostMapping("/editTask/{id}")
    public String editTask(@PathVariable Long id, Task task) {
        taskService.editTask(task);
        return "redirect:/taskDetails/" + id;
    }

    @PostMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Long id) {
        Long folderId = taskService.getTaskById(id).getFolder().getId();
        taskService.removeTask(id);
        return "redirect:/folderDetails/" + folderId;
    }

    @PostMapping("/deleteCategories/{id}")
    public String deleteCategoryFromFolder(@PathVariable Long id, @RequestParam Long categoryId) {
        folderService.deleteCategoryFromFolder(id, categoryId);
        return "redirect:/folderDetails/" + id;
    }

    @GetMapping("/categories")
    public String categoryPage(Model model) {
        model.addAttribute("categories", taskCategoryService.getAllTaskCategories());
        return "categories";
    }

    @PostMapping("/addCategory")
    public String addCategory(TaskCategory taskCategory) {
        taskCategoryService.addTaskCategory(taskCategory);
        return "redirect:/categories";
    }

    @PostMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id) {
        taskCategoryService.removeCategory(id);
        return "redirect:/categories";
    }

    @PostMapping("/addCommentToTask")
    public String addCommentToTask(@RequestParam String comment, @RequestParam Long task) {
        commentService.addComment(comment, task);
        return "redirect:/taskDetails/" + task;
    }

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam Long commentId, @RequestParam Long taskId) {
        commentService.deleteComment(commentId, taskId);
        return "redirect:/taskDetails/" + taskId;
    }


}
