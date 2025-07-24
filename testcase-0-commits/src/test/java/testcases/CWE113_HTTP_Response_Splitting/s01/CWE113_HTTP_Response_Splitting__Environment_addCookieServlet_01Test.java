package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousData = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousData);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Call the bad method
        servlet.bad(request, response);

        // Capture the cookie added to the response
        verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains a newline, indicating a potential HTTP Response Splitting vulnerability
            return cookie.getValue().contains("\r\n");
        }));
    }
}