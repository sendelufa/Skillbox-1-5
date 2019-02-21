/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

/**
 *
 * @author sendel
 */
public class CreditField {
    private String name, validateString, errorMsg;
    private int validateType;
    public static final int REGEXP = 1;
    public static final int STRING = 2;
    
    public CreditField(String name, int validateType, String validateString, String errorMsg){
        this.name = name;
        this.validateType = validateType;
        this.validateString = validateString;
        this.errorMsg = errorMsg;
    }

    public String getName() {
        return name;
    }

    public String getValidateString() {
        return validateString;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getValidateType() {
        return validateType;
    }
    
    
}
