package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_04Test {

    /**
     * Test method to verify the presence of HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if unsanitized input
     * can be injected into the HTTP header, leading to response splitting.
     * The test will fail if the input is properly sanitized or encoded.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_04 servlet =
                new CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_04();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0aHTTP/1.1%20200%20OK%0d%0aContent-Type:%20text/html%0d%0a%0d%0a<html><body>Injected</body></html>";

        // Mock the behavior of reading from a socket to return the malicious input
        try (SocketMock socketMock = new SocketMock(maliciousInput)) {
            // Act
            servlet.bad(request, response);

            // Assert
            // Verify that the setHeader method was called with unsanitized input
            verify(response).setHeader(eq("Location"), contains(maliciousInput));
        }
    }

    /**
     * A helper class to mock socket behavior for testing purposes.
     */
    private static class SocketMock implements AutoCloseable {
        private final Socket socket;
        private final BufferedReader reader;

        public SocketMock(String input) throws IOException {
            this.socket = mock(Socket.class);
            this.reader = new BufferedReader(new StringReader(input));
            when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(input.getBytes()));
        }

        @Override
        public void close() throws Exception {
            reader.close();
            socket.close();
        }
    }
}