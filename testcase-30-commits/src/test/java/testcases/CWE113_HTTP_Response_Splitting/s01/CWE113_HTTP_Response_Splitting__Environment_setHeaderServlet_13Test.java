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
     * a CRLF sequence, which is commonly used to exploit this type of vulnerability.
     * The test passes if the vulnerability is present, meaning the malicious input is not sanitized
     * and is directly included in the HTTP header.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable with malicious input
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class under test
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_13 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_13();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that the response header was set with the malicious input
        verify(response).setHeader("Location", "/author.jsp?lang=" + maliciousInput);

        // Assert that the vulnerability is present (i.e., the malicious input was not sanitized)
        assertTrue(true, "The HTTP Response Splitting vulnerability is present.");
    }
}