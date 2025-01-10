package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.InputStream;

public class DropboxUpload {

<<<<<<< HEAD

    private static final String ACCESS_TOKEN = "sl.u.AFetEnmz3noOhZ7ByyOooikxWcqXdZiBrXdlFI_NkEIRJZeiESlWRaDrKYHbdhNzQKbwIys0sAVNQFaigg07PYMzLimptmgVU_lkKXaxyEuvMl1cHJ_VY41R8emh-fPajfwqTjuXwOpSeHLn1k1FcchH5N4f57Ha_O4HNMEQCE7dUYzz_wIMqOC7PDjoATqwFm3Q7Br9NRtSa4f6a3Y1474lDLQbBmep6aE3EEIfAWX51Rf2kpLZvygXLB4EzoimIYBqNulCAYKeU1jcAiDSTfTDYMfq1TNyU2R0980lqehLDDoqK-TbNeYWHMcmTKC6NqNPeTAh-DtPhHu3f7rXttEH0d4pO8yT-SgPYVry-dX5yeCJojjfOvgkqzUb6qOhKSlkNvsnJ7bP6ayvYnSodbzwvaXgeAUmWyFG2A1yK37_gKgmPmQt69qkze74Q2prf2wEmj-kziKchnX3AloLVMXkaE-KXyi1on6tEGSvUyd5tSLJLdxDQr77TpeR906ON-MMtcBj8ozrd4z2zCeD5EfIBxKgbPQg215usY-bhLwWIJ0uD9ohITfv7BBL0-8raLFyICAPVeXjHunJ4JNyf_uOTMG1BBfySLTGb5lzxQGHp0DrReSUnoRo-DMjxXkovFmcHfgkBd9kfC09x0i4A3KFAwRfUqvgM1pkD1Wzm7bsQh1pKP-UGk6paFIq0bwfL0HtxQhGkl6a6RXz2DhOSwWoxXx6T87_tR0ltQOl9DoV5njjuci6ssq_wtUa_U-cM_uRq8FWSJGMzYrBgOYjiKWCZhmXpu6-m32-pLVdkJehZ-dWAroMAYRfQdxmAAnV0BKD9ZXYWqKfCuGtSI1gjFMjc8U1QZVw0OUsKzocXvx0OO3uYWvCzh9RUrJIIWLXg7kdhTLyRE3h_tA_ViGSCAXhlwsT5wVz35qP_q0gO5_ockw2AssgnVxoASknRu_A1PoiXiFFtdEdn5NwNVkQn_oC-vlAbAkxoMUqQcfUfMdvk6alSt0WVf64XhbhS3im6TfN7WJemtjA9A9U510qJPNAs3H1a724sTIrpx-BWt4-2n6bHxPk-t-mQHeOQthSkfmLrO5Vo4nCwnHjt6td_v_KPNsVhwxqSprwHNEAbLQecEk_Qm_OspwOi0QbRtAhYuQbkSDeDKQYj74IsyQlyp3LgI5Cjbxn_82eXZkhWgZOYqWjPTxu4OQV-8MvC81OliBbxRRqh3peZtmuOBdgP41hgBypL4Bh9-nSRWc-HKdAB-pZeLcHoGeETATSrbF5To4omESVksQNAjTFEklrWkFlzbsFFr3xQsvBJ2uaV6sOawMIqfGZfIRzIudcQNiMFXvwXnjtQRKHnmR4QksqWZOM_mnARBy1H8dwfxYjhcztsEOpDzgAhNCuEl5Fm-O1C0Cj3cJ4rzfS6-OZa-NzmoI7r4rk8uwtRPxN9zx0qerNpw";
=======
    private static final String ACCESS_TOKEN = "sl.CENWY4rg6bHqk0I52yC6RPNJdZViEsYs79B2t07Z6v4HRKiXJfRlyFze1fM35LPx7h72534GMqEzI4BJb5VgAiDc-uAIDyDdAGAGW7ZDXFWfeTBrEE_xV4B6i3nSxYRwN8DgI23g5yyxIvF4NPGyakA";
>>>>>>> cbc16d0894322b3e34ac2290779b6a16402baafe

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
