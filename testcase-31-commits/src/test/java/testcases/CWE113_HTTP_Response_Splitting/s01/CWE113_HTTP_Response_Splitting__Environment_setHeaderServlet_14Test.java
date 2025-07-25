package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_14Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input is used in the HTTP header.
     * The test will fail if the input is properly sanitized or encoded.
     */
    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate malicious input
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_14 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_14();

        // Invoking the bad method which is expected to be vulnerable
        servlet.bad(request, response);

        // Verifying if the response header was set with unsanitized input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));

        // Asserting that the vulnerability exists
        assertTrue(true, "The HTTP Response Splitting vulnerability exists as the input is not sanitized.");
    }
}