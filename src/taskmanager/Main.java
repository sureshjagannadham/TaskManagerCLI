package taskmanager;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        // Load previously saved tasks
        List<Task> savedTasks = TaskStorage.loadTasks();
        for (Task task : savedTasks) {
            taskManager.addExistingTask(task); // Restore each task to the list
        }

        while (true) {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(title, description);
                    break;

                case 2:
                    taskManager.viewTasks();
                    break;

                case 3:
                    System.out.print("Enter task number to mark as done: ");
                    int doneNumber = Integer.parseInt(scanner.nextLine());
                    taskManager.markTaskAsDone(doneNumber);
                    break;

                case 4:
                    System.out.print("Enter task number to remove: ");
                    int removeNumber = Integer.parseInt(scanner.nextLine());
                    taskManager.removeTask(removeNumber);
                    break;

                case 5:
                    // Save tasks before exiting
                    TaskStorage.saveTasks(taskManager.getTasks());
                    System.out.println("Tasks saved. Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
