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
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_31Test {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private CWE113_HTTP_Response_Splitting__File_addCookieServlet_31 servlet;

    @BeforeEach
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_31();
    }

    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Prepare a file with malicious input
        File file = new File("C:\\data.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("en-US\r\nSet-Cookie: sessionId=malicious");
        } catch (IOException e) {
            fail("Failed to set up test file with malicious input");
        }

        // Call the bad method
        servlet.bad(request, response);

        // Verify that the response contains a cookie with unsanitized input
        verify(response, atLeastOnce()).addCookie(argThat(cookie -> {
            // Check if the cookie value contains the malicious input
            return cookie.getValue().contains("\r\nSet-Cookie: sessionId=malicious");
        }));

        // Clean up the test file
        file.delete();
    }
}