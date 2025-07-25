package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_02Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * is added directly to the cookie, allowing for potential HTTP response splitting.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_02();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with unsanitized input
        verify(response, atLeastOnce()).addCookie(argThat(cookie -> {
            String value = cookie.getValue();
            // Check for unsanitized input that could lead to HTTP Response Splitting
            return value.contains("\r\n") || value.contains("\n");
        }));
    }
}