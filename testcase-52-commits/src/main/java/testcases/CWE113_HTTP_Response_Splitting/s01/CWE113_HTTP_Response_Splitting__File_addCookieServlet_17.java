package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_17 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation as in previous commits
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation as in previous commits
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = "";

        File file = new File("C:\\data.txt");
        FileInputStream streamFileInput = null;
        InputStreamReader readerInputStream = null;
        BufferedReader readerBuffered = null;

        try {
            streamFileInput = new FileInputStream(file);
            readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
            readerBuffered = new BufferedReader(readerInputStream);

            data = readerBuffered.readLine();
        } catch (IOException exceptIO) {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        } finally {
            try {
                if (readerBuffered != null) readerBuffered.close();
                if (readerInputStream != null) readerInputStream.close();
                if (streamFileInput != null) streamFileInput.close();
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error closing streams", exceptIO);
            }
        }

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
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