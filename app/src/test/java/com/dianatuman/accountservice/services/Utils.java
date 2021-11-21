package com.dianatuman.accountservice.services;

import java.util.Random;

/**
 * @author Diana Tumanian dianatumanian@gmail.com
 */
public class Utils {

    public static int randomId() {
        return new Random().nextInt();
    }

}
