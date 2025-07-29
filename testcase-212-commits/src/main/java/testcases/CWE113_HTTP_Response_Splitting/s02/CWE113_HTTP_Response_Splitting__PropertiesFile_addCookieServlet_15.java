package testcases.CWE113_HTTP_Response_Splitting.s02;

import testcasesupport.AbstractTestCaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import testcasesupport.IO;

public class CWE113_HTTP_Response_Splitting__PropertiesFile_addCookieServlet_15 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        data = ""; /* Initialize data */
        /* retrieve the property */
        {
            Properties properties = new Properties();
            FileInputStream streamFileInput = null;
            try {
                streamFileInput = new FileInputStream("../common/config.properties");
                properties.load(streamFileInput);
                /* POTENTIAL FLAW: Read data from a .properties file */
                data = properties.getProperty("data");
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            } finally {
                /* Close stream reading object */
                try {
                    if (streamFileInput != null) {
                        streamFileInput.close();
                    }
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO);
                }
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation to be added
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}