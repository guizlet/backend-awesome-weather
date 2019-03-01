package com.guizlet.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


class Utils {
    static String readFromFile(String fileName) {
        InputStream inputStream = Utils.class.getResourceAsStream("/" + fileName);
        return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
    }
}
