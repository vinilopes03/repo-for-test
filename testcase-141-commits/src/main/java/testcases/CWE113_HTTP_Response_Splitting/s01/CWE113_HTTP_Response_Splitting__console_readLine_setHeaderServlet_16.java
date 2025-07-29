/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__console_readLine_setHeaderServlet_16.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-16.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: console_readLine Read data from the console using readLine()
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 16 Control flow: while(true)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CWE113_HTTP_Response_Splitting__console_readLine_setHeaderServlet_16 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will go here
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will go here
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will go here
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will go here
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}