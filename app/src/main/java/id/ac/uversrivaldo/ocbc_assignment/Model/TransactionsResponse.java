package id.ac.uversrivaldo.ocbc_assignment.Model;

import java.util.ArrayList;

public class TransactionsResponse {

    private String status;
    private ArrayList data;

    public TransactionsResponse() {
    }

    public TransactionsResponse(String status, ArrayList data) {
        this.status = status;
        this.data = data;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }



}
