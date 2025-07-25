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
 * This test class is designed to verify the presence of an HTTP Response Splitting vulnerability
 * in the CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_09 class.
 * The test will pass if the vulnerability is present (i.e., unsanitized input is added to a cookie),
 * and fail if the input is properly sanitized or encoded.
 */
public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_09Test {

    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_09 servlet =
                new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_09();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Simulate adding a cookie with unsanitized input
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Assert that the cookie value contains unsanitized input, indicating a vulnerability
            assertTrue(cookie.getValue().contains("\r\n"), "The input was sanitized, vulnerability not present.");
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Act
        servlet.bad(request, response);
    }
}