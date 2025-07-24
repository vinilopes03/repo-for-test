package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05Test {

    @Test
    public void testVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable to simulate the bad source
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05();

        // Call the bad method
        servlet.bad(request, response);

        // Verify if the response.addCookie was called with a potentially malicious value
        verify(response, times(1)).addCookie(argThat(cookie -> {
            String value = cookie.getValue();
            return value.contains("\r") || value.contains("\n");
        }));

        // Clean up the environment variable
        System.clearProperty("ADD");
    }
}