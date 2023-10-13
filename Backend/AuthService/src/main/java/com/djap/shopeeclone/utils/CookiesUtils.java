package com.djap.shopeeclone.utils;

import javax.servlet.http.Cookie;

public class CookiesUtils {
    public static <T> T deserialize(Cookie cookie, Class<T> cls){
        return cls.cast(cookie.getValue());
    }
}
