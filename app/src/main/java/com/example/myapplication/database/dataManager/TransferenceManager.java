package com.example.myapplication.database.dataManager;

import java.util.Date;

public class TransferenceManager {

    private static TransferenceManager transferenceManager = null;

    private Long id;
    private Date date;
    private AccountManager accountOrigin;
    private AccountManager accountDestiny;
    private String description;


    private TransferenceManager() {}

    public static TransferenceManager getInstance() {
        if (transferenceManager == null) {
            transferenceManager = new TransferenceManager();
        }
        return transferenceManager;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public AccountManager getAccountOrigin() {
        return accountOrigin;
    }
    public void setAccountOrigin(AccountManager accountOrigin) {
        this.accountOrigin = accountOrigin;
    }
    public AccountManager getAccountDestiny() {
        return accountDestiny;
    }
    public void setAccountDestiny(AccountManager accountDestiny) {
        this.accountDestiny = accountDestiny;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
