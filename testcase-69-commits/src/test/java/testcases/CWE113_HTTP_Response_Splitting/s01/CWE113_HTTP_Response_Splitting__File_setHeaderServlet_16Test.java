package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_16Test {

    /**
     * Test method for HTTP Response Splitting vulnerability.
     * This test verifies that the 'bad' method in the CWE113_HTTP_Response_Splitting__File_setHeaderServlet_16
     * class is vulnerable to HTTP Response Splitting by checking if unsanitized input can be injected into
     * the HTTP header.
     */
    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_16 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_16();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=12345";

        // Mock the behavior of reading from a file to return the malicious input
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_16 spyServlet = Mockito.spy(servlet);
        doReturn(maliciousInput).when(spyServlet).readDataFromFile();

        // Act
        spyServlet.bad(request, response);

        // Assert
        // Verify that the setHeader method was called with unsanitized input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}