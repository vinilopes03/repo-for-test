/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__database_addCookieServlet_06.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-06.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: database Read data from a database
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 06 Control flow: if(PRIVATE_STATIC_FINAL_FIVE==5) and if(PRIVATE_STATIC_FINAL_FIVE!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__database_addCookieServlet_06 extends AbstractTestCaseServlet
{
    private static final int PRIVATE_STATIC_FINAL_FIVE = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation from previous commits
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        goodB2G1(request, response);
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation from previous commit
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE == 5)
        {
            data = ""; /* Initialize data */
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                connection = IO.getDBConnection();
                preparedStatement = connection.prepareStatement("select name from users where id=0");
                resultSet = preparedStatement.executeQuery();
                data = resultSet.getString(1); // Read data from DB
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error with SQL statement", exceptSql);
            }
            finally
            {
                // Close database objects
                try { if (resultSet != null) resultSet.close(); } catch (SQLException exceptSql) {}
                try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException exceptSql) {}
                try { if (connection != null) connection.close(); } catch (SQLException exceptSql) {}
            }
        }
        else
        {
            data = null;
        }

        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8")); // FIX: use URLEncoder.encode
            response.addCookie(cookieSink);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}