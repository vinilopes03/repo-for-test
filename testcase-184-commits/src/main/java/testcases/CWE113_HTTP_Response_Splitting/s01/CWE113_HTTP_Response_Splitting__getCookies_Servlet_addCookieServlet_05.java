/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__getCookies_Servlet_addCookieServlet_05.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-05.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: getCookies_Servlet Read data from the first cookie using getCookies()
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 05 Control flow: if(privateTrue) and if(privateFalse)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__getCookies_Servlet_addCookieServlet_05 extends AbstractTestCaseServlet
{
    private boolean privateTrue = true;
    private boolean privateFalse = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (privateTrue)
        {
            data = ""; /* initialize data in case there are no cookies */
            Cookie cookieSources[] = request.getCookies();
            if (cookieSources != null)
            {
                data = cookieSources[0].getValue(); // POTENTIAL FLAW
            }
        }
        else
        {
            data = null;
        }

        if (privateTrue)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data); // POTENTIAL FLAW
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (privateFalse)
        {
            data = null; // Unused
        }
        else
        {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (privateTrue)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data); // POTENTIAL FLAW
                response.addCookie(cookieSink);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        // Further methods will be implemented later
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}