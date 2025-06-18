package taskmanager;

import java.util.ArrayList;
import java.util.List;

// This class acts as the logic layer that controls all task operations
public class TaskManager {

    // List to store Task objects
    private List<Task> tasks;

    // Constructor: Initializes the task list when a TaskManager object is created
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    // Method to add a new task with the given title and description
    public void addTask(String title, String description) {
        Task task = new Task(title, description); // Create new Task object with title and description
        tasks.add(task);                         // Add it to the list
        System.out.println("Task added.");
    }

    // Method to display all tasks in a detailed format
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        System.out.println("\n--- Task List ---");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isDone() ? "[X]" : "[ ]";

            System.out.println((i + 1) + ". " + status + " " + task.getTitle());
            System.out.println("   Description: " + task.getDescription());
            System.out.println("   Created At : " + task.getCreatedAt());
        }
    }

    // Method to mark a task as done by its number (1-based index)
    public void markTaskAsDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }

        Task task = tasks.get(taskNumber - 1); // Get the task from the list
        task.markAsDone();                     // Call method to mark as done
        System.out.println("Task marked as done.");
    }

    // Method to remove a task by its number
    public void removeTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }

        tasks.remove(taskNumber - 1); // Remove task at that position
        System.out.println("Task removed.");
    }

    // Add a task loaded from file (used by TaskStorage)
    public void addExistingTask(Task task) {
        tasks.add(task);
    }

    // Getter method to return the current list of tasks (used by TaskStorage)
    public List<Task> getTasks() {
        return tasks;
    }
}
