package com.example.weatherapp.PNR_Enquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.weatherapp.R;

public class PNR_Error extends AppCompatActivity {
    String pnr_response;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_n_r__error);
        Intent i=getIntent();
        pnr_response=i.getStringExtra("response");
        result=findViewById(R.id.result_TextView);
        result.setText(pnr_response);
    }

    public void done(View view) { this.finish();
    }
}