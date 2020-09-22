package com.fs11.step.tinder.dao;

import java.util.Optional;

public interface AuthDao {

    Optional<Integer> create(String email, String password);

    Optional<Integer> check(String email, String password);

    Optional<Integer> checkUser(int id);

    Optional<Boolean> combine(int authId, int userId);
}
