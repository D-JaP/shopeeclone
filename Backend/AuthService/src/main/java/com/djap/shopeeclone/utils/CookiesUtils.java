package com.djap.shopeeclone.utils;

import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import java.util.Base64;

public class CookiesUtils {
    public static <T> T deserialize(Cookie cookie, Class<T> cls){
        return cls.cast(cookie.getValue());
    }
}
