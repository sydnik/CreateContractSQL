package org.sydnik.createContract.useSQL;

import org.sydnik.createContract.data.DataClient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainSQLRequest {
    ConnectSQLBase connectSQLBase;
    DataClient dataClient;
    public String[] selectClient(ConnectSQLBase connectSQLBase){
        ArrayList<String> list = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connectSQLBase.getStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT numberContract, strangeName FROM dataclient;");
            while (resultSet.next()) {
                list.add(resultSet.getString("numberContract") + " " + resultSet.getString("strangeName"));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(statement!=null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


        return  list.toArray(new String[list.size()]);
    }
}
