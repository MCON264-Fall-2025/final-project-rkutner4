package edu.course.eventplanner.model;

public class Guest {
    private final String id;
    private final String name;
    private final String groupTag;

    public Guest(String name, String groupTag) {
        this.id = null;
        this.name = name;
        this.groupTag = groupTag;
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