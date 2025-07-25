package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_13Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by simulating a scenario where unsanitized input is used directly in an HTTP header.
     * The test passes if the vulnerability is present, meaning the input is not sanitized and can
     * cause a response splitting attack.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_13 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_13();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en\r\nSet-Cookie: sessionId=malicious";

        // Act
        // Directly invoke the 'bad' method with the malicious input
        servlet.bad(request, response);

        // Assert
        // Verify that the response header was set with the unsanitized input
        verify(response, times(1)).setHeader(eq("Location"), eq("/author.jsp?lang=" + maliciousInput));
    }
}