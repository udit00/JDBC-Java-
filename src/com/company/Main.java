package com.company;

import com.mysql.cj.jdbc.DatabaseMetaData;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    // This function prints the data (any number of rows), it returns nothing and requires a resultSet to print the data
    private static void printDB(ResultSet resultSet) throws Exception{
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();   // to get the info about the resultSet
        short columnCount=(short)resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            for (short i = 1; i <= columnCount; i++)                   // for every row, iterating through all the columns
            {
                String currentColumnType = resultSetMetaData.getColumnTypeName(i);
                StringBuilder currentColumnName = new StringBuilder(resultSetMetaData.getColumnName(i));
                if (currentColumnType == "INT")
                {
                    int currentColumnData = resultSet.getInt(i);
                    System.out.println(currentColumnName + " : " + currentColumnData);
                } else if (currentColumnType == "VARCHAR")
                {
                    StringBuilder currentColumnData = new StringBuilder(resultSet.getString(i));
                    System.out.println(currentColumnName + " : " + currentColumnData);
                } else if (currentColumnType == "BOOLEAN")
                {
                    Boolean currentColumnData = resultSet.getBoolean(i);
                    System.out.println(currentColumnName + " : " + currentColumnData);
                }
            }
        }
    }


    private static void menu(Connection connection) throws Exception {
        short choice = 0;
        String tableName="gym";
        System.out.println("-----------------------------------------");
        System.out.println("Welcome to Udit's Database");
        System.out.println("1. Insert Data ['"+tableName+"'] // ['table name']");
        System.out.println("2. Print Data ['"+tableName+"'] // ['table name']");
        System.out.println("3. Close connection.");
        Scanner sc=new Scanner(System.in);
        choice=(short)sc.nextInt();
        switch (choice) {
            // inserting Data
            case 1:
                if(!connection.isClosed()) {
                    System.out.println("has");
                    String insertQuery = "INSERT INTO gym(firstName, lastName, state) VALUES(?, ?, ?);";
                    PreparedStatement prepareStatement = connection.prepareStatement(insertQuery);
                    prepareStatement.setString(1, "Amit");
                    prepareStatement.setString(2, "Kumar");
                    prepareStatement.setString(3, "Haryana");
                    prepareStatement.executeUpdate();
                    System.out.println("Entered Data in mySql");
                }
                break;
            case 2:
                String printQuery = "SELECT * FROM " + tableName;
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(printQuery);
                printDB(resultSet);
                break;
            case 3:
                connection.close();
                break;
            default:
                System.out.println("---- Wrong Input ----");
        }

        }


    public static void main(String[] args) throws Exception{
        String databaseName="uditlearningmysql";
        DB newDb=new DB("jdbc:mysql://localhost:3306/"+databaseName,
                "root","Foodpanda@123*");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection(newDb.getURL(), newDb.getUserName(), newDb.getPassword());
        /*String query="INSERT INTO gym VALUES(1,\"udit\",\"nair\",\"Delhi\");";
        Statement st=connection.createStatement();
        int result=st.executeUpdate(query);*/

        menu(connection);
    }
}
