/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import sendel.bank.db.DBHandler;
import sendel.bank.exception.FileIsNotDirectory;

/**
 *
 * @author sendel
 * get fil list
 */
public class FileList {
    private HashMap<String, HashMap<String,String>> files = new HashMap<>();
    File rootDirectory;
    ServletContext context;
    
    public FileList(ServletContext context) throws FileIsNotDirectory{
        this.context = context;
        rootDirectory = new File(this.context.getInitParameter("filePath")); 
        System.out.println("==" + rootDirectory.getAbsolutePath());
        //сheck if path is Directory
        if (!rootDirectory.isDirectory()){
            throw new FileIsNotDirectory();
        }
        readFileList();
        
    }
    
    private void readFileList(){
        File[] filesArray = rootDirectory.listFiles();
        files.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        Connection conn =  DBHandler.getConnection();
        ResultSet rs = null;
        try {
               rs =  conn.createStatement().executeQuery("SELECT * FROM credit;");
                
        
        
        while (rs.next()) {
            String giveCredit = " кредит не одобрен ";
            if (rs.getString("give_credit").equals("1")){
                giveCredit = " кредит одобрен ";
            }
            String id = rs.getString("id");
            String fileDate = rs.getString("file_date");
            String fileSize = rs.getString("file_size_kb");
            String fileName = rs.getString("file_original_name");
            String creditInfo = rs.getString("credit_info");
            if (fileDate.equals("null")){
                fileDate = "";
                fileSize = "";
                fileName = "файл не загружен";
                System.out.println(fileDate);
            }
            else {
                fileDate = sdf.format(new Date(Long.parseLong(fileDate)));
                fileSize = String.valueOf(fileSize) + "Кб";
            }
            HashMap<String, String> info = new HashMap<>();
            info.put("fileDate", fileDate);
            info.put("fileSize", fileSize);
            info.put("giveCredit", giveCredit);
            info.put("creditInfo", creditInfo);
            info.put("fileName", fileName);
            //String[] values = {fileSize, fileDate , giveCredit, creditInfo, fileName};
            files.put(id, info);
        }
        }catch (SQLException ex) {
                Logger.getLogger(CreditDecision.class.getName()).log(Level.SEVERE, null, ex);
            }
        context.setAttribute("fileList", files);
    }

    public HashMap<String, HashMap<String,String>> getFiles() {
        return files;
    }
    
    
}
