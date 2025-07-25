package testcases.CWE113_HTTP_Response_Splitting.s01;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability is present (i.e., the cookie is added
     * with unvalidated input from the environment variable).
     */
    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate the vulnerability
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03();

        // Invoking the bad method
        servlet.bad(request, response);

        // Verifying that a cookie was added with the malicious input
        verify(response).addCookie(argThat(cookie -> "lang".equals(cookie.getName()) && maliciousInput.equals(cookie.getValue())));

        // Asserting that the test passes if the vulnerability is present
        assertTrue(true, "The vulnerability is present as the cookie was added with unvalidated input.");
    }
}