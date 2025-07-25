/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_12.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-12.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: connect_tcp Read data using an outbound tcp connection
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 12 Control flow: if(IO.staticReturnsTrueOrFalse())
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

import java.util.logging.Level;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_12 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for bad()
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodG2B()
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodB2G()
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for good()
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}