package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate bad input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Invoking the bad method
        servlet.bad(request, response);

        // Verifying if the response contains a cookie with the malicious input
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            String value = cookie.getValue();
            return value.contains("\r\n") || value.contains("\n") || value.contains("\r");
        }));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Setting up the environment variable to simulate bad input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Invoking the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verifying if the response does not contain a cookie with the malicious input
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            String value = cookie.getValue();
            return !value.contains("\r\n") && !value.contains("\n") && !value.contains("\r");
        }));
    }
}