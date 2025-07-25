package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * from an environment variable is directly used in a cookie without encoding.
     */
    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15();

        // Invoking the 'bad' method which is expected to be vulnerable
        servlet.bad(request, response);

        // Verifying that the response contains a cookie with unsanitized input
        verify(response).addCookie(argThat(cookie -> {
            // Check if the cookie value contains the malicious input
            return cookie.getValue().equals(maliciousInput);
        }));
    }
}