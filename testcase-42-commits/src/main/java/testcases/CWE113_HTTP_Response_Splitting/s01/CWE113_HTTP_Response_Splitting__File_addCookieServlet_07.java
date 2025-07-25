/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__File_addCookieServlet_07.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-07.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: File Read data from file (named c:\data.txt)
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 07 Control flow: if(privateFive==5) and if(privateFive!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.IOException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_07 extends AbstractTestCaseServlet
{
    private int privateFive = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        // Method implementation to be added in later commits
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        // Method implementation to be added in later commits
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        // Method implementation to be added in later commits
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        // Method implementation to be added in later commits
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        // Method implementation to be added in later commits
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}

...
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        String data;
        if (privateFive == 5) 
        {
            data = ""; // Initialize data
            // Simulate reading data from a file
            try 
            {
                data = "Simulated file data"; // Placeholder for file read operation
            } 
            catch (Exception e) 
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", e);
            }
        }
        else 
        {
            data = null;
        }

        if (privateFive == 5) 
        {
            if (data != null) 
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }
...

...
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        String data;
        if (privateFive != 5) 
        {
            data = null;
        }
        else 
        {
            data = "foo"; // Use a hardcoded string
        }

        if (privateFive == 5) 
        {
            if (data != null) 
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }
...

...
    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        String data;
        if (privateFive == 5) 
        {
            data = "foo"; // Use a hardcoded string
        }
        else 
        {
            data = null;
        }

        if (privateFive == 5) 
        {
            if (data != null) 
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }
...

...
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        String data;
        if (privateFive == 5) 
        {
            data = ""; // Initialize data
            try 
            {
                data = "Simulated file data"; // Placeholder for file read operation
            } 
            catch (Exception e) 
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", e);
            }
        }
        else 
        {
            data = null;
        }

        if (privateFive != 5) 
        {
            IO.writeLine("Benign, fixed string");
        }
        else 
        {
            if (data != null) 
            {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable 
    {
        String data;
        if (privateFive == 5) 
        {
            data = ""; // Initialize data
            try 
            {
                data = "Simulated file data"; // Placeholder for file read operation
            } 
            catch (Exception e) 
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", e);
            }
        }
        else 
        {
            data = null;
        }

        if (privateFive == 5) 
        {
            if (data != null) 
            {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                response.addCookie(cookieSink);
            }
        }
    }
...