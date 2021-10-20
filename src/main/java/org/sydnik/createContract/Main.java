package org.sydnik.createContract;


import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.useSQL.ConnectSQLBase;
import org.sydnik.createContract.useSQL.SQLDataClient;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args)  {
        ConnectSQLBase connectSQLBase = null;
        try {
            connectSQLBase = ConnectSQLBase.getInstance();
            try {
                Model model = new Model(connectSQLBase);
                model.setCurrency();
                MyView view = new MyView();
                MyController controller = new MyController(model,view);
                controller.displayMainPage();

            } catch (Exception e){
                Model model = new Model(connectSQLBase);
                MyView view = new MyView();
                MyController controller = new MyController(model,view);
                controller.displayRateEUR();
            }
        }finally {
            try {
                if(connectSQLBase!=null) {
                    connectSQLBase.closeConnection();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}

