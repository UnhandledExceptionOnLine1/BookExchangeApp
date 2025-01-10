package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.InputStream;

public class DropboxUpload {

    private static final String ACCESS_TOKEN = "sl.CENWY4rg6bHqk0I52yC6RPNJdZViEsYs79B2t07Z6v4HRKiXJfRlyFze1fM35LPx7h72534GMqEzI4BJb5VgAiDc-uAIDyDdAGAGW7ZDXFWfeTBrEE_xV4B6i3nSxYRwN8DgI23g5yyxIvF4NPGyakA";

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
