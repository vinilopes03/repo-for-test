/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__PropertiesFile_setHeaderServlet_14.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-14.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: PropertiesFile Read data from a .properties file (in property named data)
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 14 Control flow: if(IO.staticFive==5) and if(IO.staticFive!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s02;
import testcasesupport.*;

import javax.servlet.http.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__PropertiesFile_setHeaderServlet_14 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticFive == 5)
        {
            data = ""; /* Initialize data */
            /* Retrieve the property */
            Properties properties = new Properties();
            FileInputStream streamFileInput = null;
            try
            {
                streamFileInput = new FileInputStream("../common/config.properties");
                properties.load(streamFileInput);
                /* POTENTIAL FLAW: Read data from a .properties file */
                data = properties.getProperty("data");
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            finally
            {
                /* Close stream reading object */
                if (streamFileInput != null)
                {
                    try
                    {
                        streamFileInput.close();
                    }
                    catch (IOException exceptIO)
                    {
                        IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO);
                    }
                }
            }
        }
        else
        {
            data = null; // Dead code
        }

        if (IO.staticFive == 5)
        {
            if (data != null)
            {
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // This method will be implemented later
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}