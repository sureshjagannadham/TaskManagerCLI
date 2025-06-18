package taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String title;
    private String description;
    private boolean isDone;
    private LocalDateTime createdAt;

    // Constructor for new tasks (called from user input)
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isDone = false;
        this.createdAt = LocalDateTime.now(); // Set creation time to now
    }

    // Constructor used when loading from file (with custom createdAt)
    public Task(String title, String description, boolean isDone, LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.isDone = false;
        this.createdAt = createdAt;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Format createdAt in a readable way
    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createdAt.format(formatter);
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") +
                title + " - " + description +
                " (Created: " + getFormattedCreatedAt() + ")";
    }
}
