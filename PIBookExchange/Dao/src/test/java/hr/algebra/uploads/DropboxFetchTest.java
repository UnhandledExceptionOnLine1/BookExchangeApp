package hr.algebra.uploads;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DbxUserFilesRequests;
import com.dropbox.core.v2.files.DownloadBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DropboxFetchTest {

    @Mock
    private DbxClientV2 mockClient;

    @Mock
    private DbxUserFilesRequests mockFilesRequests;

    @Mock
    private DownloadBuilder mockDownloadBuilder;

    private DropboxFetch dropboxFetch;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(mockClient.files()).thenReturn(mockFilesRequests);
        when(mockFilesRequests.downloadBuilder(any())).thenReturn(mockDownloadBuilder);
        dropboxFetch = new DropboxFetch(mockClient);
    }

    @Test
    void testDownloadFile_ShouldDownloadFileSuccessfully() throws Exception {
        // Arrange
        String dropboxFilePath = "/test.txt";
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        String expectedContent = "Test Content";
        simulateDropboxDownload(expectedContent);

        // Act
        String localFilePath = dropboxFetch.downloadFile(dropboxFilePath);

        // Assert
        assertNotNull(localFilePath);
        File downloadedFile = new File(localFilePath);
        assertTrue(downloadedFile.exists());
        assertEquals(expectedContent, new String(java.nio.file.Files.readAllBytes(downloadedFile.toPath())));
    }

    @Test
    void testFetchImage_ShouldReturnImageIcon() throws Exception {
        // Arrange
        File tempFile = File.createTempFile("test", ".jpg");
        tempFile.deleteOnExit();
        simulateDropboxDownload(tempFile);

        // Act
        ImageIcon imageIcon = dropboxFetch.fetchImage("/test.jpg");

        // Assert
        assertNotNull(imageIcon);
        assertNotNull(imageIcon.getImage());
    }

    @Test
    void testDownloadFile_ShouldThrowExceptionWhenFileNotFound() throws Exception {
        // Arrange
        String dropboxFilePath = "/nonexistent.txt";
        doThrow(new RuntimeException("File not found")).when(mockDownloadBuilder).download(any(OutputStream.class));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> dropboxFetch.downloadFile(dropboxFilePath));
        assertEquals("File not found", exception.getMessage());
    }

    private void simulateDropboxDownload(File tempFile) throws Exception {
        doAnswer(invocation -> {
            OutputStream out = invocation.getArgument(0);
            try (FileInputStream in = new FileInputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            return null;
        }).when(mockDownloadBuilder).download(any(OutputStream.class));
    }

    private void simulateDropboxDownload(String content) throws Exception {
        doAnswer(invocation -> {
            OutputStream out = invocation.getArgument(0);
            out.write(content.getBytes());
            return null;
        }).when(mockDownloadBuilder).download(any(OutputStream.class));
    }
}
