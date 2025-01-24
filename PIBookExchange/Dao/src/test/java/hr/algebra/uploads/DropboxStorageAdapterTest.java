package hr.algebra.uploads;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DbxUserFilesRequests;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DropboxStorageAdapterTest {

    private static final String LOCAL_PATH = "src/test/resources/test.jpg";
    private static final String REMOTE_PATH = "/test.jpg";

    @Mock
    private DbxClientV2 mockClient;

    @Mock
    private DbxUserFilesRequests mockFiles;

    @Mock
    private UploadBuilder mockUploadBuilder;

    @Mock
    private FileMetadata mockMetadata;

    private DropboxStorageAdapter dropboxStorageAdapter;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Mockiranje DbxUserFilesRequests
        when(mockClient.files()).thenReturn(mockFiles);

        // Mockiranje UploadBuilder
        when(mockFiles.uploadBuilder(REMOTE_PATH)).thenReturn(mockUploadBuilder);

        // Mockiranje uploadAndFinish
        when(mockUploadBuilder.uploadAndFinish(any(InputStream.class))).thenReturn(mockMetadata);

        // Mockiranje povratne vrijednosti getPathDisplay
        when(mockMetadata.getPathDisplay()).thenReturn(REMOTE_PATH);

        // Inicijalizacija adaptera
        dropboxStorageAdapter = new DropboxStorageAdapter(mockClient);
    }

    @Test
    void testUploadFile_ShouldUploadFileSuccessfully() throws Exception {
        // Poziv metode uploadFile
        dropboxStorageAdapter.uploadFile(LOCAL_PATH, REMOTE_PATH);

        // Verifikacija da je metoda uploadAndFinish pozvana jednom
        verify(mockUploadBuilder, times(1)).uploadAndFinish(any(InputStream.class));

        // Verifikacija da je ispravan remote path korišten
        verify(mockFiles).uploadBuilder(REMOTE_PATH);

        // Verifikacija da je getPathDisplay pozvan
        verify(mockMetadata).getPathDisplay();
    }
}