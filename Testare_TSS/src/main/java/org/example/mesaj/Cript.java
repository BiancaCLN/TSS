package org.example.mesaj;

public class Cript {
    public static final String ALFABET = " abcdefghijklmnopqrstuvwxyzăâîșțABCDEFGHIJKLMNOPQRSTUVWXYZĂÂÎȘȚ0123456789.,!?;:-()";
    public static int genereazaCheie(String mesaj) {
        int suma = 0;
        for (int i = 0; i < mesaj.length(); i++) {
            suma += mesaj.charAt(i);
        }
        return suma % ALFABET.length(); // cheia e între 0 și lungimea alfabetului
    }

    public static String filtru(String mesaj) {
        if (mesaj == null) return "";

        String rezultat = "";
        for (int i = 0; i < mesaj.length(); i++) {
            char c = mesaj.charAt(i);
            if (ALFABET.indexOf(c) != -1) {
                rezultat += c;
            }
        }
        return rezultat;
    }

    public static String codifica(String mesaj) {
        if (mesaj == null) return null;

        mesaj = filtru(mesaj);
        int cheie = genereazaCheie(mesaj);

        String rezultat = "";
        for (int i = 0; i < mesaj.length(); i++) {
            char c = mesaj.charAt(i);
            int index = ALFABET.indexOf(c);
            if (index != -1) {
                int nou = (index + cheie) % ALFABET.length();
                rezultat += ALFABET.charAt(nou);
            }
        }
        char codCheie = ALFABET.charAt(cheie);
        return codCheie + rezultat;
    }
    public static String decodifica(String criptat) {
        if (criptat == null || criptat.length() == 0) return null;

        char codCheie = criptat.charAt(0);
        int cheie = ALFABET.indexOf(codCheie);

        String rest = criptat.substring(1);
        String rezultat = "";

        for (int i = 0; i < rest.length(); i++) {
            char c = rest.charAt(i);
            int index = ALFABET.indexOf(c);
            if (index != -1) {
                int nou = (index - cheie + ALFABET.length()) % ALFABET.length();
                rezultat += ALFABET.charAt(nou);
            }
        }

        return rezultat;
    }


}