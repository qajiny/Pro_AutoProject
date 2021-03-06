package PlatformLayer;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest{

   /* Get actual class name to be printed on */
   static Logger log = Logger.getLogger(Log4jTest.class.getName());
   
   public static void main(String[] args)throws IOException,SQLException{
	   
	   //BasicConfigurator.configure();
	   /*
	   Properties props = new Properties();
	   props.load(new FileInputStream("C:\\Selenium\\Workspace\\Auto_Project\\Logs\\log4j.properties"));
	   props.setProperty("log4j.appender.File.File", "C:\\Selenium\\Workspace\\Auto_Project\\Logs\\logfile.txt");
	   */
	   
	   PropertyConfigurator.configure("C:\\Selenium\\Workspace\\Auto_Project\\Logs\\log4j.properties");
	   
	 //logs a debug message
		if(log.isDebugEnabled()){
			log.debug("This is debug");
		}
		
		//logs an error message with parameter
		log.error("This is error : " );
		
		//logs an exception thrown from somewhere
		log.error("This is error");
      log.debug("Hello this is a debug message");
      log.info("Hello this is an info message");
   }
}
