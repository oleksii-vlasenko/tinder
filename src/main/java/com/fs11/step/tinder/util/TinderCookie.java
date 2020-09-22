package com.fs11.step.tinder.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class TinderCookie {

    private static final String COOKIE_NAME = "user";

    public static Optional<Integer> getCookie(HttpServletRequest req) {
        return Optional.ofNullable(req.getCookies()).flatMap(l ->
                Arrays.stream(l)
                        .filter(c -> COOKIE_NAME.equals(c.getName()))
                        .findFirst())
                .map(r -> Integer.parseInt(r.getValue()));

    }

    public static HttpServletResponse addCookie(HttpServletResponse resp, int id) {

        Cookie cookie = new Cookie(COOKIE_NAME, String.valueOf(id));
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);

        return resp;
    }

    public static HttpServletResponse removeCookie(HttpServletResponse resp) {

        Cookie cookie_rem = new Cookie(COOKIE_NAME, String.valueOf(0));
        cookie_rem.setPath("/");
        cookie_rem.setMaxAge(0);
        resp.addCookie(cookie_rem);

        return resp;
    }
}
