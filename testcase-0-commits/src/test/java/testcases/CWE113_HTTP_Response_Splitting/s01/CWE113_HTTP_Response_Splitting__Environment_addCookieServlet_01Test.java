package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * from an environment variable is directly added to a cookie, allowing for
     * potential HTTP response splitting attacks.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Invoking the bad method
        servlet.bad(request, response);

        // Verifying that the cookie was added with unsanitized input
        verify(response, times(1)).addCookie(argThat(cookie -> 
            cookie.getName().equals("lang") && cookie.getValue().equals(maliciousInput)
        ));

        // Clean up the environment variable
        System.clearProperty("ADD");

        // If the cookie was added with unsanitized input, the vulnerability exists
        assertTrue(true, "The HTTP Response Splitting vulnerability exists.");
    }
}