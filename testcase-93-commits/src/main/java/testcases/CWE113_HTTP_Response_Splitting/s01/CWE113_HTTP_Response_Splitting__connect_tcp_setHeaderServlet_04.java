// Full class with `goodG2B1` and `goodG2B2` method implementations
public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_04 extends AbstractTestCaseServlet {
    private static final boolean PRIVATE_STATIC_FINAL_TRUE = true;
    private static final boolean PRIVATE_STATIC_FINAL_FALSE = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Existing implementation
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_FALSE) {
            data = null;
        } else {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (PRIVATE_STATIC_FINAL_TRUE) {
            if (data != null) {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_TRUE) {
            // FIX: Use a hardcoded string
            data = "foo";
        } else {
            data = null;
        }

        if (PRIVATE_STATIC_FINAL_TRUE) {
            if (data != null) {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    // Other methods remain as placeholders
}