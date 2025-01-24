package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class DropboxFetch {

    private final DbxClientV2 client;

    // Konstruktor koji koristi pristupni token iz properties datoteke
    public DropboxFetch() {
        this.client = new DbxClientV2(DbxRequestConfig.newBuilder("BookExchangeApp").build(), getAccessToken());
    }

    // Konstruktor za dependency injection (koristi se za testiranje)
    public DropboxFetch(DbxClientV2 client) {
        this.client = client;
    }

    // Metoda za učitavanje access tokena iz properties datoteke
    private String getAccessToken() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config/dropbox.properties")) {
            if (input == null) {
                throw new RuntimeException("Cannot find dropbox.properties file in config directory");
            }
            properties.load(input);
            return properties.getProperty("DROPBOX_TOKEN").trim(); // Trim spaces
        } catch (Exception e) {
            throw new RuntimeException("Error loading access token from dropbox.properties", e);
        }
    }

    // Metoda za preuzimanje datoteke s Dropboxa
    public String downloadFile(String dropboxFilePath) throws Exception {
        String localFilePath = System.getProperty("java.io.tmpdir") + File.separator + new File(dropboxFilePath).getName();
        try (OutputStream out = new FileOutputStream(localFilePath)) {
            client.files().downloadBuilder(dropboxFilePath).download(out);
            System.out.println("File downloaded to: " + localFilePath);
        }
        return localFilePath;
    }

    // Metoda za dohvaćanje slike kao ImageIcon
    public ImageIcon fetchImage(String dropboxFilePath) throws Exception {
        String localFilePath = downloadFile(dropboxFilePath);
        return new ImageIcon(localFilePath);
    }
}
