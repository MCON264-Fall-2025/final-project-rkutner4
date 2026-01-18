package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import java.util.LinkedList;
import java.util.Stack;

public class TaskManager {

    // Use LinkedList explicitly so we can addFirst
    private final LinkedList<Task> upcoming = new LinkedList<>();
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
        Task task = completed.pop();
        upcoming.addFirst(task); // <- key fix: puts task back at the front
        return task;
    }

    public int remainingTaskCount() {
        return upcoming.size();
    }
}