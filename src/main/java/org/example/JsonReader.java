package org.example;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonReader {
    public static JSONObject readFromFilePath(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(new FileReader(filePath)).toString();
            return new JSONObject(obj);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (Exception e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
        return null;
    }
}
