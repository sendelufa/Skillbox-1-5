/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author sendel
 */
public final class RequestTransformer {

    //Output Data
    private final HashMap<String, String[]> requestFields = new HashMap<>();
    private final HashMap<String, FileItem> requestFiles = new HashMap<>();
    //Input Data
    private final HttpServletRequest request;
    //Inner class data
    private String contentType = "";
    private final DiskFileItemFactory factory = new DiskFileItemFactory();
    private final int maxFileSize = 50000 * 1024;
    private final int maxMemSize = 5000 * 1024;

    public RequestTransformer(HttpServletRequest request) {
        this.request = request;
        contentType = getContentType();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File("e:\\temp"));
    }

    //fill HashMaps with text fields and files fron form request
    public void Parse() {
        if (getContentType().contains("multipart/form-data")) {
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(maxFileSize);
            try {
                List requestItems = upload.parseRequest(new ServletRequestContext(request));
                Iterator i = requestItems.iterator();
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        requestFiles.put(fi.getFieldName(), fi);
                    } else {
                        //getString must contains encoding parameter
                        addValueFieldToRequest(fi.getFieldName(), fi.getString("UTF-8"));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }

    private String getContentType() {
        try {
            contentType = request.getContentType();
            if (contentType == null) {
                contentType = "";
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return contentType;
    }
    
    private void addValueFieldToRequest(String fieldName, String fieldValue) {
        ArrayList<String> array = new ArrayList<>();
        //add element to array if more then 1 exist
        if (requestFields.containsKey(fieldName)) {
            Collections.addAll(array, requestFields.get(fieldName));
        }
        array.add(fieldValue);
        String[] values = array.toArray(new String[array.size()]);
        requestFields.put(fieldName, values);
    }

    public HashMap<String, String[]> getRequestFields() {
        return requestFields;
    }

    public HashMap<String, FileItem> getRequestFiles() {
        return requestFiles;
    }

    
}
