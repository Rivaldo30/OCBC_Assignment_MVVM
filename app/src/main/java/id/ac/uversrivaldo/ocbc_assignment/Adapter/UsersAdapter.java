package id.ac.uversrivaldo.ocbc_assignment.Adapter;

import android.content.Context;

import java.util.List;

import id.ac.uversrivaldo.ocbc_assignment.Response.LoginResponse;

public class UsersAdapter {

    private List<LoginResponse> loginResponses;
    private Context context;
    private ClickedItem clickedItem;

    public UsersAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public UsersAdapter() {

    }

    public void SetData(List<LoginResponse> loginResponses) {
        this.loginResponses = loginResponses;
        notifyAll();
    }

    public interface ClickedItem{
        public void ClickedUser(LoginResponse loginResponse);
    }

}
