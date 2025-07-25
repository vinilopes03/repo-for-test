package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05 extends AbstractTestCaseServlet {
    private boolean privateTrue = true;
    private boolean privateFalse = false;

    // Previously implemented methods omitted for brevity

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFalse) {
            data = null;
        } else {
            data = "foo";
        }

        if (privateTrue) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }

    // Other methods omitted for brevity
}