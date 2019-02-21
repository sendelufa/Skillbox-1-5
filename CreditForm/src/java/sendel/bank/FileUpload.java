/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;

/**
 *
 * @author sendel
 * Save Files to disk from Form POST Request multipart/form-data
 */
public class FileUpload {

    private final HashMap<String, FileItem> requestFiles = new HashMap<>();
    String filePath = "";

    public FileUpload(Map<String, FileItem> requestFiles, String path) {
        this.requestFiles.putAll(requestFiles);
        filePath = path;
    }

    public void saveFileToDisk() {
        for (String fileName : requestFiles.keySet()) {
            FileItem fileItem = requestFiles.get(fileName);
            System.out.println(fileName + " -> " + fileItem.getName() + " " + fileItem.isInMemory());
            String fileFullPath = filePath + System.currentTimeMillis() + "_" + cleanFileName(fileItem.getName());
            File file = new File(fileFullPath) ;
            try {
                fileItem.write(file);
            } catch (Exception ex) {
                Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String cleanFileName(String fileName){
        return fileName.replaceAll("\\s", "_");
    }
}
