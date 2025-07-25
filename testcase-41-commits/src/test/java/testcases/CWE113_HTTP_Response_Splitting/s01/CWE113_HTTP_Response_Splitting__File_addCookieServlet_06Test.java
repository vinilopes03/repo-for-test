package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_06Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It simulates a scenario where unsanitized input from a file is used to create a cookie.
     * The test passes if the vulnerability is present, i.e., the cookie value contains unsanitized input.
     */
    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Creating a ByteArrayOutputStream to capture the response output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        when(response.getWriter()).thenReturn(writer);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_06 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_06();

        // Simulating the presence of malicious input in the file
        File file = new File("C:\\data.txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("maliciousInput%0d%0aSet-Cookie:sessionId=maliciousSessionId");
        }

        // Invoking the 'bad' method
        servlet.bad(request, response);

        // Verifying that the response contains the unsanitized input
        writer.flush();
        String responseOutput = outputStream.toString();
        assertTrue(responseOutput.contains("maliciousInput%0d%0aSet-Cookie:sessionId=maliciousSessionId"),
                "The response should contain unsanitized input, indicating a vulnerability.");

        // Clean up the test file
        file.delete();
    }
}