package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_05 extends AbstractTestCaseServlet {
    private static final long serialVersionUID = 1L;

    private boolean privateTrue = true;
    private boolean privateFalse = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateTrue) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data using an outbound tcp connection
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // This block will never be executed
        }

        if (privateTrue) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFalse) {
            data = null; // This block will never be executed
        } else {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (privateTrue) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateTrue) {
            data = "foo"; // FIX: Use a hardcoded string
        } else {
            data = null; // This block will never be executed
        }

        if (privateTrue) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateTrue) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data using an outbound tcp connection
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // This block will never be executed
        }

        if (privateFalse) {
            IO.writeLine("Benign, fixed string"); // This block will never be executed
        } else {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8"); // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateTrue) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data using an outbound tcp connection
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // This block will never be executed
        }

        if (privateTrue) {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8"); // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
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