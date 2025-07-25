package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_01Test {

    private CWE113_HTTP_Response_Splitting__File_addCookieServlet_01 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_01();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Prepare a malicious input that simulates HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";

        // Write the malicious input to the file that the servlet reads from
        File file = new File("C:\\data.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(maliciousInput);
        } catch (IOException e) {
            fail("Failed to set up test file with malicious input");
        }

        // Call the vulnerable method
        servlet.bad(request, response);

        // Verify that the response contains a cookie with unsanitized input
        verify(response).addCookie(argThat(cookie -> {
            // Check if the cookie value contains the malicious input
            return cookie.getValue().contains("\r\n");
        }));
    }
}