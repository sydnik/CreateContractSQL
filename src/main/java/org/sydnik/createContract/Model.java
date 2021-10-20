package org.sydnik.createContract;

import org.sydnik.createContract.data.Currency;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.exception.DontHaveFilePattern;
import org.sydnik.createContract.useSQL.ConnectSQLBase;
import org.sydnik.createContract.useSQL.MainSQLRequest;
import org.sydnik.createContract.view.Display;
import org.sydnik.createContract.createFileDocument.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private SalesManager salesManager;
    private DataClient dataClient;
    private Currency currency;
    private ConnectSQLBase connectSQLBase;

    public Model(ConnectSQLBase connectSQLBase) {
        this.connectSQLBase = connectSQLBase;
        this.salesManager = SalesManager.load();
        if(salesManager==null) {
            System.out.println("i tutu");
            salesManager = new SalesManager("Нужно имя тебе", "99",
                    "99.99.9999","+375(00) 000 0000","");
        }
    }
    public void setCurrency(){
        currency = Currency.createCurrency();
    }
    public void setCurrency(Component[] components){
        double value = 0;
        for(Component component : components){
            try {
                if(component.getName().equals("valueCurrency")){
                    value=Double.parseDouble(((JTextField)component).getText());
                }
            }catch (Exception e){}

        }
        currency = new Currency(value);
    }
    public void setSalesManager(Component[] listComponent) throws CantWriteDoc {
        String fullName = null;
        String numberPhoneManager = null;
        String numberPowerOfAttorney = null;
        String datePowerOfAttorney = null;
        String pathForSaveContact = null;
        for (Component component:listComponent) {
            try {
                switch ((String) component.getName()) {
                    case "fullName": {
                        fullName = ((JTextField) component).getText();
                        break;
                    }
                    case "numberPowerOfAttorney": {
                        numberPowerOfAttorney = ((JTextField) component).getText();
                        break;
                    }
                    case "datePowerOfAttorney": {
                        datePowerOfAttorney = ((JTextField) component).getText();
                        break;
                    }
                    case "numberPhoneManager": {
                        numberPhoneManager = ((JTextField) component).getText();
                        break;
                    }
                    case "pathForSaveContact": {
                        pathForSaveContact = ((JTextField) component).getText();
                        break;
                    }
                }
            } catch (Exception e) {
            }
        }
        salesManager = new SalesManager(fullName,numberPowerOfAttorney,datePowerOfAttorney,numberPhoneManager,pathForSaveContact);
        salesManager.save();

    }
    public void createNewClient(Component[] listComponent) throws CantWriteDoc {

        Map<String,String> mapDataClient = new HashMap<>();
        for(Component component:listComponent){
            try {
            switch ( component.getName()) {
                case "numberContract": {
                    mapDataClient.put("numberContract",((JTextField) component).getText());
                    break;
                }
                case "strangeName" : {
                    mapDataClient.put("strangeName", ((JTextField) component).getText());
                    break;
                }
                case "fullNameClient": {
                    mapDataClient.put("fullNameClient",((JTextField) component).getText());
                    break;
                }
                case "numberPassport": {
                    mapDataClient.put("numberPassport", ((JTextField) component).getText());
                    break;
                }
                case "issuedByPassport": {
                    mapDataClient.put("issuedByPassport", ((JTextField) component).getText());
                    break;
                }
                case "whenIssued": {
                    mapDataClient.put("whenIssued", ((JTextField) component).getText());
                    break;
                }
                case "identificationNumber": {
                    mapDataClient.put("identificationNumber", ((JTextField) component).getText());
                    break;
                }
                case "addressRegistration": {
                    mapDataClient.put("addressRegistration", ((JTextField) component).getText());
                    break;
                }
                case "addressDelivery": {
                    mapDataClient.put("addressDelivery", ((JTextField) component).getText());
                    break;
                }
                case "numberPhoneClient": {
                    mapDataClient.put("numberPhoneClient", ((JTextField) component).getText());
                    break;
                }
            }
            }catch (Exception e){}
        }

        dataClient = new DataClient(mapDataClient);
        dataClient.save(connectSQLBase);
    }
    public void saveDataAboutClient(Component[] listComponent) throws CantWriteDoc {
        Map<String,String> mapDataClient = new HashMap<>();
        for(Component component:listComponent){
            try {
                switch ( component.getName()) {

                    case "numberContract": {
                        mapDataClient.put("numberContract",((JTextField) component).getText());
                        break;
                    }
                    case "strangeName" : {
                        mapDataClient.put("strangeName", ((JTextField) component).getText());
                        break;
                    }
                    case "fullNameClient": {
                        mapDataClient.put("fullNameClient",((JTextField) component).getText());
                        break;
                    }
                    case "numberPassport": {
                        mapDataClient.put("numberPassport", ((JTextField) component).getText());
                        break;
                    }
                    case "issuedByPassport": {
                        mapDataClient.put("issuedByPassport", ((JTextField) component).getText());
                        break;
                    }
                    case "whenIssued": {
                        mapDataClient.put("whenIssued", ((JTextField) component).getText());
                        break;
                    }
                    case "identificationNumber": {
                        mapDataClient.put("identificationNumber", ((JTextField) component).getText());
                        break;
                    }
                    case "addressRegistration": {
                        mapDataClient.put("addressRegistration", ((JTextField) component).getText());
                        break;
                    }
                    case "addressDelivery": {
                        mapDataClient.put("addressDelivery", ((JTextField) component).getText());
                        break;
                    }
                    case "numberPhoneClient": {
                        mapDataClient.put("numberPhoneClient", ((JTextField) component).getText());
                        break;
                    }

                }
            }catch (Exception e){}
        }

        dataClient.setDateAboutClient(mapDataClient);
        dataClient.save(connectSQLBase);
    }
    public void saveDataBaseContractClient (Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setBaseContract(display.getDataForSave());
        dataClient.save(connectSQLBase);
        CreateDocumentsAndPrint.createBasicContract(dataClient,salesManager);
    }
    public void saveDataUpSale (Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setUpSaleContract(display.getDataForSave());
        dataClient.save(connectSQLBase);
        CreateDocumentsAndPrint.createUpSaleContract(dataClient,salesManager);
    }
    public void saveDataSupplementaryAgreementBasicContract(Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setDateSupplementaryAgreementBasicContract(display.getDataForSave());
        dataClient.save(connectSQLBase);
        CreateDocumentsAndPrint.createSupplementaryAgreementBasicContract(dataClient, salesManager);
    }
    public void saveDataSupplementaryAgreementUpSaleContract(Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setDateSupplementaryAgreementUpSaleContract(display.getDataForSave());
        dataClient.save(connectSQLBase);
        CreateDocumentsAndPrint.createSupplementaryAgreementUpSaleContract(dataClient, salesManager);
    }
    public void saveDataInvoiceDocument(Display display) throws CantWriteDoc, DontHaveData, DontHaveFilePattern {
        dataClient.setDataInvoiceDocument(display.getDataForSave());
        dataClient.save(connectSQLBase);
        CreateXlsXFile.createInvoiceDocument(dataClient,salesManager);
    }

    //Делаю через SQL
    public String[] listSelectClient(){
        return new MainSQLRequest().selectClient(connectSQLBase);
    }
    public String[] listSelectClientSearch(Component[] components){
        String filter = "";
        for(Component component:components) {
            try {
                switch (component.getName()) {
                    case "searchClient": {
                        filter = ((JTextField) component).getText().toLowerCase();
                        break;
                    }
                }
            }catch (Exception e){}
        }
        String[] list = new MainSQLRequest().selectClient(connectSQLBase);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(list));
        for (int i = arrayList.size()-1; i >= 0; i--) {
            if(!arrayList.get(i).toLowerCase().contains(filter)){
                arrayList.remove(i);
            }

        }
        list = arrayList.toArray(new String[arrayList.size()]);
        return list;
    }
    public void createBaseContract() throws CantWriteDoc, DontHaveFilePattern {
        CreateDocumentsAndPrint.createBasicContract(dataClient,salesManager);
    }
    public void printDoc(String nameDoc){
        // 150 мс недает встретится двум потокам
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CreateDocumentsAndPrint.printDoc(dataClient,nameDoc,salesManager);
    }
    public void openDoc(String s){
        String path =salesManager.getPathForSaveContract();
        if(path.equals("")){
            path = "saveContract\\";
        }
        switch (s){
            case "openFileBasicContract" : {
                try {
                    Desktop.getDesktop().open(new File(path+ "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Договор"+dataClient.getNumberContract() + ".docx"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "openFileUpSaleContract": {
                try {
                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/ДоговорUpSale"+dataClient.getNumberContract() + ".docx"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
            case "openFileSupplementaryAgreementBasicContract" : {
                try {

                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() +
                            "/Дополнительное соглашение №"+ dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()+" " +dataClient.getNumberContract() + ".docx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "openFileSupplementaryAgreementUpSaleContract" : {
                try {

                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Дополнительное соглашение UpSale"+
                            dataClient.getSupplementaryAgreementUpSaleContract().getNumberSupplementaryAgreementUpSale()+" "+dataClient.getNumberContract() + ".docx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "openFileInvoiceDocument" : {
                try {

                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() +
                            "/Счет-фактура " + dataClient.getNumberContract()+ ".xlsx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }
    public void openDirectory (){
        String path = salesManager.getPathForSaveContract();
        if(path.equals("")){
            path = "saveContract\\";
        }
        try {
            Desktop.getDesktop().open(new File(path + "\\"+dataClient.getNumberContract() + " " + dataClient.getStrangeName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeDataClient(Component[] listComponent) {
        for(Component component:listComponent){
            try {
                if (component.getName().equals("ListClients")) {
                    dataClient = DataClient.load(String.valueOf((((JList)((JViewport)((JScrollPane)component).getComponent(0)).getComponent(0)).getSelectedValue()))
                            ,connectSQLBase);
                }
            }catch (Exception e){

            }
        }
    }
    public void clearDataClient(){
        dataClient = null;
    }

    public SalesManager getSalesManager() {
        return salesManager;
    }
    public DataClient getDataClient(){
        return dataClient;
    }
    public double getCurrencyValue(){
        return currency.getValue();
    }
    public boolean isDateCurrency(String date){
        if(currency.getDate().equals(date)){
            return true;
        }
        return false;
    }
}
