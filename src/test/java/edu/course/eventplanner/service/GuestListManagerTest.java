package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuestListManagerTest {

    private GuestListManager manager;

    @BeforeEach
    void setup() {
        manager = new GuestListManager();
    }

    @Test
    void addGuest_addsGuestSuccessfully() {
        Guest guest = new Guest("1", "Alice", "family");
        manager.addGuest(guest);

        List<Guest> allGuests = manager.getAllGuests();
        assertEquals(1, allGuests.size());
        assertEquals("Alice", allGuests.get(0).getName());
    }

    @Test
    void removeGuestById_removesGuestSuccessfully() {
        Guest guest = new Guest("1", "Bob", "friends");
        manager.addGuest(guest);

        boolean removed = manager.removeGuestById("1");
        assertTrue(removed);
        assertTrue(manager.getAllGuests().isEmpty());
    }

    @Test
    void removeGuestById_returnsFalseIfGuestNotFound() {
        boolean removed = manager.removeGuestById("999");
        assertFalse(removed);
    }

    @Test
    void findGuestsByName_returnsMatchingGuests() {
        Guest g1 = new Guest("1", "Carol", "family");
        Guest g2 = new Guest("2", "Carol", "friends");
        Guest g3 = new Guest("3", "Dave", "family");

        manager.addGuest(g1);
        manager.addGuest(g2);
        manager.addGuest(g3);

        List<Guest> found = manager.findGuestsByName("Carol");
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(g -> g.getName().equals("Carol")));
    }

    @Test
    void findGuestsByName_returnsEmptyListIfNoMatch() {
        Guest guest = new Guest("1", "Eve", "family");
        manager.addGuest(guest);

        List<Guest> found = manager.findGuestsByName("Nonexistent");
        assertTrue(found.isEmpty());
    }

    @Test
    void getGuestCount_returnsCorrectCount() {
        manager.addGuest(new Guest("1", "Alice", "family"));
        manager.addGuest(new Guest("2", "Bob", "friends"));
        assertEquals(2, manager.getGuestCount());
    }

    @Test
    void getAllGuests_returnsAllAddedGuests() {
        Guest g1 = new Guest("1", "Alice", "family");
        Guest g2 = new Guest("2", "Bob", "friends");
        manager.addGuest(g1);
        manager.addGuest(g2);

        List<Guest> allGuests = manager.getAllGuests();
        assertEquals(2, allGuests.size());
        assertTrue(allGuests.contains(g1));
        assertTrue(allGuests.contains(g2));
    }
}