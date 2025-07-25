package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_11Test {

    private CWE113_HTTP_Response_Splitting__File_addCookieServlet_11 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_11();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Mock the static method to return true
        IO ioMock = mock(IO.class);
        when(ioMock.staticReturnsTrue()).thenReturn(true);

        // Prepare the malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";

        // Mock the file reading to return the malicious input
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_11 spyServlet = Mockito.spy(servlet);
        doReturn(maliciousInput).when(spyServlet).readDataFromFile(any());

        // Call the bad method
        spyServlet.bad(request, response);

        // Verify that the response.addCookie was called with unsanitized input
        verify(response).addCookie(argThat(cookie -> cookie.getValue().equals(maliciousInput)));
    }
}