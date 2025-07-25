package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_03Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * from an environment variable is used directly in the HTTP header.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0a";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_03 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_03();

        // Invoke the 'bad' method
        servlet.bad(request, response);

        // Verify that the response header was set with unsanitized input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));

        // Assert that the vulnerability is present
        assertTrue(true, "The HTTP Response Splitting vulnerability is present.");
    }
}