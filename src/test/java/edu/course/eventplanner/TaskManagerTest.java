package edu.course.eventplanner;

import edu.course.eventplanner.model.Task;
import edu.course.eventplanner.service.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    @Test
    void addTask_increasesRemainingTaskCount() {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("Decorate"));

        assertEquals(1, manager.remainingTaskCount());
    }

    @Test
    void executeNextTask_returnsTaskAndRemovesFromQueue() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Set up chairs");

        manager.addTask(task);
        Task executed = manager.executeNextTask();

        assertEquals(task, executed);
        assertEquals(0, manager.remainingTaskCount());
    }

    @Test
    void undoLastTask_returnsLastCompletedTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Prepare food");

        manager.addTask(task);
        manager.executeNextTask();

        Task undone = manager.undoLastTask();
        assertEquals(task, undone);
    }

    @Test
    void undoLastTask_whenNoneExecuted_returnsNull() {
        TaskManager manager = new TaskManager();

        assertNull(manager.undoLastTask());
    }
}
