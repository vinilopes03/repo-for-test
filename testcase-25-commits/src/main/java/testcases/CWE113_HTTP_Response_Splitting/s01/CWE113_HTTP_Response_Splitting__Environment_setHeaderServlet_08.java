package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;

import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08 extends AbstractTestCaseServlet {
    private boolean privateReturnsTrue() {
        return true;
    }

    private boolean privateReturnsFalse() {
        return false;
    }

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateReturnsTrue()) {
            data = System.getenv("ADD");
        } else {
            data = null;
        }

        if (privateReturnsTrue()) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    /* goodG2B1() - use goodsource and badsink by changing first privateReturnsTrue() to privateReturnsFalse() */
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateReturnsFalse()) {
            data = null; // Will not be used
        } else {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (privateReturnsTrue()) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    /* goodB2G1() - use badsource and goodsink by changing second privateReturnsTrue() to privateReturnsFalse() */
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateReturnsTrue()) {
            data = System.getenv("ADD");
        } else {
            data = null;
        }

        if (privateReturnsFalse()) {
            IO.writeLine("Benign, fixed string");
        } else {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8"); // FIX: URL encode
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodB2G1(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}