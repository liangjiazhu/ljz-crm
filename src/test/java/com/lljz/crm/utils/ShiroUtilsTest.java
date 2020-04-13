package com.lljz.crm.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShiroUtilsTest {

    @Test
    public void encryptPassword() {
        System.out.println(new SimpleHash("md5","123123","358957cd03fa",2));
    }

    @Test
    public void randomSalt() {
        System.out.println("随机盐"+ShiroUtils.randomSalt());
    }
}