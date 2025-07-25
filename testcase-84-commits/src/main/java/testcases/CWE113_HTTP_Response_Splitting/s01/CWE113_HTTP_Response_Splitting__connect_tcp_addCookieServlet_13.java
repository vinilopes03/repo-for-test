package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_13 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for bad scenario
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodG2B1 scenario
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodG2B2 scenario
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodB2G1 scenario
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodB2G2 scenario
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Placeholder for calling good scenarios
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}

public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;
    if (IO.STATIC_FINAL_FIVE == 5) {
        data = ""; // Initialize data
        // Read data using an outbound TCP connection
        try (Socket socket = new Socket("host.example.org", 39544);
             InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
             BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

            // POTENTIAL FLAW: Read data using an outbound TCP connection
            data = readerBuffered.readLine();
        } catch (IOException exceptIO) {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        }
    } else {
        // INCIDENTAL: CWE 561 Dead Code, the code below will never run
        data = null;
    }

    if (IO.STATIC_FINAL_FIVE == 5) {
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            response.addCookie(cookieSink);
        }
    }
}

private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;
    if (IO.STATIC_FINAL_FIVE != 5) {
        // INCIDENTAL: CWE 561 Dead Code, the code below will never run
        data = null;
    } else {
        // FIX: Use a hardcoded string
        data = "foo";
    }

    if (IO.STATIC_FINAL_FIVE == 5) {
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            response.addCookie(cookieSink);
        }
    }
}

private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;
    if (IO.STATIC_FINAL_FIVE == 5) {
        // FIX: Use a hardcoded string
        data = "foo";
    } else {
        // INCIDENTAL: CWE 561 Dead Code, the code below will never run
        data = null;
    }

    if (IO.STATIC_FINAL_FIVE == 5) {
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            response.addCookie(cookieSink);
        }
    }
}

private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String data;
    if (IO.STATIC_FINAL_FIVE == 5) {
        data = ""; // Initialize data
        // Read data using an outbound TCP connection
        try (Socket socket = new Socket("host.example.org", 39544);
             InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
             BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

            // POTENTIAL FLAW: Read data using an outbound TCP connection
            data = readerBuffered.readLine();
        } catch (IOException exceptIO) {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        }
    } else {
        // INCIDENTAL: CWE 561 Dead Code, the code below will never run
        data = null;
    }

    if (IO.STATIC_FINAL_FIVE != 5) {
        // INCIDENTAL: CWE 561 Dead Code, the code below will never run
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
    if (IO.STATIC_FINAL_FIVE == 5) {
        data = ""; // Initialize data
        // Read data using an outbound TCP connection
        try (Socket socket = new Socket("host.example.org", 39544);
             InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
             BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

            // POTENTIAL FLAW: Read data using an outbound TCP connection
            data = readerBuffered.readLine();
        } catch (IOException exceptIO) {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        }
    } else {
        // INCIDENTAL: CWE 561 Dead Code, the code below will never run
        data = null;
    }

    if (IO.STATIC_FINAL_FIVE == 5) {
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