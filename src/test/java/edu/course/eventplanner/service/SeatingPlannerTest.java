package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SeatingPlannerTest {

    // Helper to create guests quickly
    private Guest guest(String id, String name, String group) {
        return new Guest(id, name, group);
    }

    @Test
    void generateSeating_groupsGuestsByGroupTag() {
        Venue venue = new Venue("Test Venue", 100.0, 50, 10, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                guest("1", "Alice", "family"),
                guest("2", "Bob", "family"),
                guest("3", "Carol", "friends")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertFalse(seating.isEmpty());

        int totalSeated = seating.values().stream()
                .mapToInt(List::size)
                .sum();

        assertEquals(3, totalSeated);
    }

    @Test
    void generateSeating_spreadsLargeGroupAcrossTables() {
        Venue venue = new Venue("Big Venue", 200.0, 100, 15, 8);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                guest("1", "G1", "friends"),
                guest("2", "G2", "friends"),
                guest("3", "G3", "friends"),
                guest("4", "G4", "friends"),
                guest("5", "G5", "friends"),
                guest("6", "G6", "friends"),
                guest("7", "G7", "friends"),
                guest("8", "G8", "friends"),
                guest("9", "G9", "friends")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        // Should spread across at least 2 tables
        assertTrue(seating.size() >= 2);
    }

    @Test
    void generateSeating_seatsAllGuests() {
        Venue venue = new Venue("Test Venue", 100, 20, 2, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                guest("1", "A", "family"),
                guest("2", "B", "family"),
                guest("3", "C", "family"),
                guest("4", "D", "family"),
                guest("5", "E", "family"),
                guest("6", "F", "family")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        int totalSeated = seating.values().stream()
                .mapToInt(List::size)
                .sum();

        assertEquals(6, totalSeated);
    }

    @Test
    void generateSeating_returnsEmptyMapForNoGuests() {
        Venue venue = new Venue("Empty Venue", 100, 10, 2, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        Map<Integer, List<Guest>> seating = planner.generateSeating(List.of());

        assertTrue(seating.isEmpty());
    }

    @Test
    void generateSeating_withZeroTables_stillSeatsGuests() {
        Venue venue = new Venue("No Tables", 100, 10, 0, 5);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                guest("1", "A", "family"),
                guest("2", "B", "friends")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertFalse(seating.isEmpty());
        assertEquals(2, seating.get(1).size());
    }

    @Test
    void generateSeating_handlesMultipleGroupTags() {
        Venue venue = new Venue("Mixed Groups", 100, 20, 4, 2);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = List.of(
                guest("1", "A", "family"),
                guest("2", "B", "friends"),
                guest("3", "C", "family"),
                guest("4", "D", "friends")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        int totalSeated = seating.values().stream()
                .mapToInt(List::size)
                .sum();

        assertEquals(4, totalSeated);
    }
}