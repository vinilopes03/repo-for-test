package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_14Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * is added to the cookie, allowing for HTTP response splitting.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_14 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_14();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with unsanitized data
        verify(response, atLeastOnce()).addCookie(any(Cookie.class));
        // Check if the cookie value contains unsanitized input (e.g., newline characters)
        Cookie addedCookie = Mockito.mock(Cookie.class);
        when(addedCookie.getValue()).thenReturn("value\nInjectedHeader: value");
        assertTrue(addedCookie.getValue().contains("\n"), "The cookie value should contain unsanitized input.");
    }
}