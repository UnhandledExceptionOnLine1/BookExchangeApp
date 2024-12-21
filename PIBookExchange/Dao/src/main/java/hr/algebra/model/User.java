/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author bruno
 */
public final class User {
    
    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String email;

    public User() {
    }

    public User(String userName, String password, String firstName, String lastName, String address, String telephone, String email) {
        setUserName(userName);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setTelephone(telephone);
        setEmail(email);
    }

    public User(int id, String userName, String password, String firstName, String lastName, String address, String telephone, String email) {
        this (userName, password, firstName, lastName, address, telephone, email);
        setId(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return id + " - " + userName;
    }
    
    
}
