package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It simulates a scenario where unsanitized input from an environment variable is used
     * to create a cookie. The test passes if the vulnerability exists, i.e., the cookie
     * value contains unsanitized input, which could lead to HTTP Response Splitting.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12();

        // Call the 'bad' method which is expected to have the vulnerability
        servlet.bad(request, response);

        // Verify that a cookie was added with the unsanitized malicious input
        verify(response, atLeastOnce()).addCookie(argThat(cookie -> {
            // Check if the cookie value contains the unsanitized input
            return cookie.getValue().contains(maliciousInput);
        }));

        // Clean up the environment variable
        System.clearProperty("ADD");
    }
}