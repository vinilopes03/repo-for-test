package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * is used in the cookie. It will fail if the input is properly sanitized.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set environment variable to a malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=12345";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the response.addCookie() was called with a cookie containing the malicious input
        verify(response, times(1)).addCookie(any(Cookie.class));
        Cookie addedCookie = Mockito.verify(response).addCookie(any(Cookie.class));
        assertTrue(addedCookie.getValue().contains("\r\nSet-Cookie: sessionId=12345"),
                "The vulnerability exists as the input is not sanitized.");
    }
}