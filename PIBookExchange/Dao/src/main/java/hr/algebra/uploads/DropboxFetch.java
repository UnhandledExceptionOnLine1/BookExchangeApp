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
            "sl.u.AFc3p_9M3Bo5GD4Qg2rKBT4m2vONKqha7fVP5-IKPS9Sjv01HBlpr3KSCrWijX2bhHx151U8rETX5cA6u4DL6LKJO2lzKYKuDg35zZ88iOD6QhVSvPs38K7loO4DfqilTZaWFzAXYOsiaZsWWEnSG0J4wRPpt7OQGmY73eWnW1OpwubZa8qn3KAhWHXpGq2ALSZeg0AnvcLSksCBpeaBvQwh6KFtX23h-EeNqMOZ1Efs9WsxgFPYgdK6lsChwHIvd92H_KcBrCbEMYSDIicmHZ_t62rKMaYHca7-6VuXbXFHG1Oij1mmwEccoSRIgO8dm4i9BxXQV05QmaohRH1xWRoR8J39GXLmrltNVY-JhJYEKtvo2qHl1jHCKOyFhSb-_1f8f9tqowInbPThT7O6cshXuqvLHaiK2o2HT-RR8O0RGK-TBglY_aAvlwTDCiWcs7Li9Lc0AfYXlE5UxZWkVhjtJCdVoADoUz8Lmm_Zoox0HX0WG7kSg8AyURPvowqsoTnLNmeOFcc5wKaP57XQzDIVlu1Tz0WDR7TqPaZcB9xiJPjm1uzsE1ajPGaUtDMu6qdZ9UMF3mqgX9eiRGZQ8DiPOn-MtFKb03J68tmOzRYJ8dfmxmH2At2FtNXHtqiU1y5JORy92gPM9Cc52YdiFkzEafACIqv3wsJv9pBcCDW7R2mztfmHgXe0U1rCpAKN3qtLycJcIq8BQ9KP9JktE_XOT0BPqoSBfNiug0cAym0JpBucjDeqpeuU6p6yja9i9BDkWb0dYCZGMGl77nCPozrlluRta1V3zSYGkzb8QT-ATVGmJ2H7SUfLCB319mY9KIYbEKsTr7jfdwNCBMoYKdS-i2bgx79YA6s1p1GpSASrVTIFr9lFTlR499KhSIykxTiiIp_jbPd9G3e859z_81GTQA6TaXpuM8BqvLrD9mQE54UsFDccJpa1hIpR3-I-1jG31-j-83guI0MDxFNY7dXsgQ1ajVc7U78-eAqHepOSdjmmSQpF62dbKD3cjixJ1vTwMH9rxUiFGJwN4Bj-nqKyZjhuaw4kHnNpTTd8ehKH1sFQWsSfV0hglBO3lXuTouJt3ZyhSh_XcaRr9jx4UzeY23dhB0y74LsxFDrCONHRf-vc22rHQEIFg7JNTOqKYgclwHbejVpQ4pCvVLyuOwHG6iRvrFTKgTfSLpBd8kOI6yy8_wqF6iZnll9MllNJwoqSpBtnaWlZJuLeuGHGCfWCoWpTBK6pdW9K9Ro31VpBSBysxhj55TmwMcuGcFDWCNYOr_VsiUCIpNuJlHhEMMQO_88RnzWtv_Jge53Mu-2otkdTdOLKK3fJH68ey_tS4a7QcOqRyKRxcBR2vYXNbWMOpuS1fpzjpyJH6uOpGDZgm6X-cqTNVI99KEYRYe6xAEzC2Mr42PxLan-Viugkh892sXwn-Zy0Joa5B5Qlz0maFA";

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
