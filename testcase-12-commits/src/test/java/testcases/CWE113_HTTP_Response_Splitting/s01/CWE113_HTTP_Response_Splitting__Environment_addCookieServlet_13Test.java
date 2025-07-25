package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_13Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It simulates a scenario where unsanitized input from an environment variable is used
     * to create a cookie, potentially allowing malicious input to manipulate HTTP headers.
     * The test will pass if the vulnerability exists (i.e., the cookie value contains unsanitized input).
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class under test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_13 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_13();

        // Call the 'bad' method which is expected to be vulnerable
        servlet.bad(request, response);

        // Verify that a cookie with unsanitized value is added
        verify(response, times(1)).addCookie(argThat(cookie -> {
            // Check if the cookie value contains the malicious input
            return cookie.getValue().equals(maliciousInput);
        }));
    }
}