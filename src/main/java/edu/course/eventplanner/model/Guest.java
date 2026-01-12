package edu.course.eventplanner.model;

public class Guest {

    private final String name;
    private final String groupTag;
    private final String id;

    public Guest(String name, String groupTag, String id) {
        this.name = name;
        this.groupTag = groupTag;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getGroupTag() {
        return groupTag;
    }

    public String getId() {
        return id;
    }
}