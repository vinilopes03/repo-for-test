package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.servlet.http.*;
import java.util.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    private static class MockHttpServletResponse implements HttpServletResponse {
        private final Map<String, String> headers = new HashMap<>();
        private String contentType;
        private int status;
        private Locale locale;

        @Override
        public void addCookie(Cookie cookie) {
            headers.put("Set-Cookie", cookie.getName() + "=" + cookie.getValue());
        }

        @Override
        public void addHeader(String name, String value) {
            headers.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            return headers.get(name);
        }

        @Override
        public Collection<String> getHeaderNames() {
            return headers.keySet();
        }

        @Override
        public Collection<String> getHeaders(String name) {
            return headers.containsKey(name) ? Collections.singletonList(headers.get(name)) : Collections.emptyList();
        }

        @Override
        public void setStatus(int sc) {
            this.status = sc;
        }

        @Override
        public void setStatus(int sc, String sm) {
            this.status = sc; // Ignore sm as it's deprecated but required for interface
        }

        @Override
        public int getStatus() {
            return status;
        }

        @Override
        public void setContentType(String type) {
            this.contentType = type;
        }

        @Override
        public String getContentType() {
            return contentType;
        }

        @Override
        public void setLocale(Locale loc) {
            this.locale = loc;
        }

        @Override
        public Locale getLocale() {
            return locale;
        }

        // Minimal implementation of required methods
        @Override public boolean containsHeader(String name) { return headers.containsKey(name); }
        @Override public String encodeURL(String url) { return url; }
        @Override public String encodeRedirectURL(String url) { return url; }
        @Override public String encodeUrl(String url) { return url; }
        @Override public String encodeRedirectUrl(String url) { return url; }
        @Override public void sendError(int sc, String msg) {}
        @Override public void sendError(int sc) {}
        @Override public void sendRedirect(String location) {}
        @Override public void setDateHeader(String name, long date) {}
        @Override public void addDateHeader(String name, long date) {}
        @Override public void setIntHeader(String name, int value) {}
        @Override public void addIntHeader(String name, int value) {}
        @Override public void setHeader(String name, String value) { headers.put(name, value); }
        @Override public void flushBuffer() {}
        @Override public void resetBuffer() {}
        @Override public boolean isCommitted() { return false; }
        @Override public void reset() {}
        @Override public void setBufferSize(int size) {}
        @Override public int getBufferSize() { return 0; }
        @Override public PrintWriter getWriter() { return null; }
        @Override public ServletOutputStream getOutputStream() { return null; }
        @Override public void setContentLength(int len) {}
        @Override public void setContentLengthLong(long len) {}
        @Override public void setCharacterEncoding(String charset) {}
    }

    @Test
    void testCWE113_HTTPResponseSplitting() {
        // Test for CWE-113: HTTP Response Splitting
        // Injects CRLF (%0d%0a) in input to check if it’s reflected in headers
        String maliciousInput = "en-US%0d%0aSet-Cookie: evil=1";
        System.setProperty("ADD", maliciousInput); // Simulate environment variable
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 instance = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        MockHttpServletResponse response = new MockHttpServletResponse();

        try {
            instance.bad(null, response); // Adjust method name if needed
            String header = response.getHeader("Set-Cookie");
            boolean isVulnerable = header != null && (header.contains("\r\n") || header.contains("%0d%0a"));
            assertTrue(isVulnerable, "Vulnerability present: CRLF was not sanitized from input");
        } catch (Exception e) {
            fail("Vulnerability fixed or not triggered: Exception occurred or CRLF was sanitized");
        }
    }
}