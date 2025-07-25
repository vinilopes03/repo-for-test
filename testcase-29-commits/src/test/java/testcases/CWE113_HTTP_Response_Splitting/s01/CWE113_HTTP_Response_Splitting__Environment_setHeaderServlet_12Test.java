package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_12Test {

    /**
     * Test method for HTTP Response Splitting vulnerability.
     * This test verifies that the 'bad' method in the CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_12
     * class is vulnerable to HTTP Response Splitting when unsanitized input is used.
     * 
     * The test sets an environment variable with a malicious payload and checks if the response header
     * is set with the unsanitized input, confirming the vulnerability.
     */
    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Set up the environment variable with a malicious payload
        String maliciousInput = "en-US%0d%0aContent-Length:%200";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_12 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_12();

        // Invoke the 'bad' method
        servlet.bad(request, response);

        // Verify that the response header is set with the unsanitized input
        verify(response, atLeastOnce()).setHeader(eq("Location"), contains(maliciousInput));
    }
}