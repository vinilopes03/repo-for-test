package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        // Set up the environment variable to simulate the vulnerability
        System.setProperty("ADD", "en-US\r\nSet-Cookie: sessionId=malicious");

        // Call the vulnerable method
        servlet.bad(request, response);

        // Verify that a cookie was added with the potentially malicious value
        verify(response, times(1)).addCookie(any(Cookie.class));

        // Capture the cookie to check its value
        Cookie addedCookie = captureAddedCookie();
        assertEquals("en-US\r\nSet-Cookie: sessionId=malicious", addedCookie.getValue());
    }

    private Cookie captureAddedCookie() {
        // Use an ArgumentCaptor to capture the cookie added to the response
        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        verify(response).addCookie(cookieCaptor.capture());
        return cookieCaptor.getValue();
    }
}