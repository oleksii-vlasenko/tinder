package model;

import java.util.TreeMap;
import java.util.TreeSet;

public class User {
    private static int nextId = 0;

    private int id;
    private String name;
    private String image;
    private TreeSet<Integer> likes;


    public User(String name, String image) {
        this.id = nextId++;
        this.name = name;
        this.image = image;
        this.likes = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public TreeSet<Integer> getLikes() {
        return likes;
    }

    public int getId() {
        return this.id;
    }

    public void addLike(int id) {
        this.likes.add(id);
    }
}
