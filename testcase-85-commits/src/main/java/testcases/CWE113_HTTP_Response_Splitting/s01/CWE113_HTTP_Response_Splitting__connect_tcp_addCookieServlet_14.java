package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_14 extends AbstractTestCaseServlet {
    // bad method...
    // goodG2B1 method...

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive == 5) {
            // FIX: Use a hardcoded string
            data = "foo";
        } else {
            // Ensure data is initialized before the Sink to avoid compiler errors
            data = null;
        }

        if (IO.staticFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        }
    }

    // Other methods...
}