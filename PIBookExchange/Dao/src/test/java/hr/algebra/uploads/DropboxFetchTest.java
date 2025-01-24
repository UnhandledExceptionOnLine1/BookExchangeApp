/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.uploads;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DbxUserFilesRequests;
import com.dropbox.core.v2.files.DownloadBuilder;
import com.dropbox.core.v2.files.FileMetadata;
import java.awt.MediaTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 *
 * @author Tin
 */
public class DropboxFetchTest {

    @Mock
    private DbxClientV2 mockClient;

    @Mock
    private DbxUserFilesRequests mockFilesRequests;

    @Mock
    private DownloadBuilder mockDownloadBuilder;

    private DropboxFetch dropboxFetch;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockClient.files()).thenReturn(mockFilesRequests);
        when(mockFilesRequests.downloadBuilder(any())).thenReturn(mockDownloadBuilder);
        dropboxFetch = new DropboxFetch(mockClient);
    }

    @Test
    void fetchImage_ShouldReturnImageIcon() throws Exception {
        // Path to the local test image in resources
        ClassLoader classLoader = getClass().getClassLoader();
        File testImage = new File(classLoader.getResource("test.jpg").getFile());

        // Simulate Dropbox download by copying the local test image content
        doAnswer(invocation -> {
            OutputStream outputStream = invocation.getArgument(0);
            try (FileInputStream inputStream = new FileInputStream(testImage)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            return null;
        }).when(mockDownloadBuilder).download(any(OutputStream.class));

        // Simulate Dropbox file path
        String dropboxFilePath = "/test.jpg";

        // Fetch image
        ImageIcon imageIcon = dropboxFetch.fetchImage(dropboxFilePath);

        // Verify the result
        assertNotNull(imageIcon);
        assertNotNull(imageIcon.getImage());
    }
}
