/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__getCookies_Servlet_addCookieServlet_14.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-14.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: getCookies_Servlet Read data from the first cookie using getCookies()
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 14 Control flow: if(IO.staticFive==5) and if(IO.staticFive!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__getCookies_Servlet_addCookieServlet_14 extends AbstractTestCaseServlet {
    
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive == 5) {
            data = ""; /* initialize data in case there are no cookies */
            Cookie cookieSources[] = request.getCookies();
            if (cookieSources != null && cookieSources.length > 0) {
                /* POTENTIAL FLAW: Read data from the first cookie value */
                data = cookieSources[0].getValue();
            }
        } else {
            data = null; // This case won't run
        }

        if (IO.staticFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive != 5) {
            data = null; // This case won't run
        } else {
            /* FIX: Use a hardcoded string */
            data = "foo";
        }

        if (IO.staticFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive == 5) {
            /* FIX: Use a hardcoded string */
            data = "foo";
        } else {
            data = null; // This case won't run
        }

        if (IO.staticFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                response.addCookie(cookieSink);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}