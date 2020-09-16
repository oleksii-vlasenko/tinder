package dao;

import model.User;

import java.util.ArrayList;
import java.util.Optional;

public class CollectionUserDao implements UserDao{

    private static final ArrayList<User> users = new ArrayList<>(){{
        add(new User("Adam", "https://upload.wikimedia.org/wikipedia/commons/a/ab/Cat_black.svg"));
        add(new User("Brian", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/60/Cat_silhouette.svg/1200px-Cat_silhouette.svg.png"));
        add(new User("Carl", "https://upload.wikimedia.org/wikipedia/commons/2/27/Black_Cat_02812_svg_vector_nevit.svg"));
        add(new User("Drake", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Julius_the_Cat.svg/1027px-Julius_the_Cat.svg.png"));
        add(new User("Earl", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Black_Cat_Vector.svg/1200px-Black_Cat_Vector.svg.png"));
        add(new User("Fry", "https://i.pinimg.com/originals/ae/1e/d9/ae1ed91ec5d4db8efd9d4a9d10754fdd.png"));
    }};

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public Optional<User> getUser(int id) {
        return Optional.of(users.get(id));
    }

    @Override
    public boolean deleteUser(User user) {
        return users.remove(user);
    }

    @Override
    public Optional<User> deleteUser(int id) {
        return Optional.of(users.remove(id));
    }

    @Override
    public boolean saveUser(User user) {
        return users.add(user);
    }

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public int getSize() {
        return users.size();
    }
}
