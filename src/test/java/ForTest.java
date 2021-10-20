import org.sydnik.createContract.data.AdditionalProduct;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.useSQL.ConnectSQLBase;
import org.sydnik.createContract.useSQL.SQLDataClient;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class ForTest  {

    public ForTest() {

    }
    public static void main(String[] args) {
        ConnectSQLBase connectSQLBase = ConnectSQLBase.getInstance();
        Statement statement = null;
        try {
            statement = connectSQLBase.getStatement();
            statement.executeUpdate("INSERT " +
                    "dataclient(numberContract, strangeName, fullNameClient, miniNameClient, " +
                    "numberPassport, issuedByPassport, whenIssued, identificationNumber, " +
                    "addressRegistration, addressDelivery, numberPhoneClient) " +
                    "VALUES ('"+"MH5-213322-132"+"', '"+"qweqwe"+"', '"+"уцуцу цуцу цууц', " +
                    "'"+"МР1235312"+"', '"+"МР1235312"+"', '"+"Октябрьское РУВД г.Минск', "+
                    "'"+"12.02.2014"+"', '"+"2132132В133РВ2"+ "', '"+"г.Минск ул Корженевского 21-230',"+
                    "'"+"г.Минск ул Корженевского 21-230"+"', '"+"+375(29) 257 4565"+"')");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
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
