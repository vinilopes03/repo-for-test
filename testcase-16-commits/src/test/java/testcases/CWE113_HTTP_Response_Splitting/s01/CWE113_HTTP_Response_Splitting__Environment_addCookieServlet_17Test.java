package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_17Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by setting a malicious input containing a newline character ('\n') in the environment variable "ADD".
     * The test passes if the vulnerability is present, meaning the malicious input is not sanitized and is added as a cookie.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable with malicious input
        String maliciousInput = "en-US\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_17 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_17();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that the cookie with malicious input is added to the response
        verify(response, times(1)).addCookie(argThat(cookie -> {
            // Check if the cookie name is "lang" and the value contains the malicious input
            return "lang".equals(cookie.getName()) && cookie.getValue().contains(maliciousInput);
        }));
    }
}