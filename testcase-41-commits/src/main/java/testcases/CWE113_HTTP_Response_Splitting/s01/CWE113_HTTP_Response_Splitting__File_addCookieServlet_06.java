// Previous code remains unchanged

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_06 extends AbstractTestCaseServlet {
    // Other code...

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE != 5) {
            data = null; // Dead code, will never run
        } else {
            data = "foo"; // Good source: hardcoded string
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink); // Potential flaw: Input not verified
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5) {
            data = "foo"; // Good source: hardcoded string
        } else {
            data = null; // Dead code, will never run
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink); // Potential flaw: Input not verified
            }
        }
    }

    // Other methods...
}