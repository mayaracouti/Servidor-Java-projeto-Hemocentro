package org.vidamais.utils;

import java.io.InputStream;

public class Utils {
    public static String convertStreamToString(InputStream is) {
        try {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        } catch (Exception error) {
            System.err.println("Erro ao ler o stream: " + error.getMessage());
        }
        return "";
    }
}
