package com.demo.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class ReadCSVFile {
    public static void main(String[] args) throws IOException, CsvException {

//        File csvFile = new File("src/main/resources/testData/LoginCreds.csv");
//        FileReader fr = new FileReader(csvFile);
//        CSVReader csvReader = new CSVReader(fr);

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
        InputStreamReader isr = new InputStreamReader(is);
        CSVReader csvReader = new CSVReader(isr);

        List<String[]> dataList =  csvReader.readAll();
        for(String[] dataArray:dataList){
            for(String data : dataArray){
                System.out.print(data);
                System.out.print("\t");
            }
            System.out.println();
        }

        for(String[] dataList1: dataList){
            System.out.println(dataList1[0]);   /// 1st column data
            System.out.println(dataList1[1]);   /// 2nd column data
        }

    }
}
