package hr.algebra.uploads;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DbxUserFilesRequests;
import com.dropbox.core.v2.files.DownloadBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

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
    void testFetchImage_ShouldReturnImageIcon() throws Exception {
        // Arrange
        File testImage = createTestImageFile();
        simulateDropboxDownload(testImage);

        String dropboxFilePath = "/test.jpg";

        // Act
        ImageIcon imageIcon = dropboxFetch.fetchImage(dropboxFilePath);

        // Assert
        assertNotNull(imageIcon);
        assertNotNull(imageIcon.getImage());
    }

    @Test
    void testDownloadFile_ShouldDownloadToLocalTemp() throws Exception {
        // Arrange
        String dropboxFilePath = "/test.txt";
        String expectedContent = "Sample file content";

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
    void testDownloadFile_ShouldThrowExceptionWhenDownloadFails() throws Exception {
        // Arrange
        String dropboxFilePath = "/nonexistent.txt";

        // Simulate Dropbox download throwing a DbxException
        doThrow(new DbxException("Download failed")).when(mockDownloadBuilder).download(any(OutputStream.class));

        // Act & Assert
        Exception exception = assertThrows(DbxException.class, () -> dropboxFetch.downloadFile(dropboxFilePath));
        assertEquals("Download failed", exception.getMessage());
    }

    private File createTestImageFile() throws Exception {
        // Helper to create a temporary test image file
        File tempFile = File.createTempFile("test", ".jpg");
        tempFile.deleteOnExit();
        try (OutputStream out = new FileOutputStream(tempFile)) {
            out.write(new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF}); // Minimal JPEG header
        }
        return tempFile;
    }

    private void simulateDropboxDownload(File testFile) throws Exception {
        doAnswer(invocation -> {
            OutputStream outputStream = invocation.getArgument(0);
            try (FileInputStream inputStream = new FileInputStream(testFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            return null;
        }).when(mockDownloadBuilder).download(any(OutputStream.class));
    }

    private void simulateDropboxDownload(String content) throws Exception {
        doAnswer(invocation -> {
            OutputStream outputStream = invocation.getArgument(0);
            outputStream.write(content.getBytes());
            return null;
        }).when(mockDownloadBuilder).download(any(OutputStream.class));
    }

    @Test
    void testFetchImage_ShouldThrowExceptionWhenDownloadFails() throws Exception {
        // Arrange
        String dropboxFilePath = "/nonexistent.jpg";

        // Simulate Dropbox download throwing an exception
        doThrow(new DbxException("Download failed")).when(mockDownloadBuilder).download(any(OutputStream.class));

        // Act & Assert
        Exception exception = assertThrows(DbxException.class, () -> dropboxFetch.fetchImage(dropboxFilePath));
        assertEquals("Download failed", exception.getMessage());
    }

    @Test
    void testDownloadFile_ShouldHandleNetworkIssues() throws Exception {
        // Arrange
        String dropboxFilePath = "/network_issue.txt";

        // Simulate network issue
        doThrow(new DbxException("Network error")).when(mockDownloadBuilder).download(any(OutputStream.class));

        // Act & Assert
        Exception exception = assertThrows(DbxException.class, () -> dropboxFetch.downloadFile(dropboxFilePath));
        assertEquals("Network error", exception.getMessage());
    }




}
