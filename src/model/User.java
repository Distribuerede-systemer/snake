package model;

import java.util.Date;
import java.util.Scanner;


/**
 * Created by Oscar on 12-10-2015.
 */
//
public class User {

    //creating variables
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Date created;
    private String status;


  //Create constructor and initiating the variables
    public User (int id, String firstName, String lastName, String userName, String password, Date created, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.created = created;
        this.status = status;
    }
    
    public User() {

    }
    
    //creating get and set method for all the variables, so they can be used by other classes

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //creating a scanner with the scannername newScanner
    Scanner newScanner = new Scanner(System.in);

    //the scanner saves the input in the varible scannerInput
    String scannerInput = newScanner.nextLine();

    //userNameInput and passwordInput is defined by the input from newScanner
    String userNameInput = scannerInput;
    String passwordInput = scannerInput;
    
    //auth is always false, unless authentication is accepted.
    boolean auth = false;

    //checking if userName and the inputted username is alike - checking if password and inputted password is alike
    private void authentication() {

        if (getUserName() == userNameInput && getPassword() == passwordInput) {
            auth = true;
        }
    }
}