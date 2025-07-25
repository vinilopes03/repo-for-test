package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_13Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by simulating a scenario where unsanitized input containing CRLF characters
     * is used in the HTTP response header. If the vulnerability exists, the test will pass.
     * If the input is properly sanitized, the test will fail.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_13 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_13();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=abc123";

        // Act
        // Directly invoke the 'bad' method with the malicious input
        servlet.bad(request, response);

        // Assert
        // Verify that the response header was set with the unsanitized input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}