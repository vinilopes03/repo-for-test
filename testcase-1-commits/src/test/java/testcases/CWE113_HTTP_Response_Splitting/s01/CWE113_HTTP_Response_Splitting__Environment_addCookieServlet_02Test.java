package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=abc123");

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that a cookie was added with the potentially malicious value
        verify(response, times(1)).addCookie(argThat(cookie -> {
            return "lang".equals(cookie.getName()) && "en-US\r\nSet-Cookie: sessionId=abc123".equals(cookie.getValue());
        }));
    }

    @Test
    public void testGoodG2B1() throws Throwable {
        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Call the goodG2B1 method
        servlet.goodG2B1(request, response);

        // Verify that a cookie was added with the hardcoded value
        verify(response, times(1)).addCookie(argThat(cookie -> {
            return "lang".equals(cookie.getName()) && "foo".equals(cookie.getValue());
        }));
    }

    @Test
    public void testGoodG2B2() throws Throwable {
        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Call the goodG2B2 method
        servlet.goodG2B2(request, response);

        // Verify that a cookie was added with the hardcoded value
        verify(response, times(1)).addCookie(argThat(cookie -> {
            return "lang".equals(cookie.getName()) && "foo".equals(cookie.getValue());
        }));
    }

    @Test
    public void testGoodB2G1() throws Throwable {
        // Set up the environment variable
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=abc123");

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Call the goodB2G1 method
        servlet.goodB2G1(request, response);

        // Verify that a cookie was added with the encoded value
        verify(response, times(1)).addCookie(argThat(cookie -> {
            return "lang".equals(cookie.getName()) && "en-US%0D%0ASet-Cookie%3A+sessionId%3Dabc123".equals(cookie.getValue());
        }));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Set up the environment variable
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=abc123");

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify that a cookie was added with the encoded value
        verify(response, times(1)).addCookie(argThat(cookie -> {
            return "lang".equals(cookie.getName()) && "en-US%0D%0ASet-Cookie%3A+sessionId%3Dabc123".equals(cookie.getValue());
        }));
    }
}