/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__getParameter_Servlet_addCookieServlet_02.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-02.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: getParameter_Servlet Read data from a querystring using getParameter()
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

public class CWE113_HTTP_Response_Splitting__getParameter_Servlet_addCookieServlet_02 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Initial implementation
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Initial implementation
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}