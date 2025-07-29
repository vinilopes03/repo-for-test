public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11 extends AbstractTestCaseServlet
{
    // ... (bad method and good methods here)

    /* goodB2G1() - use badsource and goodsink by changing second IO.staticReturnsTrue() to IO.staticReturnsFalse() */
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsTrue()) 
        {
            data = ""; /* Initialize data */
            {
                File file = new File("C:\\data.txt");
                FileInputStream streamFileInput = null;
                InputStreamReader readerInputStream = null;
                BufferedReader readerBuffered = null;
                try 
                {
                    /* read string from file into data */
                    streamFileInput = new FileInputStream(file);
                    readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                    readerBuffered = new BufferedReader(readerInputStream);
                    data = readerBuffered.readLine();
                } 
                catch (IOException exceptIO) 
                {
                    IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                } 
                finally 
                {
                    try 
                    {
                        if (readerBuffered != null) 
                        {
                            readerBuffered.close();
                        }
                    }
                    catch (IOException exceptIO) 
                    {
                        IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                    }
                    try 
                    {
                        if (readerInputStream != null) 
                        {
                            readerInputStream.close();
                        }
                    } 
                    catch (IOException exceptIO) 
                    {
                        IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                    }
                    try 
                    {
                        if (streamFileInput != null) 
                        {
                            streamFileInput.close();
                        }
                    } 
                    catch (IOException exceptIO) 
                    {
                        IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO);
                    }
                }
            }
        } 
        else 
        {
            data = null; // Initialize to avoid compiler error
        }

        if (IO.staticReturnsFalse()) 
        {
            IO.writeLine("Benign, fixed string");
        } 
        else 
        {
            if (data != null) 
            {
                /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    /* goodB2G2() - use badsource and goodsink by reversing statements in second if */
    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsTrue()) 
        {
            data = ""; /* Initialize data */
            {
                File file = new File("C:\\data.txt");
                FileInputStream streamFileInput = null;
                InputStreamReader readerInputStream = null;
                BufferedReader readerBuffered = null;
                try 
                {
                    streamFileInput = new FileInputStream(file);
                    readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                    readerBuffered = new BufferedReader(readerInputStream);
                    data = readerBuffered.readLine();
                } 
                catch (IOException exceptIO) 
                {
                    IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                } 
                finally 
                {
                    try 
                    {
                        if (readerBuffered != null) 
                        {
                            readerBuffered.close();
                        }
                    } 
                    catch (IOException exceptIO) 
                    {
                        IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                    }
                    try 
                    {
                        if (readerInputStream != null) 
                        {
                            readerInputStream.close();
                        }
                    } 
                    catch (IOException exceptIO) 
                    {
                        IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                    }
                    try 
                    {
                        if (streamFileInput != null) 
                        {
                            streamFileInput.close();
                        }
                    } 
                    catch (IOException exceptIO) 
                    {
                        IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO);
                    }
                }
            }
        } 
        else 
        {
            data = null; // Initialize to avoid compiler error
        }

        if (IO.staticReturnsTrue()) 
        {
            if (data != null) 
            {
                /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }
}