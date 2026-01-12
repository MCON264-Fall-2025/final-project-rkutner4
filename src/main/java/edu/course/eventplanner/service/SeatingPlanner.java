package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;

import java.util.*;

public class SeatingPlanner {

    private final Venue venue;
    private static final int DEFAULT_TABLE_SIZE = 8;

    public SeatingPlanner(Venue venue) {
        this.venue = venue;
    }

    /**
     * Generates a seating chart for the given list of guests.
     * Groups guests by groupTag and seats them fairly across tables.
     *
     * @param guests list of guests to seat
     * @return Map of tableNumber -> List of Guests at that table
     */
    public Map<Integer, List<Guest>> generateSeating(List<Guest> guests) {
        Map<String, Queue<Guest>> groupedGuests = new HashMap<>();

        // Group guests by their groupTag
        for (Guest guest : guests) {
            groupedGuests
                    .computeIfAbsent(guest.getGroupTag(), k -> new LinkedList<>())
                    .offer(guest);
        }

        Map<Integer, List<Guest>> seatingChart = new TreeMap<>();
        int tableNumber = 1;
        final int tableSize = DEFAULT_TABLE_SIZE;

        // Seat guests from each group fairly
        for (Queue<Guest> queue : groupedGuests.values()) {
            while (!queue.isEmpty()) {
                seatingChart.putIfAbsent(tableNumber, new ArrayList<>());
                List<Guest> table = seatingChart.get(tableNumber);

                while (table.size() < tableSize && !queue.isEmpty()) {
                    table.add(queue.poll());
                }

                if (table.size() == tableSize) {
                    tableNumber++;
                }
            }
        }

        return seatingChart;
    }
}