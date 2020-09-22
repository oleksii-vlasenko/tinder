package controller;

import dao.CollectionAuthDao;

import java.util.Optional;

public class AuthController {

    private final CollectionAuthDao authDao;

    public AuthController(CollectionAuthDao authDao) {
        this.authDao = authDao;
    }

    public Optional<Integer> create(String email, String password) {
        return this.authDao.create(email, password);
    }

    /**
     *
     * @param email
     * @param password
     * @return:     wrong data -> 0
     *              else -> auth id
     */
    public Optional<Integer> check(String email, String password) {
        return this.authDao.check(email, password);
    }

    public Optional<Integer> checkUser(int id) {
        return this.authDao.checkUser(id);
    }

    public Optional<Boolean> checkEmail(String email) {
        return this.authDao.checkEmail(email);
    }

    public Optional<Boolean> combine(int authId, int useId) {
        return this.authDao.combine(authId, useId);
    }
}
