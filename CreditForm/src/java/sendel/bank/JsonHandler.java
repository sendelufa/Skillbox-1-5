/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author sendel
 */
public class JsonHandler {

    static public String getJsonIncludePrefixFromPost(Map<String, String[]> postMap, String prefix) {
        JSONObject rootJson = new JSONObject(); //корневой объект
        JSONObject requestList = new JSONObject(); //ячейки списка
        rootJson.put("postRequest", requestList);

        for (String key : postMap.keySet()) {
            if (key.startsWith(prefix)) {
                for (String value : postMap.get(key)) {
                    requestList.put(key, value);
                }
            }

        }
        return rootJson.toJSONString();
    }

}
