package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SeatingPlannerTest {

    @Test
    void generateSeating_groupsGuestsByGroupTag() {
        Venue venue = new Venue("Test Venue", 100, 10, 2, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("Alice", "family", "1"),
                new Guest("Bob", "family", "2"),
                new Guest("Carol", "friends", "3")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);
        assertFalse(seating.isEmpty());

        int totalSeated = seating.values().stream().mapToInt(List::size).sum();
        assertEquals(3, totalSeated);
    }

    @Test
    void generateSeating_handlesZeroTablesGracefully() {
        Venue venue = new Venue("No Tables", 50, 10, 0, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("A", "family", "1"),
                new Guest("B", "friends", "2")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertTrue(seating.isEmpty());
    }

    @Test
    void generateSeating_spreadsLargeGroupAcrossTables() {
        Venue venue = new Venue("Big Venue", 200, 100, 2, 3);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                new Guest("G1", "friends", "1"),
                new Guest("G2", "friends", "2"),
                new Guest("G3", "friends", "3"),
                new Guest("G4", "friends", "4"),
                new Guest("G5", "friends", "5")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        int totalSeated = seating.values().stream().mapToInt(List::size).sum();
        assertEquals(5, totalSeated);
        assertTrue(seating.size() > 1); // Multiple tables used
    }

    @Test
    void generateSeating_returnsEmptyMapForNoGuests() {
        Venue venue = new Venue("Empty Venue", 100, 10, 2, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        Map<Integer, List<Guest>> seating = planner.generateSeating(List.of());
        assertTrue(seating.isEmpty());
    }
}