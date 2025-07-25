package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_17Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by simulating a scenario where unsanitized input containing CRLF characters
     * is added to a cookie. If the vulnerability exists, the test will pass by confirming
     * that the malicious input is directly added to the cookie without sanitization.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_17 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_17();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input with CRLF characters
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";

        // Act
        // Directly invoke the bad method with the malicious input
        servlet.bad(request, response);

        // Assert
        // Verify that the cookie was added with the malicious input
        verify(response, times(1)).addCookie(argThat(cookie -> {
            // Check if the cookie value contains the unsanitized malicious input
            return cookie.getValue().equals(maliciousInput);
        }));
    }
}