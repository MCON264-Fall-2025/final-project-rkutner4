package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;

import java.util.*;

/**
 * Manages the master guest list using guest name as the unique key.
 */
public class GuestListManager {

    private final Map<String, Guest> guestsByName = new HashMap<>();

    public void addGuest(Guest guest) {
        if (guest == null) return;
        guestsByName.put(guest.getName(), guest);
    }

    public boolean removeGuest(String guestName) {
        return guestsByName.remove(guestName) != null;
    }

    public Guest findGuest(String guestName) {
        return guestsByName.get(guestName);
    }

    public int getGuestCount() {
        return guestsByName.size();
    }

    public List<Guest> getAllGuests() {
        return new ArrayList<>(guestsByName.values());
    }
}