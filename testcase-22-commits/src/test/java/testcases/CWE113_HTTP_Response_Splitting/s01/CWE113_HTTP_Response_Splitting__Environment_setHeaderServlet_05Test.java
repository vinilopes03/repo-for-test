package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * from an environment variable is used directly in the HTTP header.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US%0d%0aContent-Length:%20123%0d%0a%0d%0a";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the setHeader method was called with unsanitized input
        verify(response, times(1)).setHeader(eq("Location"), contains(maliciousInput));
    }
}