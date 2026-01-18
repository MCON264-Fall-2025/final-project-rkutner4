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
        Guest guest = new Guest("Alice", "family");
        assertEquals(0, manager.getGuestCount());

        int id = manager.addGuest(guest);

        assertEquals(1, manager.getGuestCount());
        assertTrue(id > 0);

        List<Guest> allGuests = manager.getAllGuests();
        assertEquals(1, allGuests.size());
        assertEquals("Alice", allGuests.get(0).getName());
    }

    @Test
    void removeGuestById_removesGuestSuccessfully_andDecreasesCount() {
        Guest guest = new Guest("Bob", "friends");
        int id = manager.addGuest(guest);

        assertEquals(1, manager.getGuestCount());

        boolean removed = manager.removeGuestById(id);
        assertTrue(removed);
        assertEquals(0, manager.getGuestCount());
        assertTrue(manager.getAllGuests().isEmpty());
    }

    @Test
    void removeGuestById_returnsFalseIfGuestNotFound_andDoesNotChangeCount() {
        assertEquals(0, manager.getGuestCount());

        boolean removed = manager.removeGuestById(999);
        assertFalse(removed);
        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void findGuestsByName_returnsMatchingGuests_andDoesNotAffectCount() {
        manager.addGuest(new Guest("Carol", "family"));
        manager.addGuest(new Guest("Carol", "friends"));
        manager.addGuest(new Guest("Dave", "family"));

        assertEquals(3, manager.getGuestCount());

        List<Guest> found = manager.findGuestsByName("Carol");
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(g -> g.getName().equals("Carol")));

        assertEquals(3, manager.getGuestCount());
    }

    @Test
    void findGuestsByName_returnsEmptyListIfNoMatch_andCountRemainsCorrect() {
        manager.addGuest(new Guest("Eve", "family"));

        List<Guest> found = manager.findGuestsByName("Nonexistent");
        assertTrue(found.isEmpty());
        assertEquals(1, manager.getGuestCount());
    }

    @Test
    void getGuestCount_returnsCorrectCount_afterMultipleAddsAndRemovals() {
        int id1 = manager.addGuest(new Guest("Alice", "family"));
        int id2 = manager.addGuest(new Guest("Bob", "friends"));

        assertEquals(2, manager.getGuestCount());

        manager.removeGuestById(id1);
        assertEquals(1, manager.getGuestCount());

        manager.removeGuestById(id2);
        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void getAllGuests_returnsAllAddedGuests_andCountMatches() {
        Guest g1 = new Guest("Alice", "family");
        Guest g2 = new Guest("Bob", "friends");

        manager.addGuest(g1);
        manager.addGuest(g2);

        List<Guest> allGuests = manager.getAllGuests();
        assertEquals(manager.getGuestCount(), allGuests.size());
        assertTrue(allGuests.contains(g1));
        assertTrue(allGuests.contains(g2));
    }
}