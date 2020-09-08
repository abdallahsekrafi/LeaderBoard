package com.zwir.leaderboard.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.zwir.leaderboard.R;
import com.zwir.leaderboard.retrofit.ApiClient;
import com.zwir.leaderboard.retrofit.ApiInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    EditText firstName,lastName,emailAddress,projectLink;
    private final String BASE_GF_URL ="https://docs.google.com/forms/d/e/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        firstName=findViewById(R.id.first_name);
        lastName=findViewById(R.id.last_name);
        emailAddress=findViewById(R.id.email);
        projectLink=findViewById(R.id.project_link);
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDialog();
            }
        });
    }

    private void sendProject(String emailAddress,String firstName,String lastName,String projectLink) {
        ApiInterface apiService = ApiClient.getClient(BASE_GF_URL).create(ApiInterface.class);
        Call<Boolean>call=apiService.sendProject(emailAddress,firstName,lastName,projectLink);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful())
                   showResultDialog("success");
                if (response.isSuccessful())
                    showResultDialog("failure");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                showResultDialog("failure");
            }
        });
    }
    private void hideKeyboard() {
        View v = getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
    void showResultDialog(String type){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(getLayoutInflater().inflate(type.equals("success")?R.layout.dialog_success:R.layout.dialog_failure, null));
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    void showConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_confirm, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();
        customLayout.findViewById(R.id.cancel_dg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        customLayout.findViewById(R.id.confirm_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                sendProject(emailAddress.getText().toString(),firstName.getText().toString(),
                        lastName.getText().toString(),projectLink.getText().toString());
                hideKeyboard();
            }
        });
        dialog.show();
    }
}
