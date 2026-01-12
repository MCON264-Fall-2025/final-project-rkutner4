package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Venue;

import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class VenueSelector {

    private final NavigableSet<Venue> sortedVenues;

    public VenueSelector(List<Venue> venues) {
        // Sort by cost ascending, then capacity ascending
        Comparator<Venue> comparator = Comparator
                .comparingDouble(Venue::getCost)
                .thenComparingInt(Venue::getCapacity);

        sortedVenues = new TreeSet<>(comparator);
        sortedVenues.addAll(venues);
    }

    /**
     * Selects the cheapest venue that can hold at least minCapacity
     * and is within the maxCost budget.
     *
     * @param maxCost maximum allowed cost
     * @param minCapacity minimum required capacity
     * @return best venue, or null if none found
     */
    public Venue selectVenue(double maxCost, int minCapacity) {
        for (Venue venue : sortedVenues) {
            if (venue.getCost() <= maxCost && venue.getCapacity() >= minCapacity) {
                return venue;
            }
        }
        return null;
    }
}