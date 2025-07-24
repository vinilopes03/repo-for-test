package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that a cookie was added with the malicious input
        verify(response, times(1)).addCookie(argThat(cookie -> {
            return "lang".equals(cookie.getName()) && maliciousInput.equals(cookie.getValue());
        }));
    }

    @Test
    public void testGoodG2B() throws Throwable {
        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Call the goodG2B method
        servlet.goodG2B(request, response);

        // Verify that a cookie was added with the hardcoded value "foo"
        verify(response, times(1)).addCookie(argThat(cookie -> {
            return "lang".equals(cookie.getName()) && "foo".equals(cookie.getValue());
        }));
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Set up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Call the goodB2G method
        servlet.goodB2G(request, response);

        // Verify that a cookie was added with the encoded value
        verify(response, times(1)).addCookie(argThat(cookie -> {
            return "lang".equals(cookie.getName()) && "en-US%0D%0ASet-Cookie%3A+sessionId%3Dmalicious".equals(cookie.getValue());
        }));
    }
}