package edu.course.eventplanner.model;

public class Guest {
    private static int nextId = 1;

    private final String id;
    private final String name;
    private final String groupTag;

    public Guest(String name, String groupTag) {
        this(String.valueOf(nextId++), name, groupTag); // generate ID and delegate
    }

    public Guest(String id, String name, String groupTag) {
        this.id = id;
        this.name = name;
        this.groupTag = groupTag;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getGroupTag() { return groupTag; }
}