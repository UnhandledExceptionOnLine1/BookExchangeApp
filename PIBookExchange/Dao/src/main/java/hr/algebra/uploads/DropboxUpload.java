package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.InputStream;

public class DropboxUpload {

    private static final String APP_KEY = "lw23awqj6m0sjbh";
    private static final String APP_SECRET = "9x0k29iygomefkq";
    
   DbxRequestConfig config = DbxRequestConfig.newBuilder("BookExchangeApp").build();
    
    private static final String ACCESS_TOKEN = "";

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


//sl.CENy4i92MUXILcw9uX31SOPlwe2ni3iXWohNYgBrT4nIpAigi9fclShaZ-weG4HyfH77eXr95G9_E-QJeWScUGyGrcA1b0h1iXud8eCplehpuywtAKUKZHK-b_Iv7LYXoR-BgF4HauQQ