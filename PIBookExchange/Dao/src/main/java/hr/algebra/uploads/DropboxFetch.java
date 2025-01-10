package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadErrorException;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DropboxFetch {

    private static final String ACCESS_TOKEN = "sl.CEPDsA5zDz3v7zqaaPEAlRiSNmNvwTJQ5mEVLUIr9IQq4xtxatzW5_K_QVUd4w2-5TLYwkwsVimurreGG7xrzwhMaMkCYcAbkcWdgf6AXMjNL6rbE8Z8fJnDgb2B9gErZOHvtnVllRZsQ6_v22_Sm3g";

    private final DbxClientV2 client;

    public DropboxFetch() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("BookExchangeApp").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    /**
     * Download a file from Dropbox.
     *
     * @param dropboxFilePath Path of the file in Dropbox
     * @return Local path to the downloaded file
     * @throws Exception
     */
    public String downloadFile(String dropboxFilePath) throws Exception {
        String localFilePath = System.getProperty("java.io.tmpdir") + File.separator + new File(dropboxFilePath).getName();

        try (OutputStream out = new FileOutputStream(localFilePath)) {
            client.files().downloadBuilder(dropboxFilePath).download(out);
            System.out.println("File downloaded to: " + localFilePath);
        } catch (DownloadErrorException e) {
            System.err.println("Error downloading file: " + e.getMessage());
            throw e;
        }

        return localFilePath;
    }

    /**
     * Fetch an image as an ImageIcon for UI use.
     *
     * @param dropboxFilePath Path of the image file in Dropbox
     * @return ImageIcon to use in Swing
     * @throws Exception
     */
    public ImageIcon fetchImage(String dropboxFilePath) throws Exception {
        String localFilePath = downloadFile(dropboxFilePath);
        return new ImageIcon(localFilePath);
    }
}
