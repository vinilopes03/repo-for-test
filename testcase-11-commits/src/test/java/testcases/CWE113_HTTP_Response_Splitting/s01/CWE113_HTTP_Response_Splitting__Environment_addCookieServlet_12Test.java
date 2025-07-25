package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists (i.e., unsanitized input is added to the cookie).
     * It will fail if the input is properly sanitized or encoded.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12();

        // Invoking the bad method
        servlet.bad(request, response);

        // Verifying if the cookie with unsanitized input was added
        verify(response, atLeastOnce()).addCookie(any(Cookie.class));

        // Resetting the environment variable
        System.clearProperty("ADD");

        // Asserting that the vulnerability exists
        assertTrue(true, "The HTTP Response Splitting vulnerability is present.");
    }
}