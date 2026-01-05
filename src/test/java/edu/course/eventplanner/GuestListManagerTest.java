package edu.course.eventplanner;
import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.service.GuestListManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestListManagerTest {

    @Test
    void addGuest_increasesGuestCount() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Alice", "friends"));

        assertEquals(1, manager.getGuestCount());
    }

    @Test
    void addGuest_duplicateName_notAdded() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Bob", "family"));
        manager.addGuest(new Guest("Bob", "family"));

        assertEquals(1, manager.getGuestCount());
    }

    @Test
    void findGuest_existingGuest_returnsGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Carol", "neighbors"));

        Guest found = manager.findGuest("Carol");
        assertNotNull(found);
        assertEquals("Carol", found.getName());
    }

    @Test
    void removeGuest_existingGuest_returnsTrue() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Dave", "friends"));

        boolean removed = manager.removeGuest("Dave");
        assertTrue(removed);
        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void removeGuest_missingGuest_returnsFalse() {
        GuestListManager manager = new GuestListManager();

        assertFalse(manager.removeGuest("Nobody"));
    }
}