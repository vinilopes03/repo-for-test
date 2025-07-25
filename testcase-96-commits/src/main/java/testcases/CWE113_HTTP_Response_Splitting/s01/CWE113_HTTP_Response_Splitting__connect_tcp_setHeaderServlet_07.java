package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_07 extends AbstractTestCaseServlet {
    private int privateFive = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {
                data = readerBuffered.readLine(); // Read data using an outbound TCP connection
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code
        }

        if (privateFive == 5) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Potential flaw: input not verified
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive != 5) {
            data = null; // Dead code
        } else {
            data = "foo"; // Good source
        }

        if (privateFive == 5) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Potential flaw: input not verified
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = "foo"; // Good source
        } else {
            data = null; // Dead code
        }

        if (privateFive == 5) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Potential flaw: input not verified
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {
                data = readerBuffered.readLine(); // Read data using an outbound TCP connection
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code
        }

        if (privateFive != 5) {
            IO.writeLine("Benign, fixed string");
        } else {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8"); // Good sink: URLEncode input
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = ""; // Initialize data
            try (Socket socket = new Socket("host.example.org", 39544);
                 BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {
                data = readerBuffered.readLine(); // Read data using an outbound TCP connection
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code
        }

        if (privateFive == 5) {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8"); // Good sink: URLEncode input
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

    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}