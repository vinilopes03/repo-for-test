package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_01Test {

    /**
     * Test method for HTTP Response Splitting vulnerability.
     * This test verifies that the 'bad' method in the CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_01
     * class is vulnerable to HTTP Response Splitting by checking if unsanitized input is used in the HTTP header.
     * The test will pass if the vulnerability exists (i.e., the header is set with unsanitized input).
     */
    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_01();

        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=12345";
        System.setProperty("ADD", maliciousInput);

        // Call the 'bad' method
        servlet.bad(request, response);

        // Verify that the header was set with unsanitized input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}