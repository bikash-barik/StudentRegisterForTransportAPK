package com.example.androidproject;

public class UserPaymentHelper {

    String payer_email,payer_phone;

    public UserPaymentHelper (){

    }


    public UserPaymentHelper(String payer_email, String payer_phone) {
        this.payer_email = payer_email;
        this.payer_phone = payer_phone;
    }

    public String getPayer_email() {
        return payer_email;
    }

    public void setPayer_email(String payer_email) {
        this.payer_email = payer_email;
    }

    public String getPayer_phone() {
        return payer_phone;
    }

    public void setPayer_phone(String payer_phone) {
        this.payer_phone = payer_phone;
    }
}
