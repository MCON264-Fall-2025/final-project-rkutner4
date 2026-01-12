package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GuestListManager {

    private final List<Guest> guests = new LinkedList<>();
    private final Map<String, Guest> guestById = new HashMap<>();
    private final Map<String, List<Guest>> guestsByName = new HashMap<>();

    public void addGuest(Guest guest) {
        if (guest == null) return;  // <--- Null safety check

        guests.add(guest);
        guestById.put(guest.getId(), guest);
        guestsByName
                .computeIfAbsent(guest.getName(), k -> new ArrayList<>())
                .add(guest);
    }

    public boolean removeGuestById(String id) {
        Guest guest = guestById.remove(id);
        if (guest == null) return false;

        guests.remove(guest);
        guestsByName.get(guest.getName()).remove(guest);
        return true;
    }
    public int getGuestCount() {
        return guests.size();
    }

    public List<Guest> getAllGuests() {
        return new ArrayList<>(guests); // return a copy to avoid external modification
    }

    public List<Guest> findGuestsByName(String name) {
        return new ArrayList<>(guestsByName.getOrDefault(name, new ArrayList<>()));
    }
}