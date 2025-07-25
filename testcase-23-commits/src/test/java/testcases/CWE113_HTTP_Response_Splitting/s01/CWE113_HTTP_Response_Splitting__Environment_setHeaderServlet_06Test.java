package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting an environment variable with a malicious input pattern
     * and checking if the response header is set without sanitization.
     * If the vulnerability exists, the test will pass.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0a";
        System.setProperty("ADD", maliciousInput); // Simulate environment variable

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06();

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the header is set with the malicious input, indicating a vulnerability
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}