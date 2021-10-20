package org.sydnik.createContract.useSQL;

import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.contract.UpSaleContract;
import org.sydnik.createContract.exception.CantWriteDoc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SQLDataUpSale implements SaveData {
    private ConnectSQLBase connectSQLBase;
    private DataClient dataClient;

    public SQLDataUpSale(ConnectSQLBase connectSQLBase, DataClient dataClient) {
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
    public static UpSaleContract load(String id,ConnectSQLBase connectSQLBase){
        String[] s = id.split(" ",2);
        Statement statement = null;
        HashMap<String,String> map = new HashMap<>();
        try {
            statement = connectSQLBase.getStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM upsalecontract WHERE numberContract = '"+s[0]+"' " +
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
        return new UpSaleContract(map);

    }

    private boolean checkAvailabilityContract(){
        Statement statement = null;
        try {
            statement = connectSQLBase.getStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM upsalecontract WHERE numberContract = '"+dataClient.getNumberContract()+"' " +
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
                    "upsalecontract(numberContract, strangeName, dateCreateUpSaleContract, allSumUpSaleInBYN, " +
                    "prepaymentUpSale, payUpTo100percentUpSale) " +
                    "VALUES ('"+dataClient.getNumberContract()+"', '"+dataClient.getStrangeName()+
                    "', '"+dataClient.getUpSaleContract().getDateCreateUpSaleContract()+
                    "', '"+dataClient.getUpSaleContract().getAllSumUpSaleInBYN()+
                    "', '"+dataClient.getUpSaleContract().getPrepaymentUpSale()+
                    "', '"+dataClient.getUpSaleContract().getPayUpTo100percentUpSale()+"')");
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
        try {
            statement = connectSQLBase.getStatement();
            statement.executeUpdate("UPDATE upsalecontract SET " +
                    "dateCreateUpSaleContract = '"+dataClient.getUpSaleContract().getDateCreateUpSaleContract() + "',"+
                    "allSumUpSaleInBYN = '"+dataClient.getUpSaleContract().getAllSumUpSaleInBYN() + "',"+
                    "prepaymentUpSale = '"+dataClient.getUpSaleContract().getPrepaymentUpSale() + "',"+
                    "payUpTo100percentUpSale = '"+dataClient.getUpSaleContract().getPayUpTo100percentUpSale() + "',"+
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
    private void saveAdditionalProduct() throws CantWriteDoc {
        Statement statement = null;
        String request = "";
        request = request + "INSERT " +
                "upsalecontract(numberContract, strangeName, dateCreateUpSaleContract, allSumUpSaleInBYN, " +
                "prepaymentUpSale, payUpTo100percentUpSale) ";
        for ()
            statement.addBatch();
        try {
            statement = connectSQLBase.getStatement();
            statement.executeUpdate("INSERT " +
                    "upsalecontract(numberContract, strangeName, dateCreateUpSaleContract, allSumUpSaleInBYN, " +
                    "prepaymentUpSale, payUpTo100percentUpSale) " +
                    "VALUES ('"+dataClient.getNumberContract()+"', '"+dataClient.getStrangeName()+
                    "', '"+dataClient.getUpSaleContract().getDateCreateUpSaleContract()+
                    "', '"+dataClient.getUpSaleContract().getAllSumUpSaleInBYN()+
                    "', '"+dataClient.getUpSaleContract().getPrepaymentUpSale()+
                    "', '"+dataClient.getUpSaleContract().getPayUpTo100percentUpSale()+"')");
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
    private void updateAdditionalProduct(){

    }
}
