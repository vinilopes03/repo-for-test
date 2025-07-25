package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_02Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., the input is not sanitized
     * and allows HTTP response splitting through unsanitized input.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__File_setHeaderServlet_02 servlet = new CWE113_HTTP_Response_Splitting__File_setHeaderServlet_02();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the response to capture the header set
        doNothing().when(response).setHeader(anyString(), anyString());

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify if the setHeader method was called with unsanitized input
        verify(response, times(1)).setHeader(eq("Location"), contains("\r\n"));
    }
}