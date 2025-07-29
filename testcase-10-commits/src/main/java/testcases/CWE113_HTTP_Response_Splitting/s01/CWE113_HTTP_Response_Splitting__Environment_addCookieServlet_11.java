/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-11.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: Environment Read data from an environment variable
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 11 Control flow: if(IO.staticReturnsTrue()) and if(IO.staticReturnsFalse())
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsTrue())
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = null; // Ensure data is initialized
        }

        if(IO.staticReturnsTrue())
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8")); // Proper encoding
                response.addCookie(cookieSink); // Fixed potential flaw
            }
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = "foo"; // Hardcoded string

        if (IO.staticReturnsTrue())
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink); // Potential flaw remains
            }
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsTrue())
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = null; // Ensure data is initialized
        }

        if (IO.staticReturnsTrue())
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8")); // Proper encoding
                response.addCookie(cookieSink);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}