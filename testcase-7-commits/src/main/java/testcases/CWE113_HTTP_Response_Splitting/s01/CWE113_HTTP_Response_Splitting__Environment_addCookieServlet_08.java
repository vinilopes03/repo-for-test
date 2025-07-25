/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-08.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: Environment Read data from an environment variable
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 08 Control flow: if(privateReturnsTrue()) and if(privateReturnsFalse())
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08 extends AbstractTestCaseServlet
{
    private boolean privateReturnsTrue() {
        return true;
    }

    private boolean privateReturnsFalse() {
        return false;
    }

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commit
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commit
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commit
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commit
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commit
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}

// Previous code remains unchanged

public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;
    if (privateReturnsTrue()) {
        /* get environment variable ADD */
        /* POTENTIAL FLAW: Read data from an environment variable */
        data = System.getenv("ADD");
    } else {
        data = null; // Dead code, but ensures data is initialized
    }

    if (privateReturnsTrue()) {
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
    }
}

// Previous code remains unchanged

private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;
    if (privateReturnsFalse()) {
        data = null; // Dead code
    } else {
        /* FIX: Use a hardcoded string */
        data = "foo";
    }

    if (privateReturnsTrue()) {
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
    }
}

private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;
    if (privateReturnsTrue()) {
        /* FIX: Use a hardcoded string */
        data = "foo";
    } else {
        data = null; // Dead code
    }

    if (privateReturnsTrue()) {
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
    }
}