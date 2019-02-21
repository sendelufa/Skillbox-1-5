package sendel.bank;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sendel
 */
public class CreditDecision extends FormCreditHandler {
    private String decision = "";
    private boolean isCreditAllow = false;

    public CreditDecision(Map<String, String[]> fieldsData) {
        super(fieldsData);
        checkCreditAllow();
    }

    private void checkCreditAllow() {
        if (isFormFieldsValid) {
            long credit_summ = Long.parseLong(fieldsData.get("credit_summ")[0]);
            long credit_month_income = Long.parseLong(fieldsData.get("credit_month_income")[0]);
            long credit_term = Long.parseLong(fieldsData.get("credit_term")[0]);
            long minimum_income = (credit_summ / credit_term) * 2;
            System.out.println(minimum_income + " _ " + credit_month_income);
            if (credit_month_income >= minimum_income) {
                decision = "Вам одобрен кредит на сумму " + credit_summ + "руб. на срок " + credit_term + " месяцев";
                isCreditAllow = true;
                return;
            }
        }
        decision = "Вам не одобрили кредит";
        isCreditAllow = false;
    }
    
    public String getDecision(){
        return decision;
    }
}
