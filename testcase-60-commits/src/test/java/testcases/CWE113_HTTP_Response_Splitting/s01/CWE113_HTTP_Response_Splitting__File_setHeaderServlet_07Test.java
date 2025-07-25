package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_07Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * can be injected into the HTTP header, leading to response splitting.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_07 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_07();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Create a temporary file with malicious input
        try (FileWriter writer = new FileWriter("C:\\data.txt")) {
            writer.write("en-US%0d%0aContent-Length:%200%0d%0a%0d%0a<script>alert('XSS')</script>");
        } catch (IOException e) {
            fail("Failed to create temporary file for testing");
        }

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the header was set with unsanitized input, indicating a vulnerability
        verify(response).setHeader(eq("Location"), contains("en-US%0d%0aContent-Length:%200%0d%0a%0d%0a<script>alert('XSS')</script>"));
    }
}