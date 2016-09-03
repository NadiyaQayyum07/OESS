package com.cdk.oes.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by qayyumn on 9/2/2016.
 */
public class CSVUtility {


    public static ArrayList<String> readLine(String filePath) {
        ArrayList<String> linesList = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                linesList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesList;
    }
}