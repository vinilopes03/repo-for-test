package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            data = System.getenv("ADD");
        } else {
            data = "foo";
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        } else {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            data = "foo";
        } else {
            data = "foo";
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        } else {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}