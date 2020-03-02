package com.google.sps.servlets;

class Comment {
    private final String name;
    private final String comment;
    private final long timestamp;

    public Comment(String name, String comment, long timestamp) {
        this.name = name;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public String toString() {
        return String.format("Name: %s\n\nComment: %s", name, comment);
    }
}