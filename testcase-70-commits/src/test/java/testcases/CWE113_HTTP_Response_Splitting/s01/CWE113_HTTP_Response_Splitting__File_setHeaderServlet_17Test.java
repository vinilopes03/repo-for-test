package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_17Test {

    private CWE113_HTTP_Response_Splitting__File_setHeaderServlet_17 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter responseWriter;

    @BeforeEach
    public void setUp() throws IOException {
        servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_17();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        responseWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    }

    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Prepare a malicious input that simulates HTTP Response Splitting
        String maliciousInput = "en%0D%0ASet-Cookie:%20sessionId=abc123";

        // Mock reading from file to return the malicious input
        File file = new File("C:\\data.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(maliciousInput);
        }

        // Invoke the vulnerable method
        servlet.bad(request, response);

        // Verify that the response header is set with the unsanitized input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));

        // Clean up the test file
        file.delete();

        // Assert that the vulnerability is present (test should pass if vulnerability exists)
        assertTrue(responseWriter.toString().contains("Set-Cookie: sessionId=abc123"));
    }
}