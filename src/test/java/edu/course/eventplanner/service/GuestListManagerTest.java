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
        Guest added = manager.findGuest("Alice");
        assertNotNull(added);
        assertEquals("Alice", added.getName());
        assertEquals("friends", added.getGroupTag());
        assertEquals("1", added.getId());
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

        // List should have 1 guest
        assertEquals(1, manager.getGuestCount());

        // Lookup should return latest guest
        Guest latest = manager.findGuest("Alice");
        assertNotNull(latest);
        assertEquals("friends", latest.getGroupTag());
        assertEquals("2", latest.getId());
    }

    @Test
    void addGuest_multipleDifferentNames_addsAll() {
        GuestListManager manager = new GuestListManager();

        Guest g1 = new Guest("Alice", "family", "1");
        Guest g2 = new Guest("Bob", "friends", "2");

        manager.addGuest(g1);
        manager.addGuest(g2);

        assertEquals(2, manager.getGuestCount());
        assertEquals("Alice", manager.findGuest("Alice").getName());
        assertEquals("Bob", manager.findGuest("Bob").getName());
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
        assertEquals("1", found.getId());
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

    @Test
    void addGuest_sameNameDifferentIds_allowsOverwrite() {
        GuestListManager manager = new GuestListManager();

        Guest g1 = new Guest("Eve", "family", "1");
        Guest g2 = new Guest("Eve", "friends", "2");
        Guest g3 = new Guest("Eve", "coworkers", "3");

        manager.addGuest(g1);
        manager.addGuest(g2);
        manager.addGuest(g3);

        // List should only have 1 guest (latest)
        assertEquals(1, manager.getGuestCount());

        Guest latest = manager.findGuest("Eve");
        assertEquals("coworkers", latest.getGroupTag());
        assertEquals("3", latest.getId());
    }

    @Test
    void removeGuest_removesAllDuplicatesByName() {
        GuestListManager manager = new GuestListManager();

        Guest g1 = new Guest("Frank", "family", "1");
        Guest g2 = new Guest("Frank", "friends", "2");

        manager.addGuest(g1);
        manager.addGuest(g2);

        assertEquals(1, manager.getGuestCount()); // Only latest is kept
        boolean removed = manager.removeGuest("Frank");
        assertTrue(removed);
        assertEquals(0, manager.getGuestCount());
        assertNull(manager.findGuest("Frank"));
    }
}
