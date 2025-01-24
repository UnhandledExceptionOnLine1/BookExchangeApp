package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadErrorException;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DropboxFetch {

    private static final String ACCESS_TOKEN = 
            "sl.CFJiy0A9z0ZjhEfnV9Gzo8rz6x0VzXJ2pKhWJRjr-Dd5JeiFEcFEooIYH-IuHMet34t-hNqDGpJshCYQfzaRNlC-6tksrgwoqf_nSI2M7CQpoGagCc8iv_6j05uVjNPS2c9CXlr4u53gufLAzYj8mPQ";

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
