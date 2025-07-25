package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_13Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting an environment variable with a malicious input pattern that includes
     * a CRLF sequence, which is commonly used to exploit such vulnerabilities.
     * The test passes if the vulnerability is present, meaning the malicious input is not sanitized
     * and is directly included in the HTTP header.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable with a malicious input
        String maliciousInput = "en-US%0D%0AContent-Length%3A%200%0D%0A%0D%0A";
        System.setProperty("ADD", maliciousInput);

        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class under test
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_13 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_13();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that the setHeader method was called with the malicious input
        verify(response, times(1)).setHeader(eq("Location"), contains(maliciousInput));
    }
}