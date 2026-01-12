package edu.course.eventplanner.util;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Generators {

    /** Generates some sample venues */
    public static List<Venue> generateVenues() {
        return List.of(
                new Venue("Community Hall", 1500, 40, 5, 8),
                new Venue("Garden Hall", 2500, 60, 8, 8),
                new Venue("Grand Ballroom", 5000, 120, 15, 8)
        );
    }

    /** Generates n guests with unique IDs */
    public static List<Guest> generateGuests(int n) {
        List<Guest> guests = new ArrayList<>();
        String[] groups = {"family", "friends", "neighbors", "coworkers"};

        for (int i = 1; i <= n; i++) {
            String name = "Guest" + i;
            String group = groups[i % groups.length];
            String id = UUID.randomUUID().toString(); // unique ID
            guests.add(new Guest(name, group, id));
        }

        return guests;
    }
}