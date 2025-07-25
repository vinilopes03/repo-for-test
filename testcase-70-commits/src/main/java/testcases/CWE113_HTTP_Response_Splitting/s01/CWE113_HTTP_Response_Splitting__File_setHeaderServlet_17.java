package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_17 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = ""; // Initialize data

        File file = new File("C:\\data.txt");
        FileInputStream streamFileInput = null;
        InputStreamReader readerInputStream = null;
        BufferedReader readerBuffered = null;

        try {
            streamFileInput = new FileInputStream(file);
            readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
            readerBuffered = new BufferedReader(readerInputStream);

            // POTENTIAL FLAW: Read data from a file
            data = readerBuffered.readLine();
        } catch (IOException exceptIO) {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        } finally {
            try {
                if (readerBuffered != null) readerBuffered.close();
                if (readerInputStream != null) readerInputStream.close();
                if (streamFileInput != null) streamFileInput.close();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error closing resources", exceptIO);
            }
        }

        if (data != null) {
            // POTENTIAL FLAW: Input not verified before inclusion in header
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = "foo"; // FIX: Use a hardcoded string

        if (data != null) {
            // POTENTIAL FLAW: Input not verified before inclusion in header
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}