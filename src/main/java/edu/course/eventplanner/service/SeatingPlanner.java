package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;

import java.util.*;

/**
 * Generates a seating chart for a venue.
 * Groups guests by groupTag and seats them sequentially across tables.
 */
public class SeatingPlanner {

    private final Venue venue;

    public SeatingPlanner(Venue venue) {
        this.venue = venue;
    }

    public Map<Integer, List<Guest>> generateSeating(List<Guest> guests) {
        if (guests == null || guests.isEmpty() || venue.getTables() <= 0) {
            return Map.of(); // Gracefully handle no guests or zero tables
        }

        // Group guests by groupTag using FIFO queues
        Map<String, Queue<Guest>> groupedGuests = new HashMap<>();
        for (Guest guest : guests) {
            groupedGuests.computeIfAbsent(guest.getGroupTag(), k -> new LinkedList<>()).add(guest);
        }

        Map<Integer, List<Guest>> seating = new TreeMap<>();
        int tableNum = 1;
        int seatsPerTable = venue.getSeatsPerTable();

        // Fill tables sequentially
        while (!groupedGuests.isEmpty()) {
            List<Guest> table = new ArrayList<>();
            Iterator<Map.Entry<String, Queue<Guest>>> it = groupedGuests.entrySet().iterator();

            while (it.hasNext() && table.size() < seatsPerTable) {
                Map.Entry<String, Queue<Guest>> entry = it.next();
                Queue<Guest> queue = entry.getValue();

                while (!queue.isEmpty() && table.size() < seatsPerTable) {
                    table.add(queue.poll());
                }

                if (queue.isEmpty()) it.remove();
            }

            seating.put(tableNum++, table);
        }

        return seating;
    }
}