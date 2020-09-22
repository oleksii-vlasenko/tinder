package service;

import dao.CollectionAuthDao;

import java.util.Optional;

public class AuthService {
    private final CollectionAuthDao collection;

    public AuthService(CollectionAuthDao collection) {
        this.collection = collection;
    }

    public Optional<Integer> create(String email, String password) {
        return this.collection.create(email, password);
    }

    public Optional<Integer> check(String email, String password) {
        return this.collection.check(email, password);
    }

    public Optional<Integer> checkUser(int id) {
        return this.collection.checkUser(id);
    }

    public Optional<Boolean> combine(int authId, int useId) {
        return this.collection.combine(authId, useId);
    }
}
