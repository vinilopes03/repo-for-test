package testcases.CWE113_HTTP_Response_Splitting.s02;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__getCookies_Servlet_setHeaderServlet_09 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_TRUE) {
            data = ""; /* initialize data in case there are no cookies */
            /* Read data from cookies */
            {
                Cookie cookieSources[] = request.getCookies();
                if (cookieSources != null) {
                    /* POTENTIAL FLAW: Read data from the first cookie value */
                    data = cookieSources[0].getValue();
                }
            }
        } else {
            data = null;
        }

        if (IO.STATIC_FINAL_TRUE) {
            if (data != null) {
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_FALSE) {
            data = null;
        } else {
            /* FIX: Use a hardcoded string */
            data = "foo";
        }

        if (IO.STATIC_FINAL_TRUE) {
            if (data != null) {
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_TRUE) {
            /* FIX: Use a hardcoded string */
            data = "foo";
        } else {
            data = null;
        }

        if (IO.STATIC_FINAL_TRUE) {
            if (data != null) {
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_TRUE) {
            data = ""; /* initialize data in case there are no cookies */
            /* Read data from cookies */
            {
                Cookie cookieSources[] = request.getCookies();
                if (cookieSources != null) {
                    /* POTENTIAL FLAW: Read data from the first cookie value */
                    data = cookieSources[0].getValue();
                }
            }
        } else {
            data = null;
        }

        if (IO.STATIC_FINAL_FALSE) {
            IO.writeLine("Benign, fixed string");
        } else {
            if (data != null) {
                /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}