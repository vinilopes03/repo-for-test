/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__Property_addCookieServlet_07.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-07.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: Property Read data from a system property
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 07 Control flow: if(privateFive==5) and if(privateFive!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s02;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Property_addCookieServlet_07 extends AbstractTestCaseServlet
{
    private int privateFive = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method implementation will be added in the next commit
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method implementation will be added later
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}