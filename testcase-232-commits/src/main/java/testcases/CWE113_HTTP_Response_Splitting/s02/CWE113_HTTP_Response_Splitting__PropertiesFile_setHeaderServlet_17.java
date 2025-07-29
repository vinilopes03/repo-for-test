/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__PropertiesFile_setHeaderServlet_17.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-17.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: PropertiesFile Read data from a .properties file (in property named data)
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 17 Control flow: for loops
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

public class CWE113_HTTP_Response_Splitting__PropertiesFile_setHeaderServlet_17 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = ""; // Initialize data

        // Retrieve the property
        Properties properties = new Properties();
        FileInputStream streamFileInput = null;

        try
        {
            streamFileInput = new FileInputStream("../common/config.properties");
            properties.load(streamFileInput);
            data = properties.getProperty("data"); // POTENTIAL FLAW
        }
        catch (IOException exceptIO)
        {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        }
        finally
        {
            if (streamFileInput != null)
            {
                streamFileInput.close();
            }
        }

        for (int j = 0; j < 1; j++)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

        // FIX: Use a hardcoded string
        data = "foo";

        for (int j = 0; j < 1; j++)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Still a potential flaw
            }
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = ""; // Initialize data

        // Retrieve the property
        Properties properties = new Properties();
        FileInputStream streamFileInput = null;

        try
        {
            streamFileInput = new FileInputStream("../common/config.properties");
            properties.load(streamFileInput);
            data = properties.getProperty("data"); // POTENTIAL FLAW
        }
        catch (IOException exceptIO)
        {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        }
        finally
        {
            if (streamFileInput != null)
            {
                streamFileInput.close();
            }
        }

        for (int k = 0; k < 1; k++)
        {
            if (data != null)
            {
                data = URLEncoder.encode(data, "UTF-8"); // FIX: URL encode
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}