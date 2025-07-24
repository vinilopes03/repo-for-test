package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "value%0D%0ASet-Cookie:%20sessionId=12345";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that a cookie was added with the unencoded malicious input
        verify(response).addCookie(argThat(cookie -> {
            return cookie.getValue().equals(maliciousInput);
        }));

        // Clean up the environment variable
        System.clearProperty("ADD");

        // Assert that the vulnerability is present
        assertTrue(true, "The class is vulnerable to HTTP Response Splitting.");
    }
}