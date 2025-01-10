/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Ad;
import hr.algebra.model.AdDetails;
import hr.algebra.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bruno
 */
public interface UserRepositoryInterface {

    int createUser(User user) throws Exception;

    void updateUser(int id, User user) throws Exception;

    void deleteUser(int id) throws Exception;

    Optional<User> getUser(int id) throws Exception;

    List<User> selectAllUsers() throws Exception;

    Optional<User> loginUser(String username, String password) throws Exception;

}
