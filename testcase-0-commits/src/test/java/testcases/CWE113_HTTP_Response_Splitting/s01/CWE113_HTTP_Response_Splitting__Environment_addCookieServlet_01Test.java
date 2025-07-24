package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    public void testBadMethodForVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Mocking environment variable
        String maliciousInput = "en\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains CRLF, indicating a vulnerability
            return cookie.getValue().contains("\r\n");
        }));
    }

    @Test
    public void testGoodB2GMethodForNoVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Mocking environment variable
        String maliciousInput = "en\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.goodB2G(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value does not contain CRLF, indicating no vulnerability
            return !cookie.getValue().contains("\r\n");
        }));
    }
}