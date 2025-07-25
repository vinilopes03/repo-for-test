package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

/**
 * This test class verifies the presence of an HTTP Response Splitting vulnerability
 * in the CWE113_HTTP_Response_Splitting__File_addCookieServlet_04 class.
 * The test specifically checks if unsanitized input can be used to manipulate HTTP headers.
 */
public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_04Test {

    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_04 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_04();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the response to capture cookies added
        final boolean[] isVulnerable = {false};
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Check if the cookie value contains a newline character, indicating a potential HTTP Response Splitting vulnerability
            if (cookie.getValue().contains("\r\n")) {
                isVulnerable[0] = true;
            }
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Act
        servlet.bad(request, response);

        // Assert
        // The test passes if the vulnerability is present, i.e., the cookie value contains a newline character
        assertTrue(isVulnerable[0], "The application is vulnerable to HTTP Response Splitting.");
    }
}