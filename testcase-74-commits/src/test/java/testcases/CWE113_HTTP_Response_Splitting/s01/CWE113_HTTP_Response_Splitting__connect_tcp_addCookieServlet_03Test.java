package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_03Test {

    /**
     * This test method verifies the presence of an HTTP Response Splitting vulnerability.
     * It does so by simulating a scenario where unsanitized input containing CRLF characters
     * is added to a cookie. If the vulnerability exists, the test will pass by confirming
     * that the malicious input is directly added to the cookie without sanitization.
     */
    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_03 servlet = new CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_03();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate malicious input containing CRLF characters
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";

        // Mock the behavior of reading from a socket to return the malicious input
        try (SocketMock socketMock = new SocketMock(maliciousInput)) {
            // Act
            servlet.bad(request, response);

            // Assert
            // Verify that the cookie was added with the malicious input
            verify(response, times(1)).addCookie(argThat(cookie -> {
                String value = cookie.getValue();
                return value.equals(maliciousInput);
            }));
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
            when(socket.getInputStream()).thenReturn(new ReaderInputStream(reader));
        }

        @Override
        public void close() throws Exception {
            reader.close();
        }
    }
}