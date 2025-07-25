package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11Test {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11 servlet;

    @BeforeEach
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11();
    }

    @AfterEach
    public void tearDown() {
        request = null;
        response = null;
        servlet = null;
    }

    /**
     * Test method for HTTP Response Splitting vulnerability.
     * This test verifies that the 'bad' method does not sanitize input from the environment variable,
     * allowing malicious input to be included in the HTTP response headers.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Call the 'bad' method
        servlet.bad(request, response);

        // Verify that the response contains the unsanitized cookie
        verify(response, times(1)).addCookie(argThat(cookie -> {
            // Check if the cookie value contains the malicious input
            return cookie.getValue().equals(maliciousInput);
        }));

        // Clean up the environment variable
        System.clearProperty("ADD");
    }
}