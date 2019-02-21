package sendel.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sendel
 */
public class FormCreditHandler {

    protected Map<String, String[]> fieldsData = new HashMap<>();
    private StringBuilder errorMsg = new StringBuilder();
    private ArrayList<CreditField> fields = new ArrayList<>();
    protected Boolean isFormFieldsValid = false;

    public FormCreditHandler(Map<String, String[]> postMap) {
        this.fieldsData.clear();
        this.fieldsData.putAll(postMap);
        fillСheckFiledsData();
        CheckValidDataFields();
        writeStatusValidForm();
        
        for (String key : this.fieldsData.keySet()){
            System.out.println("FormCreditHandler ->" + key + "=" + this.fieldsData.get(key)[0]);
        }

    }
    
    private void writeStatusValidForm(){
        fieldsData.put("valid_form_status", new String[]{isFormFieldsValid.toString()});
    }

    public boolean isActionCalculate() {
        if (fieldsData.containsKey("action")) {
            if (fieldsData.get("action")[0].equals("calculate")) {
                return true;
            }
        }
        return false;
    }

    //check all selected fields in ArrayList fields, change isFormFieldsValid to true if all valid
    // NOT REALISED CHECKBOX ARRAY, only first element
    public void CheckValidDataFields() {
        errorMsg = new StringBuilder();
        for (CreditField fieldCheck : fields) {
            boolean isValid = false;
            if (fieldCheck.getValidateType() == CreditField.REGEXP) {
                isValid = isPostContainsValideRegexp(fieldCheck.getName(), fieldCheck.getValidateString());
            } else if (fieldCheck.getValidateType() == CreditField.STRING) {
                isValid = isPostContainsValideString(fieldCheck.getName(), fieldCheck.getValidateString());
            }
            if (!isValid) {
                errorMsg.append(fieldCheck.getErrorMsg()).append("<br>");
            }
        }
        isFormFieldsValid = (errorMsg.length() == 0);
    }

    private boolean isPostContainsValideString(String key, String value) {
        if (fieldsData.containsKey(key)) {
            if (fieldsData.get(key)[0].equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPostContainsValideRegexp(String key, String regexp) {
        if (fieldsData.containsKey(key)) {
            if (fieldsData.get(key)[0].matches(regexp)) {
                return true;
            }
        }
        return false;
    }

    private void fillСheckFiledsData() {
        fields.add(new CreditField("credit_summ", CreditField.REGEXP, "^[\\d]{5,7}$", "Неверная сумма кредита"));
        fields.add(new CreditField("credit_agree_gpdr", CreditField.STRING, "1", "Не подтверждена обработка персональных данных"));
        fields.add(new CreditField("credit_fullName", CreditField.REGEXP, "^[а-яёА-ЯЁ\\s\\-]+$", "Некорректно введены ФИО"));
        fields.add(new CreditField("credit_sex", CreditField.REGEXP, "[MF]{1}", "Не выбран пол"));
        fields.add(new CreditField("credit_adress", CreditField.REGEXP, ".+", "Не введен адрес регистрации"));
        fields.add(new CreditField("credit_passport_issued_by", CreditField.REGEXP, ".+", "Не введено кем выдан паспорт"));
        fields.add(new CreditField("credit_month_income", CreditField.REGEXP, "[\\d]{4,7}", "Неверно введено сумма дохода в месяц"));
        fields.add(new CreditField("credit_birthday", CreditField.REGEXP, "^[\\d\\.\\-\\s]{6,10}", "Неверно введена дата рождения"));
        fields.add(new CreditField("credit_passport_series", CreditField.REGEXP, "^[\\d]{4}$", "Неверно введена серия паспорта"));
        fields.add(new CreditField("credit_passport_date", CreditField.REGEXP, "[\\d\\.\\-\\s]{6,10}", "Неверная дата выдачи паспорта"));
        fields.add(new CreditField("credit_passport_division", CreditField.REGEXP, ".+", "Неверное подразделение выдачи паспорта"));
        fields.add(new CreditField("credit_employer", CreditField.REGEXP, ".+", "Не введен работодатель"));
        fields.add(new CreditField("credit_education", CreditField.REGEXP, "^\\d{1,2}$", "Не введено образование"));
        fields.add(new CreditField("credit_employer_industry", CreditField.REGEXP, ".+", "Не введено отрасль работы работодателя"));
        fields.add(new CreditField("credit_tel", CreditField.REGEXP, "^[\\+]?[\\d\\s\\-\\(\\)]{11,}$", "неверный формат номера телефона"));
        fields.add(new CreditField("credit_passport_number", CreditField.REGEXP, "^[\\d]{6}$", "Неверный номер паспорта"));
        fields.add(new CreditField("credit_family_status", CreditField.REGEXP, "^[12]{1}$", "Не введено семейное положение"));
        fields.add(new CreditField("credit_term", CreditField.REGEXP, "^[\\d]{1,2}$", "Неверный срок кредита"));

    }

    public String getErrorMsg() {
        return errorMsg.toString();
    }

    public boolean isFormFieldsValid() {
        return isFormFieldsValid;
    }

    public Map<String, String[]> getPostMap() {
        return fieldsData;
    }
    
    
    
    
    
    

}
