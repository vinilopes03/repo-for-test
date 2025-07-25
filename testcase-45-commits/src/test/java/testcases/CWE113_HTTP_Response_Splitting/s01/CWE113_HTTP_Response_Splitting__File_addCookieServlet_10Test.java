package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_10Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It uses a mock HttpServletResponse to capture the cookies added by the 'bad' method.
     * The test passes if a cookie with unsanitized input is added, indicating the vulnerability exists.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_10 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_10();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with unsanitized input
        verify(response, atLeastOnce()).addCookie(argThat(cookie -> {
            // Check if the cookie value contains a newline character, indicating unsanitized input
            return cookie.getValue().contains("\n");
        }));
    }
}