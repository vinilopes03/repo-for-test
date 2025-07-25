package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_17Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and allows malicious input to be included in the HTTP response headers.
     */
    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_17 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_17();

        // Setting up a malicious input that simulates HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";

        // Mocking the file reading process to return the malicious input
        File file = new File("C:\\data.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(maliciousInput);
        }

        // Invoking the 'bad' method which is vulnerable
        servlet.bad(request, response);

        // Verifying if the response contains the malicious input as a cookie
        verify(response).addCookie(argThat(cookie -> cookie.getValue().equals(maliciousInput)));

        // Clean up the test file
        file.delete();

        // If the malicious input is added as a cookie, the test should pass, indicating the vulnerability exists
        assertTrue(true, "The vulnerability exists as the malicious input was added as a cookie.");
    }
}