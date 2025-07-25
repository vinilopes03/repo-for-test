package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_08Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by simulating a scenario where unsanitized input containing CRLF characters
     * is added to a cookie. If the vulnerability exists, the test will pass by confirming
     * that the malicious input is directly used in the cookie without sanitization.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_08 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_08();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with unsanitized input
        verify(response, atLeastOnce()).addCookie(argThat(cookie -> {
            // Check if the cookie value contains CRLF characters, indicating a vulnerability
            return cookie.getValue().contains("\r\n");
        }));
    }
}