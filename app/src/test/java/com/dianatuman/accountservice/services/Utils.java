package com.dianatuman.accountservice.services;

import java.util.Random;

public class Utils {

    public static int randomId() {
        return new Random().nextInt();
    }

}
