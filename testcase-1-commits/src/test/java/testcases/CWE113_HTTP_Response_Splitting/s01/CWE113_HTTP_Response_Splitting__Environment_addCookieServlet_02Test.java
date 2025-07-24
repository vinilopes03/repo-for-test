package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    private CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    public void testBad() throws Throwable {
        // Set environment variable to simulate the vulnerability
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=12345");

        // Capture the cookies added to the response
        doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            assertTrue(cookie.getValue().contains("\r\nSet-Cookie: sessionId=12345"), "Cookie value should contain CRLF injection");
            return null;
        }).when(response).addCookie(any(Cookie.class));

        // Call the bad method
        servlet.bad(request, response);

        // Verify that addCookie was called
        verify(response, times(1)).addCookie(any(Cookie.class));
    }

    @Test
    public void testGoodG2B1() throws Throwable {
        // Call the goodG2B1 method
        servlet.goodG2B1(request, response);

        // Verify that addCookie was called with a safe value
        verify(response, times(1)).addCookie(argThat(cookie -> "foo".equals(cookie.getValue())));
    }

    @Test
    public void testGoodG2B2() throws Throwable {
        // Call the goodG2B2 method
        servlet.goodG2B2(request, response);

        // Verify that addCookie was called with a safe value
        verify(response, times(1)).addCookie(argThat(cookie -> "foo".equals(cookie.getValue())));
    }

    @Test
    public void testGoodB2G1() throws Throwable {
        // Set environment variable
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=12345");

        // Call the goodB2G1 method
        servlet.goodB2G1(request, response);

        // Verify that addCookie was called with an encoded value
        verify(response, times(1)).addCookie(argThat(cookie -> !cookie.getValue().contains("\r\n")));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Set environment variable
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=12345");

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify that addCookie was called with an encoded value
        verify(response, times(1)).addCookie(argThat(cookie -> !cookie.getValue().contains("\r\n")));
    }
}