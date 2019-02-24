/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank.db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.util.Map;
import sendel.bank.CreditDecision;

/**
 *
 * @author sendel
 */
public class DBHandler {
    private static Connection connection;
    //setting for connect to DB
    private static String dbHost = "localhost";
    private static String dbPort = "3307";
    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "12345";
    //buffer setiings
    private final static int bufferSize = 500_000;
    
    //set DB Connect Settings
 /*   public static void setConnection(String sdbHost, String sdbPort, String sdbName, String sdbUser, String sdbPass){
        dbHost = sdbHost;
        dbPort = sdbPort;
        dbName = sdbName;
        dbUser = sdbUser;
        dbPass = sdbPass;
    }*/
    
    //set new Connection or get exist
    public static Connection getConnection() {
        if (connection == null) {
            try {
                //dbDriver.init();
                //connection = dbDriver.getMyConnection();
                //Connect to MySQL57
                Class.forName("com.mysql.jdbc.Driver");
                connection = (Connection) DriverManager.getConnection(
                        "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass + "&characterEncoding=UTF-8");
                 
                connection.createStatement().execute("TRUNCATE TABLE voter_count");
               /* connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE `learn`.`credit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `json_request` JSON NOT NULL,
  `form_valid` TINYINT NOT NULL DEFAULT 0,
  `give_credit` TINYINT NOT NULL DEFAULT 0,
  `file_original_name` LONGTEXT NULL,
  `file_path` LONGTEXT NULL,
  `file_size_kb` INT NULL,
  `file_date` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
COMMENT = 'M15 for CreditForm';");*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    

    
}
