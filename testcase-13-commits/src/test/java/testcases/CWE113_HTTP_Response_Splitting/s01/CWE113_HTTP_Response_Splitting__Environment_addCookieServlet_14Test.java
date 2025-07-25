package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_14Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test checks if the 'bad' method in CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_14
     * class is vulnerable to HTTP Response Splitting by passing unsanitized input from an environment variable.
     * The test will pass if the vulnerability exists, i.e., the input is not sanitized and is directly added to the cookie.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_14 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_14();

        // Call the 'bad' method
        servlet.bad(request, response);

        // Verify that the response's addCookie method was called with the unsanitized input
        verify(response, times(1)).addCookie(argThat(cookie -> {
            // Check if the cookie value contains the malicious input
            return cookie.getValue().equals(maliciousInput);
        }));
    }
}