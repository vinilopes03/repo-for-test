package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_05Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test checks if the 'bad' method in CWE113_HTTP_Response_Splitting__File_setHeaderServlet_05
     * allows unsanitized input to be included in the HTTP header, which can lead to HTTP Response Splitting.
     * The test will pass if the vulnerability exists (i.e., the input is not sanitized).
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_05 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_05();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the setHeader method was called with unsanitized input
        // We assume that the malicious input is read from the file and directly used in the header
        verify(response, atLeastOnce()).setHeader(eq("Location"), contains("\n"));
    }
}