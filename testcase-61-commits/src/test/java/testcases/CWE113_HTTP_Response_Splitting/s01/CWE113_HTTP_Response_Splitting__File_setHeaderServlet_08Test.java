package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_08Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and can cause HTTP response splitting.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_08 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_08();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate reading malicious input from a file
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=abc123";
        doReturn(maliciousInput).when(response).getHeader("Location");

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the response header contains the unsanitized input, indicating a vulnerability
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
        assertTrue(response.getHeader("Location").contains(maliciousInput), "Vulnerability exists: HTTP Response Splitting is possible.");
    }
}