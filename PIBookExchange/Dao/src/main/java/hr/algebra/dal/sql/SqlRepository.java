/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Ad;
import hr.algebra.model.AdDetails;
import hr.algebra.model.User;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlRepository implements Repository {

    // USER_ADMINISTRATION_CONSTANTS
    private static final String ID_USER = "IDKorisnik";
    private static final String USER_NAME = "KorisnickoIme";
    private static final String PASSWORD = "Lozinka";
    private static final String FIRST_NAME = "Ime";
    private static final String LAST_NAME = "Prezime";
    private static final String ADDRESS = "Adresa";
    private static final String TELEPHONE = "Telefon";
    private static final String EMAIL = "Email";
    private static final String MSG = "message";

    // USER_ADMINISTRATION_PROCEDURES
    private static final String CREATE_USER = "{ CALL CreateUser (?,?,?,?,?,?,?) }";
    private static final String UPDATE_USER = "{ CALL UpdateUser (?,?,?,?,?,?,?,?) }";
    private static final String DELETE_USER = "{ CALL DeleteUser (?) }";
    private static final String GET_USER = "{ CALL GetUser (?) }";
    private static final String GET_USERS = "{ CALL GetAllUsers }";
    private static final String LOGIN_USER = "{ CALL LoginUser(?,?) }";

    // AD_CONSTANTS
    private static final String ID_AD = "IDOglas";
    private static final String AD_NAME = "Naziv";
    private static final String AD_CATEGORY = "KategorijaID";
    private static final String AD_PAYMENT = "NaplataID";
    private static final String AD_PICTURE_PATH = "Slika";
    private static final String AD_DESC = "Opis";
    private static final String AD_TIME = "VrijemeObjave"; // definirano u bazi
    private static final String AD_USER = "KorisnikID";
    private static final String AD_PRICE = "Cijena";

    // AD_PROCEDURES
    private static final String CREATE_AD = "{ CALL CreateAd (?,?,?,?,?,?,?) }";
    private static final String UPDATE_AD = "{ CALL UpdateAd (?,?,?,?,?,?,?,?,?) }";
    private static final String DELETE_AD = "{ CALL DeleteAd (?) }";
    private static final String GET_AD = "{ CALL GetAd (?) }";
    private static final String GET_ADS = "{ CALL GetAllUsers }";
    private static final String GET_ALL_CATEGORIES = "{ CALL GetAllCategoryNames }";
    private static final String GET_ALL_PAYMENTS = "{ CALL GetAllPaymentNames }";
    // AD_PROCEDURES
    private static final String AD_CATEGORY_NAME = "KategorijaNaziv"; // Naziv kategorije
    private static final String AD_PAYMENT_NAME = "VrstaNaplate";    // Naziv vrste naplate
    private static final String AD_USER_NAME = "KorisnikIme";        // Ime korisnika

    @Override
    public int createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall("{ CALL CreateUser (?,?,?,?,?,?,?,?) }")) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getTelephone());
            stmt.setString(7, user.getEmail());
            stmt.registerOutParameter(8, java.sql.Types.INTEGER); // Registracija izlaznog parametra
            stmt.executeUpdate();
            return stmt.getInt(8); // Vraća ID korisnika
        }
    }

    @Override
    public void updateUser(int id, User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_USER)) {
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
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_USER)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.getString(MSG);
        }
    }

    @Override
    public Optional<User> getUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_USER)) {
            stmt.setInt(ID_USER, id);

            try (ResultSet rs = stmt.executeQuery()) {
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
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_USERS)) {

            try (ResultSet rs = stmt.executeQuery()) {
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

    @Override
    public Optional<User> loginUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall((LOGIN_USER))) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
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

    ///////////////////////////////////////
    // AD PROCEDURE CALLING
    @Override
    public void createAd(Ad ad) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_AD)) {
            stmt.setString(AD_NAME, ad.getName());
            stmt.setInt(AD_CATEGORY, ad.getCategoryId());
            stmt.setInt(AD_PAYMENT, ad.getPaymentTypeId());
            String fileName = Paths.get(ad.getImagePath()).getFileName().toString();
            stmt.setString(AD_PICTURE_PATH, fileName);
            stmt.setString(AD_DESC, ad.getDescription());
            stmt.setDouble(AD_PRICE, ad.getPrice());
            stmt.setInt(AD_USER, ad.getUserId());
            //stmt.registerOutParameter(ID_AD, Types.INTEGER); // Postavljanje izlaznog parametra
            stmt.executeUpdate();
            //return stmt.getInt(ID_AD);  // Vraćanje generiranog ID-a oglasa
        }
    }

    @Override
    public void updateAd(int id, Ad ad) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_AD)) {
            stmt.setInt(ID_AD, id);
            stmt.setString(AD_NAME, ad.getName());
            stmt.setInt(AD_CATEGORY, ad.getCategoryId());
            stmt.setInt(AD_PAYMENT, ad.getPaymentTypeId());
            stmt.setString(AD_PICTURE_PATH, ad.getImagePath());
            stmt.setString(AD_DESC, ad.getDescription());
            stmt.setDouble(AD_PRICE, ad.getPrice());
            stmt.setInt(AD_USER, ad.getUserId());
            stmt.registerOutParameter(MSG, Types.NVARCHAR);
            stmt.executeUpdate();
            stmt.getString(MSG);
        }
    }

    @Override
    public void deleteAd(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_AD)) {
            stmt.setInt(ID_AD, id);
            stmt.executeUpdate();
        }
    }

 @Override
public Optional<AdDetails> getAd(int id) throws Exception {
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_AD)) {
        stmt.setInt(1, id); // Postavite ID oglasa kao parametar
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Kreirajte i vratite AdDetails objekt s nazivima
                return Optional.of(new AdDetails(
                        rs.getInt(ID_AD),                 // ID oglasa
                        rs.getString(AD_NAME),            // Naziv oglasa
                        rs.getString(AD_CATEGORY_NAME),   // Naziv kategorije
                        rs.getString(AD_PAYMENT_NAME),    // Naziv vrste naplate
                        rs.getString(AD_PICTURE_PATH),    // Putanja slike
                        rs.getString(AD_DESC),            // Opis oglasa
                        rs.getDouble(AD_PRICE),           // Cijena
                        rs.getString(AD_USER_NAME)        // Ime korisnika
                ));
            }
        }
    }
    return Optional.empty(); // Ako oglas nije pronađen
}

    @Override
    public List<Ad> getAllAds() throws Exception {
        List<Ad> ads = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_ADS)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ads.add(new Ad(
                            rs.getInt(ID_AD),
                            rs.getString(AD_NAME),
                            rs.getInt(AD_CATEGORY),
                            rs.getInt(AD_PAYMENT),
                            rs.getString(AD_PICTURE_PATH),
                            rs.getString(AD_DESC),
                            rs.getDouble(AD_PRICE),
                            rs.getInt(AD_USER)
                    ));
                }
            }
        }
        return ads;
    }

    @Override
    public int getCategoryIdByName(String categoryName) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); PreparedStatement stmt
                = con.prepareStatement("SELECT IDKategorija FROM Kategorija WHERE Naziv = ?")) {
            stmt.setString(1, categoryName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IDKategorija");
                } else {
                    throw new Exception("Category not found: " + categoryName); // Ako kategorija ne postoji
                }
            }
        }
    }

    @Override
    public int getPaymentIdByName(String paymentName) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); PreparedStatement stmt
                = con.prepareStatement("SELECT IDNaplata FROM Naplata WHERE Vrsta = ?")) {
            stmt.setString(1, paymentName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IDNaplata");
                } else {
                    throw new Exception("Category not found: " + paymentName); // Ako kategorija ne postoji
                }
            }
        }
    }

    public List<String> getAllCategoryNames() throws Exception {
        List<String> categoryNames = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); Statement stmt
                = con.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_CATEGORIES)) {
            while (rs.next()) {
                categoryNames.add(rs.getString("Naziv"));
            }
        }
        return categoryNames;
    }

    public List<String> getAllPaymentNames() throws Exception {
        List<String> paymentsNames = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); Statement stmt
                = con.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_PAYMENTS)) {
            while (rs.next()) {
                paymentsNames.add(rs.getString("Vrsta"));
            }
        }
        return paymentsNames;
    }
}
