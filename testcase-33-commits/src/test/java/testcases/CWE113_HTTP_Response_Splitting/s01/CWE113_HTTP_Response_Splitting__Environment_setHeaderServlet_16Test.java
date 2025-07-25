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
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting a malicious input containing CRLF characters ('\r\n') as an environment variable.
     * The test passes if the vulnerability is present, meaning the malicious input is not sanitized
     * and is directly used in the HTTP header, leading to potential header injection.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Set up the environment variable with malicious input
        String maliciousInput = "en\r\nContent-Length: 0\r\n\r\n<script>alert('xss')</script>";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16();

        // Call the 'bad' method which is expected to be vulnerable
        servlet.bad(request, response);

        // Verify that the response header was set with the malicious input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}