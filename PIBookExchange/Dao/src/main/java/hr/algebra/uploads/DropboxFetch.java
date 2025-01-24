package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DropboxFetch {

    private final DbxClientV2 client;

    // Dodan konstruktor koji omogućuje dependency injection
    public DropboxFetch(DbxClientV2 client) {
        this.client = client;
    }

    // Defaultni konstruktor koji koristi pristupni token
    public DropboxFetch() {
        this(new DbxClientV2(DbxRequestConfig.newBuilder("BookExchangeApp").build(),
                "sl.CFLau2FGDs2F_c2Vq-X90TXzaHj3nIlub99B1lMCXR6Br4S5pLjLssdav6kmBvPv6Z1hYasumENnTrYnomS6dSwJyycWlNoHC2Fm3IXKsRtrr2YfQpySQpey9gx_ypgm5LVkBTATuWkZ4adPbO8p-Xs"));
    }

    public String downloadFile(String dropboxFilePath) throws Exception {
        String localFilePath = System.getProperty("java.io.tmpdir") + File.separator + new File(dropboxFilePath).getName();
        try (OutputStream out = new FileOutputStream(localFilePath)) {
            client.files().downloadBuilder(dropboxFilePath).download(out);
            System.out.println("File downloaded to: " + localFilePath);
        }
        return localFilePath;
    }

    public ImageIcon fetchImage(String dropboxFilePath) throws Exception {
        String localFilePath = downloadFile(dropboxFilePath);
        return new ImageIcon(localFilePath);
    }
}
