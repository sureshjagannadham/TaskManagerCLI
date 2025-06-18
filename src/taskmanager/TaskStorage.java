package taskmanager;

import java.io.*;                         // For file I/O classes: FileReader, FileWriter, etc.
import java.time.LocalDateTime;          // For handling task creation timestamps
import java.util.*;                      // For List and ArrayList
import java.nio.file.*;                  // For checking if file exists

public class TaskStorage {

    // Name of the file where all tasks will be saved and loaded
    private static final String FILE_NAME = "tasks.txt";

    /**
     * Save a list of tasks to a file.
     * Each task is saved in a line with the following format:
     * title|description|isDone|createdAt
     */
    public static void saveTasks(List<Task> tasks) {
        try (
                // BufferedWriter improves write efficiency by reducing I/O calls
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))
        ) {
            for (Task task : tasks) {
                // Save task fields as a single line separated by "|"
                String line = task.getTitle() + "|" +
                        task.getDescription() + "|" +
                        task.isDone() + "|" +
                        task.getCreatedAt().toString(); // Format LocalDateTime to String
                writer.write(line);
                writer.newLine(); // Move to next line after each task
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage()); // Handle write failures
        }
    }

    /**
     * Load tasks from the file and return them as a list.
     * Each line in the file is expected to follow this format:
     * title|description|isDone|createdAt
     *
     * If the file doesn't exist, returns an empty list.
     */
    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>(); // Prepare list to hold loaded tasks

        // If the file doesn't exist, there's nothing to load
        if (!Files.exists(Paths.get(FILE_NAME))) {
            return tasks;
        }

        try (
                // BufferedReader allows reading the file line-by-line
                BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))
        ) {
            String line;

            // Read the file line-by-line
            while ((line = reader.readLine()) != null) {
                // Split the line by "|" to extract task fields
                String[] parts = line.split("\\|"); // Escaping "|" since it's a regex special character

                if (parts.length == 4) {
                    String title = parts[0];                   // Task title
                    String description = parts[1];             // Task description
                    boolean isDone = Boolean.parseBoolean(parts[2]); // Completion status
                    LocalDateTime createdAt = LocalDateTime.parse(parts[3]); // Parse timestamp

                    // Create the Task with full info (assumes you have this constructor)
                    Task task = new Task(title, description, isDone, createdAt);

                    // Mark task as done if flagged true (for safety)
                    if (isDone) {
                        task.markAsDone();
                    }

                    tasks.add(task); // Add task to list
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage()); // Handle read failures
        }

        return tasks; // Return the loaded list
    }
}
