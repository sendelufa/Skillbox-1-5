/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletContext;
import sendel.bank.exception.FileIsNotDirectory;

/**
 *
 * @author sendel
 * get fil list
 */
public class FileList {
    HashMap<String, String[]> files = new HashMap<>();
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
        
        for (File file : filesArray){
            String[] values = {String.valueOf(file.length()/(1024)) + "Кб", sdf.format(new Date(file.lastModified()))};
            //String value = 
            //        "<i class=\"fas fa-calendar-day\"></i> " + sdf.format(new Date(file.lastModified())) +
            //        "&nbsp;&nbsp;&nbsp;<i class=\"far fa-file\"></i> " + file.length()/(1024) + "Кб" ;
            files.put(file.getName(), values);
        }
        context.setAttribute("fileList", files);
    }

    public HashMap<String, String[]> getFiles() {
        return files;
    }
    
    
}
