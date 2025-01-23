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
            "sl.CFEhkpdCEWVVhAkorTqhBQr6rrDbADuxzGuKQIsv1FCeFqxANT7INiIJrY-cfpcaLL_fgEIssDQb0MKHWEfMJzoj3oDGuTU-e-IJsJkeY6pYqfE-Iv5jMswl5yUfSD4ryttdFaus3_TLDkZjMPNoqJA";

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