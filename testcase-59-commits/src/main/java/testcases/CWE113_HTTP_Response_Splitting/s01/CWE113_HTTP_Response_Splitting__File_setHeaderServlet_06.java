/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__File_setHeaderServlet_06.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-06.tmpl.java
*/

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_06 extends AbstractTestCaseServlet
{
    private static final int PRIVATE_STATIC_FINAL_FIVE = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            FileInputStream streamFileInput = null;
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;
            try
            {
                streamFileInput = new FileInputStream(file);
                readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            finally
            {
                try { if (readerBuffered != null) readerBuffered.close(); }
                catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO); }
                try { if (readerInputStream != null) readerInputStream.close(); }
                catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO); }
                try { if (streamFileInput != null) streamFileInput.close(); }
                catch (IOException exceptIO) { IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO); }
            }
        }
        else
        {
            data = null; // INCIDENTAL: CWE 561 Dead Code
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE != 5)
        {
            data = null; // INCIDENTAL: CWE 561 Dead Code
        }
        else
        {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = "foo"; // FIX: Use a hardcoded string
        }
        else
        {
            data = null; // INCIDENTAL: CWE 561 Dead Code
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodB2G1()
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodB2G2()
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        goodG2B2(request, response);
        // Calls to other 'good' methods will be here
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}