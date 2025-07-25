package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_01Test {

    /**
     * Test method for HTTP Response Splitting vulnerability.
     * This test verifies if the 'bad' method in the CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_01
     * class is vulnerable to HTTP Response Splitting by injecting a newline character in the cookie value.
     * If the vulnerability exists, the test will pass by confirming that the malicious input is not sanitized.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_01();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);

        when(response.getWriter()).thenReturn(writer);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify if the response contains a cookie with unsanitized input
        verify(response, atLeastOnce()).addCookie(any(Cookie.class));
        verify(response).addCookie(argThat(cookie -> {
            // Check if the cookie value contains a newline character, indicating a vulnerability
            return cookie.getValue().contains("\n");
        }));
    }
}