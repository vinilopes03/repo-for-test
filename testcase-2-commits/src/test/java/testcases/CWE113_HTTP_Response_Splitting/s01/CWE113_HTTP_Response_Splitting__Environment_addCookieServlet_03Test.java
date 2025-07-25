package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It simulates a scenario where unsanitized input from an environment variable is used
     * directly in a cookie, potentially allowing malicious input to manipulate HTTP headers.
     * The test will pass if the vulnerability is present, i.e., the cookie value is not encoded.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable to simulate malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the cookie was added with the malicious input, indicating a vulnerability
        verify(response, times(1)).addCookie(argThat(cookie -> {
            // Check if the cookie value contains the malicious input without encoding
            return cookie.getValue().equals(maliciousInput);
        }));
    }
}