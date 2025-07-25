package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_03 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation from previous commit
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (5!=5) {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            data = null;
        } else {
            /* FIX: Use a hardcoded string */
            data = "foo";
        }

        if (5==5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (5==5) {
            /* FIX: Use a hardcoded string */
            data = "foo";
        } else {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            data = null;
        }

        if (5==5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                response.addCookie(cookieSink);
            }
        }
    }

    // Method implementations for goodB2G1 and goodB2G2 will follow in subsequent commits

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}