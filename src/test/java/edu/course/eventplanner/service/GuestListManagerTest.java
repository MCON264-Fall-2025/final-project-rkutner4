package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuestListManagerTest {

    @Test
    void addGuest_increasesGuestCount() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Alice", "friends", "1"));

        assertEquals(1, manager.getGuestCount());
    }

    @Test
    void addGuest_nullGuest_doesNothing() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(null);

        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void addGuest_duplicateName_overwritesLookupWithoutIncreasingCount() {
        GuestListManager manager = new GuestListManager();

        Guest g1 = new Guest("Alice", "family", "1");
        Guest g2 = new Guest("Alice", "friends", "2");

        manager.addGuest(g1);
        manager.addGuest(g2);

        assertEquals(1, manager.getGuestCount());
        // Lookup should return latest guest
        assertEquals("friends", manager.findGuest("Alice").getGroupTag());
        assertEquals("2", manager.findGuest("Alice").getId());
    }

    @Test
    void removeGuest_existingGuest_returnsTrue() {
        GuestListManager manager = new GuestListManager();
        Guest g = new Guest("Bob", "family", "1");
        manager.addGuest(g);

        boolean removed = manager.removeGuest("Bob");

        assertTrue(removed);
        assertEquals(0, manager.getGuestCount());
        assertNull(manager.findGuest("Bob"));
    }

    @Test
    void removeGuest_missingGuest_returnsFalse() {
        GuestListManager manager = new GuestListManager();
        assertFalse(manager.removeGuest("Nonexistent"));
    }

    @Test
    void findGuest_existingGuest_returnsGuest() {
        GuestListManager manager = new GuestListManager();
        Guest g = new Guest("Carol", "neighbors", "1");
        manager.addGuest(g);

        Guest found = manager.findGuest("Carol");
        assertNotNull(found);
        assertEquals("Carol", found.getName());
        assertEquals("neighbors", found.getGroupTag());
    }

    @Test
    void getAllGuests_returnsCopy() {
        GuestListManager manager = new GuestListManager();
        Guest g = new Guest("Dan", "coworkers", "1");
        manager.addGuest(g);

        List<Guest> all = manager.getAllGuests();
        assertEquals(1, all.size());

        // Modifying returned list should not affect original
        all.clear();
        assertEquals(1, manager.getGuestCount());
    }
}
