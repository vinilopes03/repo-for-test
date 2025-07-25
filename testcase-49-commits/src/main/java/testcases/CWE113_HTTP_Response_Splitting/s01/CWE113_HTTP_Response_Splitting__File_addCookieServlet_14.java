package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_14 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive == 5) {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            FileInputStream streamFileInput = null;
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;
            try {
                streamFileInput = new FileInputStream(file);
                readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            } finally {
                try {
                    if (readerBuffered != null) {
                        readerBuffered.close();
                    }
                    if (readerInputStream != null) {
                        readerInputStream.close();
                    }
                    if (streamFileInput != null) {
                        streamFileInput.close();
                    }
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing streams", exceptIO);
                }
            }
        } else {
            data = null;
        }

        if (IO.staticFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                response.addCookie(cookieSink); // FIX: use URLEncoder.encode
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive != 5) {
            data = null;
        } else {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (IO.staticFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink); // POTENTIAL FLAW
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive == 5) {
            data = "foo"; // FIX: Use a hardcoded string
        } else {
            data = null;
        }

        if (IO.staticFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink); // POTENTIAL FLAW
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive == 5) {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            FileInputStream streamFileInput = null;
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;
            try {
                streamFileInput = new FileInputStream(file);
                readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            } finally {
                try {
                    if (readerBuffered != null) {
                        readerBuffered.close();
                    }
                    if (readerInputStream != null) {
                        readerInputStream.close();
                    }
                    if (streamFileInput != null) {
                        streamFileInput.close();
                    }
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing streams", exceptIO);
                }
            }
        } else {
            data = null;
        }

        if (IO.staticFive != 5) {
            IO.writeLine("Benign, fixed string");
        } else {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                response.addCookie(cookieSink); // FIX: use URLEncoder.encode
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive == 5) {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            FileInputStream streamFileInput = null;
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;
            try {
                streamFileInput = new FileInputStream(file);
                readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);
                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            } finally {
                try {
                    if (readerBuffered != null) {
                        readerBuffered.close();
                    }
                    if (readerInputStream != null) {
                        readerInputStream.close();
                    }
                    if (streamFileInput != null) {
                        streamFileInput.close();
                    }
                } catch (IOException exceptIO) {
                    IO.logger.log(Level.WARNING, "Error closing streams", exceptIO);
                }
            }
        } else {
            data = null;
        }

        if (IO.staticFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                response.addCookie(cookieSink); // FIX: use URLEncoder.encode
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