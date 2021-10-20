package org.sydnik.createContract.useSQL;

import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.contract.BasicContract;
import org.sydnik.createContract.exception.CantWriteDoc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SQLDataBasicContract implements SaveData {
    private ConnectSQLBase connectSQLBase;
    private DataClient dataClient;

    public SQLDataBasicContract(ConnectSQLBase connectSQLBase, DataClient dataClient) {
        this.connectSQLBase = connectSQLBase;
        this.dataClient = dataClient;
    }

    @Override
    public void save() throws CantWriteDoc {
        if(checkAvailabilityContract()){
            updateData();
        }else {
            saveNewContract();
        }
    }
    public static BasicContract load(String id, ConnectSQLBase connectSQLBase) {
        String[] s = id.split(" ", 2);
        Statement statement = null;
        HashMap<String,String> map = new HashMap<>();
        try {
            statement = connectSQLBase.getStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM basiccontract " +
                    "WHERE numberContract = '" + s[0] +"' " +
                    "AND strangeName = '" + s[1]+"'");
            int column = resultSet.getMetaData().getColumnCount();
            resultSet.next();
            for (int i = 1; i <= column; i++) {
                map.put(resultSet.getMetaData().getColumnName(i),resultSet.getString(i));
            }


        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            return new BasicContract();
        } finally {
            try {
                if(statement!=null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
//                throwables.printStackTrace();
                return new BasicContract();
            }
        }
        return new BasicContract(map);
    }

    private boolean checkAvailabilityContract(){
        Statement statement = null;
        try {
            statement = connectSQLBase.getStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM basiccontract WHERE numberContract = '"+dataClient.getNumberContract()+"' " +
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
    private void saveNewContract() throws CantWriteDoc {
        Statement statement = null;
        try {
            statement = connectSQLBase.getStatement();
            statement.executeUpdate("INSERT " +
                    "basiccontract(numberContract, strangeName, dateCreateContract, timeProduction, " +
                    "allSumInEUR, allSumInBYN, prepaymentOr10PercentSum, payUpTo50PercentSum, " +
                    "payUpTo100PercentSum) " +
                    "VALUES ('"+dataClient.getNumberContract()+"', '"+dataClient.getStrangeName()+
                    "', '"+dataClient.getBasicContract().getDateCreateContract()+
                    "', '"+dataClient.getBasicContract().getTimeProduction()+"', '"+dataClient.getBasicContract().getAllSumInEUR()+
                    "', '"+dataClient.getBasicContract().getAllSumInBYN()+ "', '"+dataClient.getBasicContract().getPrepaymentOr10PercentSum()+
                    "', '"+dataClient.getBasicContract().getPayUpTo50PercentSum()+ "', '"+dataClient.getBasicContract().getPayUpTo100PercentSum()+"')");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new CantWriteDoc("не смог");
        }finally {
            try {
                if(statement!=null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new CantWriteDoc("не смог");
            }
        }
    }
    private void updateData(){
        Statement statement = null;
        System.out.println();
        try {
            statement = connectSQLBase.getStatement();
            statement.executeUpdate("UPDATE basiccontract SET " +
                    "dateCreateContract = '"+dataClient.getBasicContract().getDateCreateContract() + "',"+
                    "timeProduction = '"+dataClient.getBasicContract().getTimeProduction() + "',"+
                    "allSumInEUR = '"+dataClient.getBasicContract().getAllSumInEUR() + "',"+
                    "allSumInBYN = '"+dataClient.getBasicContract().getAllSumInBYN() + "',"+
                    "prepaymentOr10PercentSum = '"+dataClient.getBasicContract().getPrepaymentOr10PercentSum() + "',"+
                    "payUpTo50PercentSum = '"+dataClient.getBasicContract().getPayUpTo50PercentSum() + "',"+
                    "payUpTo100PercentSum = '"+dataClient.getBasicContract().getPayUpTo100PercentSum() + "'"+
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
