package com.sl.student.secondary.dao.util;

import com.sl.student.primary.config.DatasourceConfig;

public class CheckDb {


    public static void main(String[] args) {
        String s1 = "jdbc:oracle://localhost:3306/storedproducer";

        if (s1.toLowerCase().contains("mysql")){
            System.out.println("My-Sql");
        }
        else if (s1.toLowerCase().contains("sqlserver")){
            System.out.println("Sql-Server");
        }
        else if (s1.toLowerCase().contains("oracle")){
            System.out.println("Oracle");
        }
        else {
            System.out.println("default Postgresql");
        }


        //DatasourceConfig dbConfig = new DatasourceConfig();
        //dbConfig.connect("jdbc:mysql://localhost:3306/storedproducer","root","root");
    }

}
