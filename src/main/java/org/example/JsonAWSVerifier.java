package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.regex.Pattern;

public class JsonAWSVerifier {

    public static boolean verifyAsterisk(String jsonFilePath) {
        JSONObject object = JsonReader.readFromFilePath(jsonFilePath);
        if (checkAWSSyntax(object)) {
            try {
                JSONArray statements = object.getJSONObject("PolicyDocument").getJSONArray("Statement");
                for (int i = 0; i < statements.length(); i++) {
                    if (statements.getJSONObject(i).has("Resource")) {
                        Object resourceValue = statements.getJSONObject(i).get("Resource");
                        if (resourceValue instanceof String) {
                            String value = (String) resourceValue;
                            if (value.equals("*"))
                                return false;
                        } else if (resourceValue instanceof JSONArray values) {
                            for (int j = 0; j < values.length(); j++) {
                                String value = values.getString(j);
                                if (value.equals("*"))
                                    return false;
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    public static boolean checkAWSSyntax(JSONObject jsonObject) {
        if (jsonObject != null && jsonObject.length() == 2) {
            String policyNameValue = jsonObject.optString("PolicyName");
            JSONObject policyDocumentValue = jsonObject.optJSONObject("PolicyDocument");
            return checkPolicyNameValueSyntax(policyNameValue) && checkPolicyDocumentValueSyntax(policyDocumentValue);
        }
        return false;
    }

    public static boolean checkPolicyNameValueSyntax(String policyNameValue) {
        String pattern = "[\\w+=,.@-]+";
        int minLength = 1;
        int maxLength = 128;
        return Pattern.matches(pattern, policyNameValue) && policyNameValue.length() >= minLength && policyNameValue.length() <= maxLength;
    }

    public static boolean checkPolicyDocumentValueSyntax(JSONObject policyDocumentObject) {
        if (policyDocumentObject.length() == 2) {
            String policyDocumentVersionValue = policyDocumentObject.optString("Version");
            JSONArray statements = policyDocumentObject.optJSONArray("Statement");
            if (!checkPolicyDocumentVersionValue(policyDocumentVersionValue) || statements.length() == 0)
                return false;
            for (int i = 0; i < statements.length(); i++) {
                try {
                    if (!checkPolicyDocumentStatementValue(statements.getJSONObject(i)))
                        return false;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        }
        return false;
    }

    public static boolean checkPolicyDocumentVersionValue(String version) {
        return version.equals("2012-10-17") || version.equals("2008-10-17");
    }

    public static boolean checkPolicyDocumentStatementValue(JSONObject statement) {
        boolean usedEffect = false;
        boolean usedActions = false;
        Iterator<String> keys = statement.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = statement.opt(key);
            if (key.equals("Sid")) {
                if (!(value instanceof String))
                    return false;
            } else if (key.equals(("Effect"))) {
                if (!(value instanceof String && (value.equals("Allow") || value.equals("Deny"))))
                    return false;
                usedEffect = true;
            } else if (key.equals("Principal")) {
                if (!(value instanceof JSONObject))
                    return false;
            } else if (key.equals("Action")) {
                if (!(value instanceof JSONArray))
                    return false;
                else if (((JSONArray) value).length() == 0)
                    return false;
                usedActions = true;
            } else if (key.equals("Resource")) {
                if (!(value instanceof JSONArray || value instanceof String))
                    return false;
            } else if (key.equals("Condition")) {
                if (!(value instanceof JSONObject))
                    return false;
            } else
                return false;

        }
        return usedEffect && usedActions;
    }

}
