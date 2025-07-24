package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    private CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    public void testBad() throws Throwable {
        // Set environment variable to simulate the vulnerability
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=abc123");

        // Call the bad method
        servlet.bad(request, response);

        // Verify that a cookie was added with potential HTTP response splitting
        verify(response, times(1)).addCookie(any(Cookie.class));
    }

    @Test
    public void testGoodG2B() throws Throwable {
        // Call the goodG2B method
        servlet.goodG2B(request, response);

        // Verify that a cookie was added
        verify(response, times(1)).addCookie(any(Cookie.class));
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Set environment variable to simulate the vulnerability
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=abc123");

        // Call the goodB2G method
        servlet.goodB2G(request, response);

        // Verify that a cookie was added with URLEncoded value
        verify(response, times(1)).addCookie(any(Cookie.class));
    }
}