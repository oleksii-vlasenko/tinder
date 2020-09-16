package model;

import java.util.TreeMap;
import java.util.TreeSet;

public class User {

    private String name;
    private String image;
    private TreeSet<Integer> likes;


    public User(String name, String image) {
        this.name = name;
        this.image = image;
        this.likes = new TreeSet<>(){{
            add(1);
            add(3);
            add(4);
        }};
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

    public void addLike(int id, boolean like) {
        this.likes.add(id);
    }
}
