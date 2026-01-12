package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Venue;
import java.util.*;

public class VenueSelector {

    private final List<Venue> venues;

    public VenueSelector(List<Venue> venues) {
        this.venues = venues;
    }

    public Venue selectVenue(double budget, int guestCount) {
        List<Venue> validVenues = new ArrayList<>();

        // Filter venues by budget and capacity
        for (Venue venue : venues) {
            if (venue.getCost() <= budget && venue.getCapacity() >= guestCount) {
                validVenues.add(venue);
            }
        }

        if (validVenues.isEmpty()) {
            return null;
        }

        // Sort by lowest cost, then smallest sufficient capacity
        validVenues.sort((v1, v2) -> {
            int costCompare = Double.compare(v1.getCost(), v2.getCost());
            if (costCompare != 0) {
                return costCompare;
            }
            return Integer.compare(v1.getCapacity(), v2.getCapacity());
        });

        return validVenues.get(0);
    }
}