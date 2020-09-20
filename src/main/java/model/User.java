package model;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class User {

    private int id;
    private String name;
    private String image;
    private Set<Integer> likes;
    private Set<Integer> dislikes;

    public User(String name, String image) {
        this.id = -1;
        this.name = name;
        this.image = image;
        this.likes = new TreeSet<>();
        this.dislikes = new TreeSet<>();
    }

    public User(String name, String image, Set<Integer> likes, Set<Integer> dislikes) {
        this(name, image);
        this.id = -1;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Set<Integer> getLikes() {
        return this.likes;
    }

    public Set<Integer> getDislikes() {
        return this.dislikes;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addLike(int id) {
        this.likes.add(id);
    }

    public void addDislike(int id) {
        this.dislikes.add(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", likes='" + likes.toString() + '\'' +
                ", dislikes='" + dislikes.toString() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(image, user.image) &&
                Objects.equals(likes, user.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, likes);
    }
}
