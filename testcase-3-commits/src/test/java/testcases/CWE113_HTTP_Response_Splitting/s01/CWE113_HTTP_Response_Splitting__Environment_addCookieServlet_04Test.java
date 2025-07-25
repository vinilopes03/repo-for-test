package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * from an environment variable is directly used in a cookie without encoding.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04();

        // Invoke the 'bad' method
        servlet.bad(request, response);

        // Verify that the response.addCookie method was called with a cookie containing unsanitized input
        verify(response, times(1)).addCookie(any(Cookie.class));
        Cookie addedCookie = Mockito.verify(response).addCookie(any(Cookie.class));
        assertTrue(addedCookie.getValue().contains("\r\n"), "The cookie value should contain unsanitized input, indicating a vulnerability.");
    }
}