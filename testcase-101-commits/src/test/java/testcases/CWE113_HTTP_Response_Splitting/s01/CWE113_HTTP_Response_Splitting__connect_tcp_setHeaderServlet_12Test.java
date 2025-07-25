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
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * can be injected into the HTTP header, leading to response splitting.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_12 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_12();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could lead to HTTP Response Splitting
        String maliciousInput = "en-US%0D%0ASet-Cookie:%20sessionId=malicious";

        // Mock the behavior of IO.staticReturnsTrueOrFalse() to control the flow
        IO ioMock = mock(IO.class);
        when(ioMock.staticReturnsTrueOrFalse()).thenReturn(true);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify if the response header is set with unsanitized input, indicating a vulnerability
        verify(response, atLeastOnce()).setHeader(eq("Location"), contains(maliciousInput));
    }
}