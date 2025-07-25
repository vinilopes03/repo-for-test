package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_08Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by simulating a scenario where unsanitized input is directly added to a cookie.
     * The test will pass if the vulnerability exists, i.e., the malicious input is not sanitized
     * and is directly added to the cookie, which can be checked by inspecting the added cookies.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_08 servlet =
                new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_08();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with potentially unsanitized data
        verify(response, atLeastOnce()).addCookie(argThat(cookie -> {
            // Check if the cookie value contains unsanitized input
            String value = cookie.getValue();
            return value.contains("\r\n") || value.contains("\n");
        }));
    }
}