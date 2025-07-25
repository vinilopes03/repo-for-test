package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

/**
 * This JUnit 5 test class is designed to verify the presence of an HTTP Response Splitting vulnerability
 * in the CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_06 class.
 * The test specifically checks if unsanitized input can be injected into HTTP headers via cookies.
 */
public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_06Test {

    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_06 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_06();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Mock the response to capture the cookie added
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Assert that the cookie value contains unsanitized input, indicating a vulnerability
            assertTrue(cookie.getValue().contains("\r\n"), "The cookie value should contain unsanitized input, indicating a vulnerability.");
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Act
        servlet.bad(request, response);
    }
}