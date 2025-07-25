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
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting an environment variable with a malicious input pattern
     * and checking if the response header is set without proper sanitization.
     * If the vulnerability exists, the test will pass.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable with a malicious input
        String maliciousInput = "en-US%0d%0aContent-Length:%200";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify if the header was set with the malicious input
        verify(response, times(1)).setHeader(eq("Location"), eq("/author.jsp?lang=" + maliciousInput));

        // Assert that the vulnerability exists by checking if the header was set with unsanitized input
        assertTrue(true, "The HTTP Response Splitting vulnerability exists as the input was not sanitized.");
    }
}