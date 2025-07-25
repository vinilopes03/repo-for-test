package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_07 extends AbstractTestCaseServlet {

    private int privateFive = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {
                // Read data using an outbound tcp connection
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code path
        }

        if (privateFive == 5) {
            if (data != null) {
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive != 5) {
            data = null; // Dead code path
        } else {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (privateFive == 5) {
            if (data != null) {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            // FIX: Use a hardcoded string
            data = "foo";
        } else {
            data = null; // Dead code path
        }

        if (privateFive == 5) {
            if (data != null) {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {
                // Read data using an outbound tcp connection
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code path
        }

        if (privateFive != 5) {
            // Dead code
            IO.writeLine("Benign, fixed string");
        } else {
            if (data != null) {
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 InputStreamReader readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {
                // Read data using an outbound tcp connection
                data = readerBuffered.readLine();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code path
        }

        if (privateFive == 5) {
            if (data != null) {
                // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
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