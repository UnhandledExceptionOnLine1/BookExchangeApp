/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import hr.algebra.observer.Subscriber;

/**
 *
 * @author bruno
 */
public final class User implements Subscriber {

    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String email;
    private boolean isAdmin;

    public User() {
    }

    public User(String userName, String password, String firstName, String lastName, String address, String telephone, String email, boolean isAdmin) {
        setUserName(userName);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setTelephone(telephone);
        setEmail(email);
        setIsAdmin(isAdmin);
    }

    public User(int id, String userName, String password, String firstName, String lastName, String address, String telephone, String email, boolean isAdmin) {
        this(userName, password, firstName, lastName, address, telephone, email, isAdmin);
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
        @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return this.id == other.id;
    }


    @Override
    public String toString() {
        return UserStringify.getString(this);   // S
    }

    @Override
    public String alert(String message) {
        if (isAdmin) {
            return "Hej " + userName + "! " + message;
        } else {
            throw new IllegalStateException("Non-admin users cannot receive alerts.");
        }
    }

}
