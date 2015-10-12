package controller;


public class Main {

    public static void main(String[] args) {

        String depardieu = "whatever";
        Security encrypt = new Security();
        System.out.println(depardieu);
        System.out.println(encrypt.hashing(depardieu));

    }


}
