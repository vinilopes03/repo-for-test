package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    private static class MockHttpServletResponse implements HttpServletResponse {
        private String header;

        @Override
        public void addCookie(Cookie cookie) {
            header = "Set-Cookie: " + cookie.getName() + "=" + cookie.getValue();
        }

        public String getHeader() {
            return header;
        }

        // Other methods are not implemented for brevity
        @Override public void addHeader(String name, String value) {}
        @Override public void addIntHeader(String name, int value) {}
        @Override public boolean containsHeader(String name) { return false; }
        @Override public String encodeRedirectURL(String url) { return null; }
        @Override public String encodeRedirectUrl(String url) { return null; }
        @Override public String encodeURL(String url) { return null; }
        @Override public String encodeUrl(String url) { return null; }
        @Override public void sendError(int sc) {}
        @Override public void sendError(int sc, String msg) {}
        @Override public void sendRedirect(String location) {}
        @Override public void setDateHeader(String name, long date) {}
        @Override public void setHeader(String name, String value) {}
        @Override public void setIntHeader(String name, int value) {}
        @Override public void setStatus(int sc) {}
        @Override public void setStatus(int sc, String sm) {}
        @Override public void flushBuffer() {}
        @Override public int getBufferSize() { return 0; }
        @Override public String getCharacterEncoding() { return null; }
        @Override public String getContentType() { return null; }
        @Override public Locale getLocale() { return null; }
        @Override public ServletOutputStream getOutputStream() { return null; }
        @Override public PrintWriter getWriter() { return null; }
        @Override public boolean isCommitted() { return false; }
        @Override public void reset() {}
        @Override public void resetBuffer() {}
        @Override public void setBufferSize(int size) {}
        @Override public void setCharacterEncoding(String charset) {}
        @Override public void setContentLength(int len) {}
        @Override public void setContentLengthLong(long len) {}
        @Override public void setContentType(String type) {}
        @Override public void setLocale(Locale loc) {}
    }

    @Test
    void testCWE113_HTTPResponseSplitting() {
        // CWE-113: HTTP Response Splitting
        // Inject CRLF (%0d%0a) in input. If it's reflected in headers unescaped, vulnerability is present.
        String maliciousInput = "en-US%0d%0aSet-Cookie: evil=1";
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 instance = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        MockHttpServletResponse response = new MockHttpServletResponse();

        try {
            instance.bad(null, response); // Simulate the bad method execution

            String header = response.getHeader();
            boolean isVulnerable = header != null && (header.contains("\r\n") || header.contains("%0d%0a"));
            assertTrue(isVulnerable, "Vulnerability present: CRLF was not sanitized from input.");
        } catch (Exception e) {
            fail("Vulnerability fixed or not triggered: Exception occurred or CRLF was sanitized.");
        }
    }
}