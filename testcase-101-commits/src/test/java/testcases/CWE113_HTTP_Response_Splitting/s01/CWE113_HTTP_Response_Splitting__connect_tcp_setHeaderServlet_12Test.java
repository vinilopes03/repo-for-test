package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_12Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It uses a mock HttpServletResponse to capture the header set by the 'bad' method.
     * The test passes if the vulnerability is present, i.e., the malicious input is not sanitized
     * and is directly included in the HTTP header, allowing for response splitting.
     */
    @Test
    public void testBadMethodForHttpResponseSplitting() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_12 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_12();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify if the header was set with unsanitized input
        verify(response, atLeastOnce()).setHeader(eq("Location"), contains("\r\n"));
    }
}