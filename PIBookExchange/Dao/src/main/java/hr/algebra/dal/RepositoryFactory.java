/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author lecturerf6
 */
public class RepositoryFactory {

    private static final String PATH = "/config/repository.properties";
    private static final String CLASS_NAME = "CLASS_NAME";
    
    private static final Properties PROPERTIES = new Properties();
    private static Repository repository;

    static {
        try (InputStream is = RepositoryFactory.class.getResourceAsStream(PATH)){
            PROPERTIES.load(is);
            
            repository = (Repository)
                    Class.forName(PROPERTIES.getProperty(CLASS_NAME))
                    .getDeclaredConstructor()
                    .newInstance();
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        System.out.println(repository);
    }

    private RepositoryFactory() {
    }
    

    public static Repository getRepository() {
        
       
        return repository;
    }
    
    
    
    
}
