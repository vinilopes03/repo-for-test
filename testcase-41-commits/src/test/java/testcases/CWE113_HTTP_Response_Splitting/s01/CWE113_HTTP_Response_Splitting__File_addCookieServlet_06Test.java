package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_06Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * is used in the HTTP response without proper encoding.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_06 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_06();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with potentially unsanitized data
        verify(response, atLeastOnce()).addCookie(any(Cookie.class));

        // Check if the cookie value contains unsanitized newline characters
        Cookie addedCookie = Mockito.mock(Cookie.class);
        when(response.addCookie(addedCookie)).thenAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            return cookie.getValue().contains("\n");
        });

        assertTrue(addedCookie.getValue().contains("\n"), "The cookie value should contain unsanitized newline characters indicating a vulnerability.");
    }
}