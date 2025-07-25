package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_04 extends AbstractTestCaseServlet {
    private static final long serialVersionUID = 1L;
    
    private static final boolean PRIVATE_STATIC_FINAL_TRUE = true;
    private static final boolean PRIVATE_STATIC_FINAL_FALSE = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_TRUE) {
            data = ""; // Initialize data
            try {
                File file = new File("C:\\data.txt");
                FileInputStream streamFileInput = new FileInputStream(file);
                InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                BufferedReader readerBuffered = new BufferedReader(readerInputStream);
                // POTENTIAL FLAW: Read data from a file
                data = readerBuffered.readLine();
                readerBuffered.close();
                readerInputStream.close();
                streamFileInput.close();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null;
        }

        if (PRIVATE_STATIC_FINAL_TRUE) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_FALSE) {
            data = null;
        } else {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (PRIVATE_STATIC_FINAL_TRUE) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_TRUE) {
            // FIX: Use a hardcoded string
            data = "foo";
        } else {
            data = null;
        }

        if (PRIVATE_STATIC_FINAL_TRUE) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_TRUE) {
            data = ""; // Initialize data
            try {
                File file = new File("C:\\data.txt");
                FileInputStream streamFileInput = new FileInputStream(file);
                InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                BufferedReader readerBuffered = new BufferedReader(readerInputStream);
                // POTENTIAL FLAW: Read data from a file
                data = readerBuffered.readLine();
                readerBuffered.close();
                readerInputStream.close();
                streamFileInput.close();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null;
        }

        if (PRIVATE_STATIC_FINAL_FALSE) {
            IO.writeLine("Benign, fixed string");
        } else {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_TRUE) {
            data = ""; // Initialize data
            try {
                File file = new File("C:\\data.txt");
                FileInputStream streamFileInput = new FileInputStream(file);
                InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                BufferedReader readerBuffered = new BufferedReader(readerInputStream);
                // POTENTIAL FLAW: Read data from a file
                data = readerBuffered.readLine();
                readerBuffered.close();
                readerInputStream.close();
                streamFileInput.close();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null;
        }

        if (PRIVATE_STATIC_FINAL_TRUE) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.addCookie(cookieSink);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}