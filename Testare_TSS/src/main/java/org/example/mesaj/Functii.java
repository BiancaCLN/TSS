package org.example.mesaj;

public class Functii {

    public static boolean esteValid(String mesaj) {
        if (mesaj == null) return false;
        if (mesaj.trim().length() == 0) return false;
        return true;
    }

    public static String invers(String mesaj) {
        if (mesaj == null) return null;

        String rezultat = "";
        for (int i = mesaj.length() - 1; i >= 0; i--) {
            rezultat += mesaj.charAt(i);
        }
        return rezultat;
    }

    public static int lungime(String mesaj) {
        if (mesaj == null) return 0;
        return mesaj.length();
    }
}