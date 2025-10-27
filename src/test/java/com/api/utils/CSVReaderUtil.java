package com.api.utils;

import com.api.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CSVReaderUtil {
 /// ----- Singleton ----
    private CSVReaderUtil() {

    }

    public static void loadCSV(String pathOfCSVFile) {

////        File csvFile = new File("src/main/resources/testData/LoginCreds.csv");
////        FileReader fr = new FileReader(csvFile);
////        CSVReader csvReader = new CSVReader(fr);
//
//        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
//        InputStreamReader isr = new InputStreamReader(is);
//        CSVReader csvReader = new CSVReader(isr);
//
//        List<String[]> dataList =  csvReader.readAll();
//        for(String[] dataArray:dataList){
//            for(String data : dataArray){
//                System.out.print(data);
//                System.out.print("\t");
//            }
//            System.out.println();
//        }
//
//        for(String[] dataList1: dataList){
//            System.out.println(dataList1[0]);   /// 1st column data
//            System.out.println(dataList1[1]);   /// 2nd column data
//        }


        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
        InputStreamReader isr = new InputStreamReader(is);
        CSVReader csvReader = new CSVReader(isr);

        CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
                .withType(UserBean.class)
                .withIgnoreEmptyLine(true)
                .build();


        List<UserBean> userList = csvToBean.parse();
        System.out.println(userList);
        System.out.println(userList.get(1).getUsername());
    }
}
