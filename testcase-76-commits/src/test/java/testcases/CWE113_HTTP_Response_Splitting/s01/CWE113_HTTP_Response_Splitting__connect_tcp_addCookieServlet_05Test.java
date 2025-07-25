package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_05Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test simulates a scenario where unsanitized input is added to a cookie.
     * If the vulnerability exists, the test should pass by confirming that the malicious input
     * is directly added to the cookie without sanitization.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_05 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_05();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with unsanitized input
        verify(response, atLeastOnce()).addCookie(any(Cookie.class));
        Cookie addedCookie = Mockito.mock(Cookie.class);
        when(response.addCookie(addedCookie)).thenAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Check if the cookie value contains unsanitized input
            assertTrue(cookie.getValue().contains("\r\n"), "The cookie value should contain unsanitized input indicating a vulnerability.");
            return null;
        });
    }
}