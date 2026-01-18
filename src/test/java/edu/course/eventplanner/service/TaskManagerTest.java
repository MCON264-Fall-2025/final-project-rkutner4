package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
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
    void undoLastTask_returnsLastCompletedTask_andRestoresItToQueue() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Prepare food");

        manager.addTask(task);
        manager.executeNextTask();

        // Undo the last task
        Task undone = manager.undoLastTask();
        assertEquals(task, undone);

        // Now it should be back in the upcoming queue
        assertEquals(1, manager.remainingTaskCount());

        // Executing again should give the same task
        Task executedAgain = manager.executeNextTask();
        assertEquals(task, executedAgain);
        assertEquals(0, manager.remainingTaskCount());
    }

    @Test
    void undoLastTask_whenNoneExecuted_returnsNull() {
        TaskManager manager = new TaskManager();
        assertNull(manager.undoLastTask());
    }

    @Test
    void remainingTaskCount_decreasesAfterExecution() {
        TaskManager manager = new TaskManager();
        manager.addTask(new Task("Decorate"));
        manager.addTask(new Task("Setup chairs"));

        manager.executeNextTask();

        assertEquals(1, manager.remainingTaskCount());
    }

    @Test
    void executeNextTask_returnsNullWhenNoTasksExist() {
        TaskManager manager = new TaskManager();
        assertNull(manager.executeNextTask());
    }

    @Test
    void undoAfterExecute_restoresLastTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Decorate");

        manager.addTask(task);
        manager.executeNextTask();

        Task undone = manager.undoLastTask();
        assertEquals(task, undone);

        // Verify it goes back to the queue
        Task next = manager.executeNextTask();
        assertEquals(task, next);
    }
}