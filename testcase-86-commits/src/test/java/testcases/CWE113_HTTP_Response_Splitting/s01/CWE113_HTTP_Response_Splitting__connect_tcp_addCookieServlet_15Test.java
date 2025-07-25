package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_15Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the malicious input is not sanitized.
     * The test will fail if the input is properly sanitized or encoded.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_15 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_15();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate a malicious input that could lead to HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";

        // Mock the behavior of the response to capture the added cookie
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Assert that the cookie value contains the malicious input, indicating a vulnerability
            assertTrue(cookie.getValue().contains("\r\nSet-Cookie: sessionId=malicious"));
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Act
        servlet.bad(request, response);

        // Verify that the addCookie method was called, indicating that the vulnerability was triggered
        verify(response, times(1)).addCookie(any(Cookie.class));
    }
}