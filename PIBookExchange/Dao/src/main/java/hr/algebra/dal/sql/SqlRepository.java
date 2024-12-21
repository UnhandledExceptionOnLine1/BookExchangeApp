/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlRepository implements Repository {

    private static final String ID_USER = "IDKorisnik";
    private static final String USER_NAME = "KorisnickoIme";
    private static final String PASSWORD = "Lozinka";
    private static final String FIRST_NAME = "Ime";
    private static final String LAST_NAME = "Prezime";
    private static final String ADDRESS = "Adresa";
    private static final String TELEPHONE = "Telefon";
    private static final String EMAIL = "Email";
    private static final String MSG = "message";

    private static final String CREATE_USER = "{ CALL createUser (?,?,?,?,?,?,?) }";
    private static final String UPDATE_USER = "{ CALL updateUser (?,?,?,?,?,?,?,?) }";
    private static final String DELETE_USER = "{ CALL deleteUser (?) }";
    private static final String GET_USER = "{ CALL getUser (?) }";
    private static final String GET_USERS = "{ CALL getAllUsers}";

    @Override
    public int createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            stmt.setString(USER_NAME, user.getUserName());
            stmt.setString(PASSWORD, user.getPassword());
            stmt.setString(FIRST_NAME, user.getFirstName());
            stmt.setString(LAST_NAME, user.getLastName());
            stmt.setString(ADDRESS, user.getAddress());
            stmt.setString(TELEPHONE, user.getTelephone());
            stmt.setString(EMAIL, user.getEmail());
            stmt.registerOutParameter(ID_USER, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_USER);
        }
    }

    @Override
    public void updateUser(int id, User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(UPDATE_USER)) {
            stmt.setString(USER_NAME, user.getUserName());
            stmt.setString(PASSWORD, user.getPassword());
            stmt.setString(FIRST_NAME, user.getFirstName());
            stmt.setString(LAST_NAME, user.getLastName());
            stmt.setString(ADDRESS, user.getAddress());
            stmt.setString(TELEPHONE, user.getTelephone());
            stmt.setString(EMAIL, user.getEmail());
            stmt.registerOutParameter(MSG, Types.NVARCHAR);
            stmt.executeUpdate();
            stmt.getString(MSG);
        }
    }

    @Override
    public void deleteUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(DELETE_USER)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.getString(MSG);
        }
    }

    @Override
    public Optional<User> getUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(GET_USER)) {
            stmt.setInt(ID_USER, id);
            
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER), 
                            rs.getString(USER_NAME), 
                            rs.getString(PASSWORD), 
                            rs.getString(FIRST_NAME), 
                            rs.getString(LAST_NAME),
                            rs.getString(ADDRESS),
                            rs.getString(TELEPHONE),
                            rs.getString(EMAIL)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> selectAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(GET_USERS)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt(ID_USER), 
                            rs.getString(USER_NAME), 
                            rs.getString(PASSWORD), 
                            rs.getString(FIRST_NAME), 
                            rs.getString(LAST_NAME),
                            rs.getString(ADDRESS),
                            rs.getString(TELEPHONE),
                            rs.getString(EMAIL)
                    ));
                }
            }       
        }
        return users;
    }
}
