package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuestListManagerTest {

    @Test
    void addGuest_addsGuestToList() {
        GuestListManager manager = new GuestListManager();
        Guest guest = new Guest("1", "Alice", "friends");

        manager.addGuest(guest);

        assertEquals(1, manager.getGuestCount());
        assertTrue(manager.getAllGuests().contains(guest));
    }

    @Test
    void addGuest_allowsDuplicateNames() {
        GuestListManager manager = new GuestListManager();

        manager.addGuest(new Guest("1", "Bob", "family"));
        manager.addGuest(new Guest("2", "Bob", "friends"));

        assertEquals(2, manager.getGuestCount());

        List<Guest> bobs = manager.findGuestsByName("Bob");
        assertEquals(2, bobs.size());
    }

    @Test
    void findGuestsByName_returnsAllMatchingGuests() {
        GuestListManager manager = new GuestListManager();

        manager.addGuest(new Guest("1", "Carol", "neighbors"));
        manager.addGuest(new Guest("2", "Carol", "family"));

        List<Guest> found = manager.findGuestsByName("Carol");

        assertEquals(2, found.size());
    }

    @Test
    void removeGuestById_existingGuest_returnsTrue() {
        GuestListManager manager = new GuestListManager();
        Guest guest = new Guest("1", "Dave", "friends");

        manager.addGuest(guest);

        boolean removed = manager.removeGuestById("1");

        assertTrue(removed);
        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void removeGuestById_missingGuest_returnsFalse() {
        GuestListManager manager = new GuestListManager();

        assertFalse(manager.removeGuestById("missing-id"));
    }

    @Test
    void addGuest_nullGuest_doesNothing() {
        GuestListManager manager = new GuestListManager();

        manager.addGuest(null);

        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void addGuest_sameNameDifferentGuests_areIndependent() {
        GuestListManager manager = new GuestListManager();

        Guest g1 = new Guest("1", "Alice", "family");
        Guest g2 = new Guest("2", "Alice", "friends");

        manager.addGuest(g1);
        manager.addGuest(g2);

        List<Guest> alices = manager.findGuestsByName("Alice");

        assertEquals(2, alices.size());
        assertTrue(alices.contains(g1));
        assertTrue(alices.contains(g2));
    }
}