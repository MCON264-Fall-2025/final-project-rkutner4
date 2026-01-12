package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;

import java.util.*;

/**
 * Manages the master guest list and fast lookup by name.
 * Uses:
 * - LinkedList to maintain order of guests
 * - HashMap for O(1) lookup by name
 */
public class GuestListManager {

    private final LinkedList<Guest> guests = new LinkedList<>();
    private final Map<String, Guest> guestByName = new HashMap<>();

    public void addGuest(Guest guest) {
        if (guest == null) return; // Null-safety

        // If guest with same name exists, overwrite lookup but keep original list entry
        if (!guestByName.containsKey(guest.getName())) {
            guests.add(guest);
        }
        guestByName.put(guest.getName(), guest);
    }

    public boolean removeGuest(String guestName) {
        Guest guest = guestByName.remove(guestName);
        if (guest == null) return false;

        guests.remove(guest); // Remove from master list
        return true;
    }

    public Guest findGuest(String guestName) {
        return guestByName.get(guestName);
    }

    public int getGuestCount() {
        return guests.size();
    }

    public List<Guest> getAllGuests() {
        return new ArrayList<>(guests); // Return copy to prevent external modification
    }
}