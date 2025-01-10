package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.InputStream;

public class DropboxUpload {

    private static final String ACCESS_TOKEN = "sl.CEPDsA5zDz3v7zqaaPEAlRiSNmNvwTJQ5mEVLUIr9IQq4xtxatzW5_K_QVUd4w2-5TLYwkwsVimurreGG7xrzwhMaMkCYcAbkcWdgf6AXMjNL6rbE8Z8fJnDgb2B9gErZOHvtnVllRZsQ6_v22_Sm3g";

    public static void uploadFile(String localFilePath, String dropboxFilePath) throws Exception {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("BookExchangeApp").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try (InputStream in = new FileInputStream(localFilePath)) {
            FileMetadata metadata = client.files().uploadBuilder(dropboxFilePath)
                    .uploadAndFinish(in);
            System.out.println("File uploaded to: " + metadata.getPathDisplay());
        }
    }
}
