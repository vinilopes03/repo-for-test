package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05Test {

    /**
     * Test method to verify the existence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * from an environment variable is used directly in the HTTP response headers.
     */
    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05();

        // Invoke the bad method which is expected to be vulnerable
        servlet.bad(request, response);

        // Verify that the response.addCookie method was called with unsanitized input
        verify(response, times(1)).addCookie(any(Cookie.class));

        // Capture the cookie added to the response
        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        verify(response).addCookie(cookieCaptor.capture());
        Cookie addedCookie = cookieCaptor.getValue();

        // Check if the cookie value contains the malicious input, indicating a vulnerability
        assertTrue(addedCookie.getValue().contains("\r\nSet-Cookie: sessionId=malicious"),
                "The cookie value should contain the unsanitized input, indicating a vulnerability.");
    }
}