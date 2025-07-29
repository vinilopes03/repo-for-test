package testcases.CWE113_HTTP_Response_Splitting.s02;

import testcasesupport.AbstractTestCaseServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.net.URLEncoder;
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

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        /* FIX: Use a hardcoded string */
        data = "foo";

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
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

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
            /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
            response.addCookie(cookieSink);
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}