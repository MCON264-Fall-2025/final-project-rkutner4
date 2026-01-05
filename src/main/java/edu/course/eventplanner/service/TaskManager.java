package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import java.util.*;

public class TaskManager {

    private final Queue<Task> upcoming = new LinkedList<>();
    private final Stack<Task> completed = new Stack<>();

    public void addTask(Task task) {
        if (task != null) {
            upcoming.offer(task);
        }
    }

    public Task executeNextTask() {
        Task task = upcoming.poll();
        if (task != null) {
            completed.push(task);
        }
        return task;
    }

    public Task undoLastTask() {
        if (completed.isEmpty()) {
            return null;
        }
        return completed.pop();
    }

    public int remainingTaskCount() {
        return upcoming.size();
    }
}
