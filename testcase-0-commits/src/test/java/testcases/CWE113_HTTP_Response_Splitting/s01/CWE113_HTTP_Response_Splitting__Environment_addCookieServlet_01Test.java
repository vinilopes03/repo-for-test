package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.servlet.http.*;
import java.util.*;
import java.lang.reflect.Field;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    private static class MockHttpServletResponse implements HttpServletResponse {
        private final Map<String, String> headers = new HashMap<>();
        private String contentType;
        private int status;
        private Locale locale;
        private String characterEncoding = "UTF-8";

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
            this.status = sc;
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

        @Override
        public String getCharacterEncoding() {
            return characterEncoding;
        }

        @Override
        public void setCharacterEncoding(String charset) {
            this.characterEncoding = charset;
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
    }

    @Test
    void testCWE113_HTTPResponseSplitting() throws Exception {
        String maliciousInput = "en-US%0d%0aSet-Cookie: evil=1";
        // Note: For Java 17+, add to maven-surefire-plugin configuration in pom.xml:
        // <configuration>
        //   <argLine>--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED</argLine>
        // </configuration>
        // to allow reflection on internal JDK fields for setting environment variables.
        Class<?> pe = Class.forName("java.lang.ProcessEnvironment");
        Field envField = pe.getDeclaredField("theEnvironment");
        envField.setAccessible(true);
        Object unmodEnv = envField.get(null);
        Field mField = unmodEnv.getClass().getDeclaredField("m");
        mField.setAccessible(true);
        Map<String, String> writableEnv = (Map<String, String>) mField.get(unmodEnv);
        writableEnv.put("ADD", maliciousInput);

        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 instance = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        MockHttpServletResponse response = new MockHttpServletResponse();

        try {
            instance.bad(null, response);
        } catch (Throwable t) {
            fail("Vulnerability fixed or not triggered: Exception occurred or CRLF was sanitized");
        }

        String header = response.getHeader("Set-Cookie");
        boolean isVulnerable = header != null && (header.contains("\r\n") || header.contains("%0d%0a"));
        assertTrue(isVulnerable, "Vulnerability fixed or not triggered: CRLF was sanitized or not reflected");
    }
}