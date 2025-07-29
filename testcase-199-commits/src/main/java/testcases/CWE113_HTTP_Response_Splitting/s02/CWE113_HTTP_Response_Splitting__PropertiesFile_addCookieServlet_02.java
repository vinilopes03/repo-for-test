/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__PropertiesFile_addCookieServlet_02.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-02.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: PropertiesFile Read data from a .properties file (in property named data)
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 02 Control flow: if(true) and if(false)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s02;
import testcasesupport.*;

import javax.servlet.http.*;

import java.util.Properties;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.logging.Level;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__PropertiesFile_addCookieServlet_02 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation from previous commit
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        data = "foo"; /* FIX: Use a hardcoded string */

        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        // Other good methods will be implemented in later commits
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}