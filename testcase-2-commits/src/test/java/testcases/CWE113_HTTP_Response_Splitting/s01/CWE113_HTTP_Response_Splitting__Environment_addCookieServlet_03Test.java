package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03Test {

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that the response added a cookie with the malicious input
        verify(response, times(1)).addCookie(argThat(cookie -> {
            return "lang".equals(cookie.getName()) && maliciousInput.equals(cookie.getValue());
        }));
    }
}