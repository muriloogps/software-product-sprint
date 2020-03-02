package com.google.sps.servlets;

class Comment {
    private final String name;
    private final String comment;

    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String toString() {
        String toString = "Name: " + name;
        toString += "\n\n";
        toString += "Comment: " + comment;
        return toString;
    }
}