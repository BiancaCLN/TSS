package org.example.mesaj;

public class Main {
    public static void main(String[] args) {
        String mesajOriginal = "Maine voi fi in fata operei in jurul orei 12.";
        String mesajCriptat = Cript.codifica(mesajOriginal);
        String mesajDecriptat = Cript.decodifica(mesajCriptat);
        System.out.println("Mesaj original  : " + mesajOriginal);
        System.out.println("Mesaj criptat   : " + mesajCriptat);
        System.out.println("Mesaj decriptat : " + mesajDecriptat);
    }
}
