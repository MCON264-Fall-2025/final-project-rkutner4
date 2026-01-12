package edu.course.eventplanner.service;

import edu.course.eventplanner.model.*;
import java.util.*;

public class SeatingPlanner {

    private final Venue venue;

    public SeatingPlanner(Venue venue) {
        this.venue = venue;
    }

    public Map<Integer, List<Guest>> generateSeating(List<Guest> guests) {
        // Group guests by groupTag using Map<String, Queue<Guest>>
        Map<String, Queue<Guest>> groupedGuests = new HashMap<>();

        for (Guest guest : guests) {
            groupedGuests
                    .computeIfAbsent(guest.getGroupTag(), k -> new LinkedList<>())
                    .offer(guest);
        }

        // TreeMap acts as a BST for ordered table numbers
        Map<Integer, List<Guest>> seatingChart = new TreeMap<>();

        int tableNumber = 1;
        final int TABLE_SIZE = 8;

        // Seat each group fairly using queues
        for (Queue<Guest> queue : groupedGuests.values()) {
            while (!queue.isEmpty()) {
                seatingChart.putIfAbsent(tableNumber, new ArrayList<>());
                List<Guest> table = seatingChart.get(tableNumber);

                while (table.size() < TABLE_SIZE && !queue.isEmpty()) {
                    table.add(queue.poll());
                }

                if (table.size() == TABLE_SIZE) {
                    tableNumber++;
                }
            }
        }

        return seatingChart;
    }
}