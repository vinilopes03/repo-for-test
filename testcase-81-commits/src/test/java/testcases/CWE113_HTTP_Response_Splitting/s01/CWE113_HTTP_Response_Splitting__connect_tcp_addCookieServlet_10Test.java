package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_10Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It simulates a scenario where unsanitized input is directly added to a cookie.
     * The test passes if the vulnerability is present, i.e., the malicious input is not sanitized.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_10 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_10();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";

        // Mock the behavior of the response to capture cookies added
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Assert that the cookie value contains the malicious input, indicating a vulnerability
            assertTrue(cookie.getValue().contains("\r\nSet-Cookie: sessionId=malicious"));
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Act
        servlet.bad(request, response);

        // Verify that addCookie was called, indicating the vulnerability was triggered
        verify(response, times(1)).addCookie(any(Cookie.class));
    }
}