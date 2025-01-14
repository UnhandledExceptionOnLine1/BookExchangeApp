package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.InputStream;

public class DropboxUpload {

    private static final String ACCESS_TOKEN = 
            "sl.CEV6v80KILAxVzAwwk-ducKo0h9kRW3zgYTUH30RMhheV5x6q8iBKsUbiLiPtKABAGikFKMcndRPgcf0cfXV3UgiA2ndJPtpWYpArUdHeDyuM6o-uBR6e7c7M5GWAhUOmDXVU_W0idUScei28MLajIE";

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
