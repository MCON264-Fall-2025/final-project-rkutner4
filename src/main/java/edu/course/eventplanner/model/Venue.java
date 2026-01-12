package edu.course.eventplanner.model;

public class Venue {
    private final String name;
    private final double cost;
    private final int capacity;
    private final int openHour;
    private final int closeHour;

    public Venue(String name, double cost, int capacity, int openHour, int closeHour) {
        this.name = name;
        this.cost = cost;
        this.capacity = capacity;
        this.openHour = openHour;
        this.closeHour = closeHour;
    }

    public String getName() { return name; }
    public double getCost() { return cost; }
    public int getCapacity() { return capacity; }
    public int getOpenHour() { return openHour; }
    public int getCloseHour() { return closeHour; }
}