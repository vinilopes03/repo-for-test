package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and allows HTTP response splitting.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the behavior of IO.staticReturnsTrue() to ensure the bad path is executed
        IO ioMock = mock(IO.class);
        when(ioMock.staticReturnsTrue()).thenReturn(true);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the setHeader method was called with unsanitized input
        verify(response, atLeastOnce()).setHeader(eq("Location"), contains("\r\n"));
    }
}