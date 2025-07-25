package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_03Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by providing a malicious input that includes a CRLF sequence ('\r\n').
     * If the input is not properly sanitized, the test will pass, confirming the vulnerability.
     * If the input is sanitized, the test will fail, indicating the vulnerability is absent.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_03 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_03();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create a malicious input with CRLF sequence
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";

        // Mock the behavior of reading from a file to return the malicious input
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_03 spyServlet = Mockito.spy(servlet);
        doReturn(maliciousInput).when(spyServlet).readDataFromFile();

        // Act
        spyServlet.bad(request, response);

        // Assert
        // Verify that the cookie was added with the malicious input, indicating a vulnerability
        verify(response).addCookie(argThat(cookie -> cookie.getValue().equals(maliciousInput)));
    }
}