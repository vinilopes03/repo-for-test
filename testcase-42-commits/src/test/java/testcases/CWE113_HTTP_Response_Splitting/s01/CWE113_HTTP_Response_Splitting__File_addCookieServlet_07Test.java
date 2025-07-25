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
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and can cause HTTP Response Splitting.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Creating a ByteArrayOutputStream to capture the output of the response
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        when(response.getWriter()).thenReturn(writer);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_07 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_07();

        // Simulating the presence of malicious input in the file
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        File file = new File("C:\\data.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(maliciousInput);
        }

        // Invoking the 'bad' method which is vulnerable
        servlet.bad(request, response);

        // Verifying that the response contains the unsanitized input, indicating vulnerability
        verify(response).addCookie(argThat(cookie -> cookie.getValue().equals(maliciousInput)));

        // Clean up the file after the test
        file.delete();

        // Assert that the vulnerability exists
        assertTrue(outputStream.toString().contains("Set-Cookie: sessionId=malicious"));
    }
}