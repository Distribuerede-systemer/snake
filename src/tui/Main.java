package tui;

/**
 * Created by Jakob Frederik Frank on 28-09-2015.
*/

public class Main {

    public static void main(String[] args) {

        Logik logik = new Logik();

        logik.addUser("Mads","123");
        logik.addUser("Ole", "321");
        logik.addUser("Helle", "asd");

        logik.start();

    }

}

