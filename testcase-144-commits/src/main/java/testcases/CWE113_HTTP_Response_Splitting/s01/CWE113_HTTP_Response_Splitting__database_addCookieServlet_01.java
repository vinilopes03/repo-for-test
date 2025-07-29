/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__database_addCookieServlet_01.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-01.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: database Read data from a database
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 01 Baseline
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

public class CWE113_HTTP_Response_Splitting__database_addCookieServlet_01 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = ""; /* Initialize data */
        /* Read data from a database */
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connection = IO.getDBConnection();
            preparedStatement = connection.prepareStatement("select name from users where id=0");
            resultSet = preparedStatement.executeQuery();
            data = resultSet.getString(1); // POTENTIAL FLAW
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

        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", data); // POTENTIAL FLAW
            response.addCookie(cookieSink);
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = "foo"; // FIX: Use a hardcoded string
        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", data); // Still a potential flaw
            response.addCookie(cookieSink);
        }
        
        goodB2G(request, response);
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = ""; /* Initialize data */
        /* Read data from a database */
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connection = IO.getDBConnection();
            preparedStatement = connection.prepareStatement("select name from users where id=0");
            resultSet = preparedStatement.executeQuery();
            data = resultSet.getString(1); // POTENTIAL FLAW
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

        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8")); // FIX: URL encode the data
            response.addCookie(cookieSink);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}