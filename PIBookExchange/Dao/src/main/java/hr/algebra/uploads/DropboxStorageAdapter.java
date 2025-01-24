/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Tin
 */
public class DropboxStorageAdapter implements StorageService {

    private static final String ACCESS_TOKEN;

    static {
        Properties properties = new Properties();
        try (InputStream input = DropboxStorageAdapter.class.getClassLoader().getResourceAsStream("config/dropbox.properties")) {
            if (input == null) {
                throw new RuntimeException("dropbox.properties file not found in resources/config directory.");
            }
            properties.load(input);
            ACCESS_TOKEN = properties.getProperty("DROPBOX_TOKEN");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Dropbox access token from properties file.", e);
        }
    }

    private final DbxClientV2 client;

    // Konstruktor za proizvodni kod (defaultno ponašanje)
    public DropboxStorageAdapter() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("BookExchangeApp").build();
        this.client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    // Konstruktor za testiranje (dependency injection)
    public DropboxStorageAdapter(DbxClientV2 client) {
        this.client = client;
    }

    @Override
    public void uploadFile(String localPath, String remotePath) throws Exception {
        if (remotePath == null || remotePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Remote path cannot be empty or null.");
        }

        try (InputStream in = new FileInputStream(localPath)) {
            FileMetadata metadata = client.files().uploadBuilder(remotePath)
                    .uploadAndFinish(in);
            System.out.println("File uploaded to: " + metadata.getPathDisplay());
        }
    }
}
