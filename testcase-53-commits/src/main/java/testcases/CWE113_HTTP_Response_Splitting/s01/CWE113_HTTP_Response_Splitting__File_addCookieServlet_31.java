// Initial setup of the project with support classes
package testcasesupport;

import java.io.*;
import java.sql.*;
import java.util.logging.Logger;

public class IO 
{ 
    public static final Logger logger = Logger.getLogger("testcases");

    public static void writeLine(String line) 
    {
        System.out.println(line);
    }
}