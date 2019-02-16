/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.voteanalyzer;

import java.sql.*;

/**
 *
 * @author noniko
 */
public class DBConnect {
   
    private Connection myConnection;
    
    /** Creates a new instance of MyDBConnection */
    public DBConnect() {

    }

    public void init(){
    
       try{
        
        Class.forName("com.mysql.jdbc.Driver");
        //myConnection=DriverManager.getConnection(
        //        "jdbc:mysql://localhost:3307/learn","root", "12345"
        //        );
        myConnection=DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/learn?user=root&password=12345&characterEncoding=UTF-8");
        }
        catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }
    }
    
    
    public Connection getMyConnection(){
        return myConnection;
    }
    
    
    public void close(ResultSet rs){
        
        if(rs !=null){
            try{
               rs.close();
            }
            catch(Exception e){}
        
        }
    }
    
     public void close(java.sql.Statement stmt){
        
        if(stmt !=null){
            try{
               stmt.close();
            }
            catch(Exception e){}
        
        }
    }
     
  public void destroy(){
  
    if(myConnection !=null){
    
         try{
               myConnection.close();
            }
            catch(Exception e){}
        
        
    }
  }
    
}
