package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;

import java.util.*;

public class GuestListManager {

    private final List<Guest> guests = new LinkedList<>();

    private final Map<Integer, Guest> guestById = new HashMap<>();
    private final Map<Guest, Integer> idByGuest = new HashMap<>();
    private final Map<String, List<Guest>> guestsByName = new HashMap<>();

    private int nextId = 1;

    public int addGuest(Guest guest) {
        if (guest == null) return -1;

        int id = nextId++;

        guests.add(guest);
        guestById.put(id, guest);
        idByGuest.put(guest, id);

        guestsByName
                .computeIfAbsent(guest.getName(), k -> new ArrayList<>())
                .add(guest);

        return id; // caller gets the ID
    }

    public boolean removeGuestById(int id) {
        Guest guest = guestById.remove(id);
        if (guest == null) return false;

        guests.remove(guest);
        idByGuest.remove(guest);
        guestsByName.get(guest.getName()).remove(guest);

        return true;
    }

    public Integer getIdForGuest(Guest guest) {
        return idByGuest.get(guest);
    }

    public int getGuestCount() {
        return guests.size();
    }

    public List<Guest> getAllGuests() {
        return new ArrayList<>(guests);
    }

    public List<Guest> findGuestsByName(String name) {
        return new ArrayList<>(guestsByName.getOrDefault(name, List.of()));
    }
}