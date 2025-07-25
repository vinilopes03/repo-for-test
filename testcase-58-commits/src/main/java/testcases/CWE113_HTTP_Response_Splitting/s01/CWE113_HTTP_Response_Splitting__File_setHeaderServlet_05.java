package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_05 extends AbstractTestCaseServlet {

    private boolean privateTrue = true;
    private boolean privateFalse = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateTrue) {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            try (FileInputStream streamFileInput = new FileInputStream(file);
                 InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                data = readerBuffered.readLine(); // Potential flaw: Read data from a file
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code
        }

        if (privateTrue) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Potential flaw
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFalse) {
            data = null; // Dead code
        } else {
            data = "foo"; // Fixed: Use a hardcoded string
        }

        if (privateTrue) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Potential flaw
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateTrue) {
            data = "foo"; // Fixed: Use a hardcoded string
        } else {
            data = null; // Dead code
        }

        if (privateTrue) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // Potential flaw
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method to be implemented
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method to be implemented
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