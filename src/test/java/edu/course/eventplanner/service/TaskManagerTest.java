package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    @Test
    void executeNextTask_movesTaskToCompleted() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Do something");
        manager.addTask(task);

        Task executed = manager.executeNextTask();
        assertEquals(task, executed);
        assertEquals(0, manager.remainingTaskCount());
    }

    @Test
    void undoLastTask_returnsTaskToQueue() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Task 1");
        manager.addTask(task);

        manager.executeNextTask();
        Task undone = manager.undoLastTask();

        assertEquals(task, undone);
        assertEquals(1, manager.remainingTaskCount());
    }

    @Test
    void undoLastTask_returnsNullWhenNothingCompleted() {
        TaskManager manager = new TaskManager();
        assertNull(manager.undoLastTask());
    }

    @Test
    void addTask_nullTask_doesNothing() {
        TaskManager manager = new TaskManager();
        manager.addTask(null);
        assertEquals(0, manager.remainingTaskCount());
    }
}
