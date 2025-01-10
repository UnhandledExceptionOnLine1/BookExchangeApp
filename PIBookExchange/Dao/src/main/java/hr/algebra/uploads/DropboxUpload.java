package hr.algebra.uploads;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.InputStream;

public class DropboxUpload {

    private static final String ACCESS_TOKEN = 
            "sl.u.AFcFJoU_CELHwSZ4S5aRiNrsN8JR_admahKuFWqjfbwJQWaUAA26FxfQq_Z-m5ruBGpntpJ2sJBMoeFZN7UEWUNFuz3Q3DHKsP0de1FVjTra75EmPmZqxoRL_JTQYzyufcKss1y_CHSOLu0uL9QVeou04MRA0EFyswzWuXl9BenJdjcMQbOlW64NPRG_SRHXLTtk7Yz5LuzD6DiaZDqcEXMw5lYS9WuGiao5wL27eyEHLa7UsJ0TsrHj1yhMkzD6uof4eAml5DKeaqpiCA83M4cx6HJ2LFyPNfTqyHVoSwm1DYfdmSSaqnPymosc4vguSHPKNvAlvEfgQWfpZG4PCryf_0WqebyvC3ZEUXbykBaT20ku5l1wjy_Hg0IrpC-jHd9YKRAk5lh-8R70Q_AYoSHy8UoXFG2rs8Wt8ysFTBM6pYTUnVvvUB7MTKrMM327NnCwHNPNwoPLH_s1yyZBTboSIrdQTXEY4C_OeZw9cIqjiRaTx3XrxGCtkCsm0I_jcxvq3LloJV4BoyEiMolq_l3-IIBUyndC6Zs1EY7B1Py8b4KtT5NQLOzKWPXcy7U3s5Q2U61HKYBuQ-MgDqF4rjQnIaG8pZJBrfa7GBJ85RUpUhGL5CHmU9ilG42qx9R6kGMjfdY_ml6G2NsxyyfiVAq6XnRyEeaIlA5O3sLf-A3oqKSTR7jAJXBrImZdxWoaeGcOjAs7_sUvCWWdooREtfSgDzA-ZZ9JNigJd2XzbbhkjIiv_YzHb6TbmX59Htz_I1SrrHiDwV6hGQeRdhbDMKIM9HaxALbUu9zKWZUUVv5w-egyHv5AcnnK69CvVWJVPbVk_6TDCA7lXwBl3751aop0_1Gs2HPbqy5AEMa11vTZyazHpLVKJppQOT4OJucXIHfTAzrkapfbmHPsUSQyuAOse1aHzMkqaS3_vaEg5lwJAMdF3AU3BBDTM2f9hoCdEk6v7VKkQLzzFMmX1H-6CmV9_Y4wb2O-X2wN55Ox5LL6Pagjf-Pc3yHMrBLiku-m3BNOWIol9WO9CXecO59DR6ka1ebs3jtCBrz7jJi8FXXSpUf5Shvyo1OUSP_N7VLbUyj7-G6R0S-7tqne5SIyHrzyLXU90-JGMA9nI6A5oFwwBbDDricQB_WSWxSxABTnijfyBE9nLfu6ZCmoEj_vGYPYNt9bXXZl5WtmasKECSUHFvHpVT3fvaxYidYNZ713431Dni5krVyTJl8zGmO4h6uVyIMD-DqzyQ3PlVWMy67zvBYnhVNM7j7EBs35e2DX8IIWUgYqgGCTr2WdXlpZGnqQedCJZ9apMx8E2pPSvEq98zbXIwY9cRkyZquUfRqwluB8e7WyAQnOkV4HbS0zhnrNmNViNniR1JeDXOd8C-gPq7Icb-B9PDAcUMt7fJr18PsQhQkZkS9PF9tbXKRfilI3YpCxCJCnIsJDe4gnEUDkqA";

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
