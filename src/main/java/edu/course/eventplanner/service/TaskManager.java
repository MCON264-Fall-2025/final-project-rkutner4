package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Manages event tasks with FIFO execution and undo functionality.
 */
public class TaskManager {

    private final Queue<Task> upcoming = new LinkedList<>();
    private final Stack<Task> completed = new Stack<>();

    public void addTask(Task task) {
        if (task != null) upcoming.add(task);
    }

    public Task executeNextTask() {
        Task task = upcoming.poll();
        if (task != null) completed.push(task);
        return task;
    }

    public Task undoLastTask() {
        if (completed.isEmpty()) return null;
        Task task = completed.pop();
        upcoming.add(task); // Put undone task back into upcoming queue
        return task;
    }

    public int remainingTaskCount() {
        return upcoming.size();
    }
}