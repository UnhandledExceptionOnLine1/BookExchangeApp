/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Tin
 */
public class DropboxStorageAdapter implements StorageService {

    private static final String ACCESS_TOKEN = 
            "sl.CFLau2FGDs2F_c2Vq-X90TXzaHj3nIlub99B1lMCXR6Br4S5pLjLssdav6kmBvPv6Z1hYasumENnTrYnomS6dSwJyycWlNoHC2Fm3IXKsRtrr2YfQpySQpey9gx_ypgm5LVkBTATuWkZ4adPbO8p-Xs";

    @Override
    public void uploadFile(String localPath, String remotePath) throws Exception {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("BookExchangeApp").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try (InputStream in = new FileInputStream(localPath)) {
            FileMetadata metadata = client.files().uploadBuilder(remotePath)
                    .uploadAndFinish(in);
            System.out.println("File uploaded to: " + metadata.getPathDisplay());
        }
    }
}