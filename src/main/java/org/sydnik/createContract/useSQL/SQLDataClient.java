package org.sydnik.createContract.useSQL;

import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.CantWriteDoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SQLDataClient implements SaveData {
    DataClient dataClient;
    ConnectSQLBase connectSQLBase;

    public SQLDataClient(DataClient dataClient,ConnectSQLBase connectSQLBase){
        this.dataClient = dataClient;
        this.connectSQLBase = connectSQLBase;
    }
    public void save() throws CantWriteDoc {
        try {
        if(checkAvailabilityClient()){
            updateData();
        }
        else saveNewClient();
        } catch (Exception e) {
            throw new CantWriteDoc("Не смог сохранить данные в файл");
        }
    }
    public static DataClient load(String id,ConnectSQLBase connectSQLBase){
        String[] s = id.split(" ",2);
        Statement statement = null;
        HashMap<String,String> map = new HashMap<>();
        try {
            statement = connectSQLBase.getStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dataclient WHERE numberContract = '"+s[0]+"' " +
                    "AND strangeName = '" + s[1]+"'");
            int columns = resultSet.getMetaData().getColumnCount();
            resultSet.next();
            for (int i = 1; i <= columns; i++) {
                map.put(resultSet.getMetaData().getColumnName(i),resultSet.getString(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(statement!=null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return new DataClient(map);
    }
    private boolean checkAvailabilityClient(){
        Statement statement = null;
        try {
            statement = connectSQLBase.getStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dataclient WHERE numberContract = '"+dataClient.getNumberContract()+"' " +
                    "AND strangeName = '" + dataClient.getStrangeName()+"'");
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(statement!=null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }
    private void saveNewClient(){
        Statement statement = null;
        try {
            statement = connectSQLBase.getStatement();
            statement.executeUpdate("INSERT " +
                    "dataclient(numberContract, strangeName, fullNameClient, miniNameClient, " +
                    "numberPassport, issuedByPassport, whenIssued, identificationNumber, " +
                    "addressRegistration, addressDelivery, numberPhoneClient) " +
                    "VALUES ('"+dataClient.getNumberContract()+"', '"+dataClient.getStrangeName()+"', '"+dataClient.getFullNameClient()+
                    "', '"+dataClient.getMiniNameClient()+"', '"+dataClient.getNumberPassport()+"', '"+dataClient.getIssuedByPassport()+
                    "', '"+dataClient.getWhenIssued()+"', '"+dataClient.getIdentificationNumber()+ "', '"+dataClient.getAddressRegistration()+
                    "', '"+dataClient.getAddressDelivery()+"', '"+dataClient.getNumberPhoneClient()+"')");
        }catch (SQLException throwables) {

            throwables.printStackTrace();
        }finally {
            try {
                if(statement!=null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    private void updateData(){
        Statement statement = null;
        try {
            statement = connectSQLBase.getStatement();
            statement.executeUpdate("UPDATE dataclient SET " +
                            "fullNameClient = '"+dataClient.getFullNameClient() + "',"+
                            "miniNameClient = '"+dataClient.getMiniNameClient() + "',"+
                            "numberPassport = '"+dataClient.getNumberPassport() + "',"+
                            "issuedByPassport = '"+dataClient.getIssuedByPassport() + "',"+
                            "whenIssued = '"+dataClient.getWhenIssued() + "',"+
                            "identificationNumber = '"+dataClient.getIdentificationNumber() + "',"+
                            "addressRegistration = '"+dataClient.getAddressRegistration() + "',"+
                            "addressDelivery = '"+dataClient.getAddressDelivery() + "',"+
                            "numberPhoneClient = '"+dataClient.getNumberPhoneClient() + "'"+
                            "WHERE numberContract = '" + dataClient.getNumberContract() +"' " +
                            "AND strangeName = '"+ dataClient.getStrangeName() + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                if(statement!=null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

