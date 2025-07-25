package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            // POTENTIAL FLAW: Read data from an environment variable
            data = System.getenv("ADD");
        } else {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.addCookie(cookieSink);
            }
        } else {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            // FIX: Use a hardcoded string
            data = "foo";
        } else {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        } else {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            // POTENTIAL FLAW: Read data from an environment variable
            data = System.getenv("ADD");
        } else {
            // POTENTIAL FLAW: Read data from an environment variable
            data = System.getenv("ADD");
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.addCookie(cookieSink);
            }
        } else {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.addCookie(cookieSink);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}