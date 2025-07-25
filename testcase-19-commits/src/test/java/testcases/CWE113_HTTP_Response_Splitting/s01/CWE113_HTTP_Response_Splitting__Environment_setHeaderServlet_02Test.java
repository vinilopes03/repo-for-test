package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_02Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting a malicious environment variable and checking if the response
     * header is set with unsanitized input, which would confirm the vulnerability.
     */
    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable with malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class under test
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_02();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that the response header was set with the unsanitized input
        verify(response).setHeader("Location", "/author.jsp?lang=" + maliciousInput);

        // Assert that the vulnerability is present (i.e., the header was set with unsanitized input)
        assertTrue(true, "The HTTP Response Splitting vulnerability is present.");
    }
}