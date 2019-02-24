package sendel.bank;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.apache.tomcat.util.http.fileupload.FileItem;
import sendel.bank.db.DBHandler;

/**
 *
 * @author sendel
 */
public class CreditDecision extends FormCreditHandler {
    private String decision = "";
    private boolean isCreditAllow = false;

    public CreditDecision(Map<String, String[]> fieldsData, Map<String, FileItem> files) {
        super(fieldsData);
        checkCreditAllow();
        //set decision about credit
        super.fieldsData.put("credit_allow", new String[]{String.valueOf(isCreditAllow)});

    }

    private void checkCreditAllow() {
        if (isFormFieldsValid) {
            long credit_summ = Long.parseLong(fieldsData.get("credit_summ")[0]);
            long credit_month_income = Long.parseLong(fieldsData.get("credit_month_income")[0]);
            long credit_term = Long.parseLong(fieldsData.get("credit_term")[0]);
            long minimum_income = (credit_summ / credit_term) * 2;
            if (credit_month_income >= minimum_income) {
                decision = "Вам одобрен кредит на сумму " + credit_summ + "руб. на срок " + credit_term + " месяцев";
                isCreditAllow = true;
                return;
            }
        }
        decision = "Вам не одобрили кредит";
        isCreditAllow = false;
        
    }
    
    public void saveFormResultToDB(ArrayList<String> files, ServletContext context) {
        //insert iinto db CreditDecision
                //insert to DB only if form is valid
        if (isFormFieldsValid()) {
            
           Connection conn =  DBHandler.getConnection();
           String cell_json = JsonHandler.getJsonIncludePrefixFromPost(getPostMap(), "");
              try {
                PreparedStatement stmt = null;
                //create prepareStatement for avoid SQL Injections and escape symbols
                stmt = conn.prepareStatement("INSERT INTO credit "
                        + "(json_request, form_valid, give_credit, file_original_name, file_path, file_size_kb, file_date, credit_info)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, cell_json);
                stmt.setString(2, String.valueOf(isFormFieldsValid()? 1 : 0));
                stmt.setString(3, String.valueOf(isIsCreditAllow()? 1 : 0));
                stmt.setString(8, fieldsData.get("credit_fullName")[0] + " сумма кредита:" + fieldsData.get("credit_summ")[0]);
                //check upload files
                if (files.isEmpty() || files == null){
                stmt.setString(4, "null");
                stmt.setString(5, "null");
                stmt.setString(6, "0");
                stmt.setString(7, "null");  
                }
                else {
                    for (String filename : files){
                        File file = new File(context.getInitParameter("filePath") + filename);
                stmt.setString(4, filename);
                stmt.setString(5, file.getAbsolutePath());
                stmt.setString(6, String.valueOf(file.length()/1024));
                stmt.setString(7, String.valueOf(file.lastModified()));
                    }
                }

                stmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(CreditDecision.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public String getDecision(){
        return decision;
    }

    public boolean isIsCreditAllow() {
        return isCreditAllow;
    }
    
    
}
