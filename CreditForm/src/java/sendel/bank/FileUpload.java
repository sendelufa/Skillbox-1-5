/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
    private ArrayList<String> uploadedFileNames = new ArrayList<>();
    private String filePath = "";

    public FileUpload(Map<String, FileItem> requestFiles, String path) {
        this.requestFiles.putAll(requestFiles);
        filePath = path;
        saveFileToDisk();
    }

    private void saveFileToDisk() {
        uploadedFileNames.clear();
        for (String inputFormName : requestFiles.keySet()) {
            FileItem fileItem = requestFiles.get(inputFormName);
            if (fileItem.getName().equals("")) break;
            System.out.println(inputFormName + " -> " + fileItem.getName() + " " + fileItem.isInMemory());
            String fileFullPath = filePath + System.currentTimeMillis() + "_" + cleanFileName(fileItem.getName());
            File file = new File(fileFullPath) ;
            try {
                fileItem.write(file);
                uploadedFileNames.add(file.getName());
            } catch (Exception ex) {
                Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String cleanFileName(String fileName){
        return fileName.replaceAll("\\s", "_");
    }

    public ArrayList<String> getUploadedFileNames() {
        return uploadedFileNames;
    }
    
    
    
    //convert filename for save cyrillyc symbols
    public static String getContentDespositionFilename(String fileName) {
        try {
           byte[] fileNameBytes = fileName.getBytes("utf-8");
            String dispositionFileName = "";
            for (byte b : fileNameBytes) {
                dispositionFileName += (char) (b & 0xff);
            }
            return dispositionFileName;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return fileName;
    }
}
