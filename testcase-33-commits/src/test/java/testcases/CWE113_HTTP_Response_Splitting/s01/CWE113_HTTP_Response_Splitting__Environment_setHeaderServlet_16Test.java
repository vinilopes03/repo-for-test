package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test checks if the 'bad' method in the CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16
     * class is vulnerable to HTTP Response Splitting by injecting a malicious input.
     * The test will pass if the vulnerability exists, i.e., the malicious input is not sanitized.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate malicious input
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16();

        // Invoking the 'bad' method
        servlet.bad(request, response);

        // Verifying if the response header is set with the unsanitized malicious input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}