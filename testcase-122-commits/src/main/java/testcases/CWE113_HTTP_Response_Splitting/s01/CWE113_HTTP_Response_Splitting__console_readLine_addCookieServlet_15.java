/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__console_readLine_addCookieServlet_15.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-15.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: console_readLine Read data from the console using readLine()
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 15 Control flow: switch(6) and switch(7)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__console_readLine_addCookieServlet_15 extends AbstractTestCaseServlet {
    // Class implementation will be added in subsequent commits
}

public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;

    switch (6) {
    case 6:
        data = ""; /* Initialize data */
        {
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;
            /* read user input from console with readLine */
            try {
                readerInputStream = new InputStreamReader(System.in, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);
                /* POTENTIAL FLAW: Read data from the console using readLine */
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            } finally {
                try {
                    if (readerBuffered != null) {
                        readerBuffered.close();
                    }
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                }

                try {
                    if (readerInputStream != null) {
                        readerInputStream.close();
                    }
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                }
            }
        }
        break;
    default:
        data = null;
        break;
    }

    switch (7) {
    case 7:
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
        break;
    default:
        IO.writeLine("Benign, fixed string");
        break;
    }
}

private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;

    switch (5) {
    case 6:
        data = null;
        break;
    default:
        /* FIX: Use a hardcoded string */
        data = "foo";
        break;
    }

    switch (7) {
    case 7:
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
        break;
    default:
        IO.writeLine("Benign, fixed string");
        break;
    }
}

private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;

    switch (6) {
    case 6:
        data = ""; /* Initialize data */
        {
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;
            /* read user input from console with readLine */
            try {
                readerInputStream = new InputStreamReader(System.in, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);
                /* POTENTIAL FLAW: Read data from the console using readLine */
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            } finally {
                try {
                    if (readerBuffered != null) {
                        readerBuffered.close();
                    }
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                }

                try {
                    if (readerInputStream != null) {
                        readerInputStream.close();
                    }
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                }
            }
        }
        break;
    default:
        data = null;
        break;
    }

    switch (8) {
    case 7:
        IO.writeLine("Benign, fixed string");
        break;
    default:
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
            /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
            response.addCookie(cookieSink);
        }
        break;
    }
}