package controller;

/**
 * @author Team Depardieu
 * Usage: MainMethod for Security.java testing
 * TODO: Delete after tests.
 */
public class Main {

    public static void main(String[] args) {

        String depardieu = "{\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"}";
        Security security = new Security();
        String test = security.encrypt(depardieu, "j");
        System.out.println(depardieu);
        System.out.println(security.hashing(depardieu));
        System.out.println(security.encrypt(depardieu, "j"));
        System.out.println(security.decrypt(test, "j"));
        if (depardieu.equals(security.decrypt(test, "j"))) {
            System.out.println("True");
        }
    }
}
