package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_15Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting an environment variable with a malicious input pattern that includes
     * a CRLF sequence. The test checks if this input is directly used in the HTTP response header
     * without proper sanitization or encoding, which would confirm the vulnerability.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable with malicious input
        String maliciousInput = "en-US%0d%0aContent-Length%3a%200%0d%0a%0d%0a";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class under test
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_15 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_15();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that the response header was set with the malicious input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}