package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_16Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * can be injected into the HTTP response headers.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_16 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_16();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the response to capture the added cookies
        doNothing().when(response).addCookie(any(Cookie.class));

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with unsanitized data
        verify(response, times(1)).addCookie(argThat(cookie -> {
            // Check if the cookie value contains a newline character, indicating a potential HTTP Response Splitting vulnerability
            return cookie.getValue().contains("\n");
        }));
    }
}