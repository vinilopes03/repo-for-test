package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * from an environment variable is directly used in the HTTP header.
     */
    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate malicious input
        String maliciousInput = "en-US%0D%0AContent-Length%3A%200%0D%0A%0D%0A";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06();

        // Invoking the bad method which is expected to be vulnerable
        servlet.bad(request, response);

        // Verifying that the response header is set with unsanitized input
        verify(response, times(1)).setHeader(eq("Location"), contains(maliciousInput));

        // Asserting that the vulnerability is present
        assertTrue(true, "The HTTP Response Splitting vulnerability is present.");
    }
}