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
    void addGuest_addsGuestSuccessfully_andIncreasesCount() {
        Guest guest = new Guest("1", "Alice", "family");
        assertEquals(0, manager.getGuestCount(), "Guest count should start at 0");

        manager.addGuest(guest);
        assertEquals(1, manager.getGuestCount(), "Guest count should increase to 1 after adding");

        List<Guest> allGuests = manager.getAllGuests();
        assertEquals(1, allGuests.size());
        assertEquals("Alice", allGuests.get(0).getName());
    }

    @Test
    void removeGuestById_removesGuestSuccessfully_andDecreasesCount() {
        Guest guest = new Guest("1", "Bob", "friends");
        manager.addGuest(guest);
        assertEquals(1, manager.getGuestCount(), "Guest count should be 1 before removal");

        boolean removed = manager.removeGuestById("1");
        assertTrue(removed);
        assertEquals(0, manager.getGuestCount(), "Guest count should decrease after removal");
        assertTrue(manager.getAllGuests().isEmpty());
    }

    @Test
    void removeGuestById_returnsFalseIfGuestNotFound_andDoesNotChangeCount() {
        assertEquals(0, manager.getGuestCount(), "Guest count should start at 0");

        boolean removed = manager.removeGuestById("999");
        assertFalse(removed);
        assertEquals(0, manager.getGuestCount(), "Guest count should remain 0 when removing nonexistent guest");
    }

    @Test
    void findGuestsByName_returnsMatchingGuests_andDoesNotAffectCount() {
        Guest g1 = new Guest("1", "Carol", "family");
        Guest g2 = new Guest("2", "Carol", "friends");
        Guest g3 = new Guest("3", "Dave", "family");

        manager.addGuest(g1);
        manager.addGuest(g2);
        manager.addGuest(g3);

        assertEquals(3, manager.getGuestCount(), "Guest count should be 3 after adding");

        List<Guest> found = manager.findGuestsByName("Carol");
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(g -> g.getName().equals("Carol")));

        // Ensure count remains correct
        assertEquals(3, manager.getGuestCount(), "Guest count should remain unchanged after search");
    }

    @Test
    void findGuestsByName_returnsEmptyListIfNoMatch_andCountRemainsCorrect() {
        Guest guest = new Guest("1", "Eve", "family");
        manager.addGuest(guest);

        List<Guest> found = manager.findGuestsByName("Nonexistent");
        assertTrue(found.isEmpty());
        assertEquals(1, manager.getGuestCount(), "Guest count should remain unchanged when search yields no results");
    }

    @Test
    void getGuestCount_returnsCorrectCount_afterMultipleAddsAndRemovals() {
        assertEquals(0, manager.getGuestCount());

        manager.addGuest(new Guest("1", "Alice", "family"));
        manager.addGuest(new Guest("2", "Bob", "friends"));
        assertEquals(2, manager.getGuestCount(), "Guest count should reflect two added guests");

        manager.removeGuestById("1");
        assertEquals(1, manager.getGuestCount(), "Guest count should reflect removal of one guest");

        manager.removeGuestById("2");
        assertEquals(0, manager.getGuestCount(), "Guest count should be 0 after removing all guests");
    }

    @Test
    void getAllGuests_returnsAllAddedGuests_andCountMatches() {
        Guest g1 = new Guest("1", "Alice", "family");
        Guest g2 = new Guest("2", "Bob", "friends");
        manager.addGuest(g1);
        manager.addGuest(g2);

        List<Guest> allGuests = manager.getAllGuests();
        assertEquals(manager.getGuestCount(), allGuests.size(), "All guests list size should match guest count");
        assertTrue(allGuests.contains(g1));
        assertTrue(allGuests.contains(g2));
    }
}