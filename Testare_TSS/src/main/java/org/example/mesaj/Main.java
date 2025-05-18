package org.example.mesaj;

public class Main {
    public static void main(String[] args) {
        // Textul românesc prestabilit (cu diacritice și punctuație)
        String mesajOriginal = "Maine voi fi in fata operei in jurul orei 12.";

        // Criptare
        String mesajCriptat = Cript.codifica(mesajOriginal);

        // Decriptare
        String mesajDecriptat = Cript.decodifica(mesajCriptat);

        // Afișare
        System.out.println("Mesaj original  : " + mesajOriginal);
        System.out.println("Mesaj criptat   : " + mesajCriptat);
        System.out.println("Mesaj decriptat : " + mesajDecriptat);
    }
}