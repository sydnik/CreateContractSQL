package org.sydnik.createContract.useSQL;


import org.sydnik.createContract.exception.CantWriteDoc;

public interface SaveData {
    public void save() throws CantWriteDoc;
}
