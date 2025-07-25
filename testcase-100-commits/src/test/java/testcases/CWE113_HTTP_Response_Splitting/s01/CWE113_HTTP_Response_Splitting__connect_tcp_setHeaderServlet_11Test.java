package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_11Test {

    /**
     * Test method to verify the HTTP Response Splitting vulnerability.
     * This test will pass if the vulnerability exists, i.e., if the input is not sanitized
     * and allows HTTP response splitting via unsanitized input.
     */
    @Test
    public void testHttpResponseSplittingVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_11 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_11();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input that could cause HTTP Response Splitting
        String maliciousInput = "en-US%0d%0aContent-Length:%200%0d%0a%0d%0a<script>alert('XSS')</script>";
        
        // Mock the behavior of the socket reading to return the malicious input
        try (SocketMock socketMock = new SocketMock(maliciousInput)) {
            // Act
            servlet.bad(request, response);

            // Assert
            // Verify that the response header is set with the unsanitized input
            verify(response).setHeader(eq("Location"), contains(maliciousInput));
        }
    }

    /**
     * A helper class to mock socket behavior for testing purposes.
     */
    private static class SocketMock implements AutoCloseable {
        private final Socket socket;
        private final BufferedReader readerBuffered;
        private final InputStreamReader readerInputStream;

        public SocketMock(String data) throws IOException {
            this.socket = mock(Socket.class);
            this.readerInputStream = new InputStreamReader(new ByteArrayInputStream(data.getBytes("UTF-8")));
            this.readerBuffered = new BufferedReader(readerInputStream);

            when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(data.getBytes("UTF-8")));
        }

        @Override
        public void close() throws Exception {
            readerBuffered.close();
            readerInputStream.close();
            socket.close();
        }
    }
}