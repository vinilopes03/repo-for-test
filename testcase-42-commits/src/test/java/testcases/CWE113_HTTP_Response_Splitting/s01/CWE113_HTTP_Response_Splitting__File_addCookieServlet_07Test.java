package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_07Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * can be used to manipulate HTTP headers.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_07 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_07();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the response to capture the added cookies
        doNothing().when(response).addCookie(any(Cookie.class));

        // Create a temporary file with malicious input
        File tempFile = File.createTempFile("data", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("en-US\r\nSet-Cookie: sessionId=malicious");
        }

        // Redirect the file reading to the temporary file
        System.setProperty("user.dir", tempFile.getParent());

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the cookie was added with unsanitized input
        verify(response, times(1)).addCookie(argThat(cookie -> {
            String value = cookie.getValue();
            // Check if the cookie value contains unsanitized input
            return value.contains("\r\nSet-Cookie: sessionId=malicious");
        }));

        // Clean up
        tempFile.delete();
    }
}