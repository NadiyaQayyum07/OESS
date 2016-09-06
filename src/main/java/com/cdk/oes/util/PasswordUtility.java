package com.cdk.oes.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by qayyumn on 8/30/2016.
 */
public class PasswordUtility {
    public static final Random RANDOM = new SecureRandom();
    public static final int PASSWORD_LENGTH = 8;

    public static String generateRandomPassword(){
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ23456789+@";
        String pw = "";
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int)(RANDOM.nextDouble()*letters.length());
            pw+=letters.substring(index,index+1);

        }
        return pw;
    }
}
