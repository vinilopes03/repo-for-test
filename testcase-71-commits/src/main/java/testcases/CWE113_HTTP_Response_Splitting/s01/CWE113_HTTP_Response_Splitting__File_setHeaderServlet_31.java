package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_31 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation as per previous commit
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation as per previous commit
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String dataCopy;
        {
            String data;
            data = ""; // Initialize data
            {
                File file = new File("C:\\data.txt");
                FileInputStream streamFileInput = null;
                InputStreamReader readerInputStream = null;
                BufferedReader readerBuffered = null;

                try {
                    streamFileInput = new FileInputStream(file);
                    readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                    readerBuffered = new BufferedReader(readerInputStream);

                    data = readerBuffered.readLine(); // POTENTIAL FLAW
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                } finally {
                    try {
                        if (readerBuffered != null) readerBuffered.close();
                        if (readerInputStream != null) readerInputStream.close();
                        if (streamFileInput != null) streamFileInput.close();
                    } catch (IOException exceptIO) {
                        IO.logger.log(Level.WARNING, "Error closing stream", exceptIO);
                    }
                }
            }
            dataCopy = data;
        }
        {
            String data = dataCopy;
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8"); // FIX: use URLEncoder.encode
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}