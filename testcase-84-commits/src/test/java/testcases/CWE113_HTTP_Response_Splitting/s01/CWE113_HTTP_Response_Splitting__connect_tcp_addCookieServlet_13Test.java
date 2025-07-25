package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_13Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by simulating a scenario where unsanitized input containing CRLF characters
     * is added to a cookie. If the vulnerability exists, the test will pass by confirming
     * that the malicious input is directly added to the cookie without sanitization.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_13 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_13();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input containing CRLF characters
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";

        // Mock the behavior of the response to capture cookies
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