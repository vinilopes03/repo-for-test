public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_11 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
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
                    /* POTENTIAL FLAW: Read data from a file */
                    data = readerBuffered.readLine();
                } 
                catch (IOException exceptIO) 
                {
                    IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                } 
                finally 
                {
                    /* Close stream reading objects */
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
            data = null; // Initialize data to avoid compiler error
        }

        if (IO.staticReturnsTrue())
        {
            if (data != null)
            {
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }
}