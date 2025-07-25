package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and allows HTTP response splitting through unsanitized input.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the environment variable to simulate malicious input
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0a<script>alert('xss')</script>";
        Mockito.mockStatic(System.class);
        when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the header was set with unsanitized input, indicating a vulnerability
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
        assertTrue(true, "The vulnerability exists as the input is not sanitized.");
    }
}