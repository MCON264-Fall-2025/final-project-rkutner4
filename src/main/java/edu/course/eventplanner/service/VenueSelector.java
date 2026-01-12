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
        sortedVenues = new TreeSet<>(Comparator
                .comparingDouble(Venue::getCost)
                .thenComparingInt(Venue::getCapacity)
        );
        sortedVenues.addAll(venues);
    }

    public Venue selectVenue(double budget, int guestCount) {
        return sortedVenues.stream()
                .filter(v -> v.getCost() <= budget && v.getCapacity() >= guestCount)
                .findFirst()
                .orElse(null);
    }
}
