package tui;

/**
 * Created by Jakob Frederik Frank on 28-09-2015.
*/

public class Main {

    public static void main(String[] args) {

        Logik logik = new Logik();

        logik.addUser("Mads", "Madsen", "test1", "123");
        logik.addUser("Ole", "Jensen", "test2", "1234");
        logik.addUser("Henrik", "Madsen", "test3", "12345");

        logik.start();

    }

}

