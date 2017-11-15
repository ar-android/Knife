package com.ahmadrosid.knifelite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.ahmadrosid.knifelite.knife.Knife;
import com.ahmadrosid.knifelite.knife.annotation.KnifeView;
import com.ahmadrosid.knifelite.knife.annotation.KnifeClick;
import com.ahmadrosid.knifelite.knife.annotation.KnifeLongClick;

public class MainActivity extends AppCompatActivity {

    @KnifeView(R.id.btn_simple)
    AppCompatButton btn_simple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Knife.bind(this);

        btn_simple.setText("Login");

    }

    @KnifeClick(R.id.btn_simple)
    public void onClickSimpleButton(View view){
        Toast.makeText(this, "On Click executed.", Toast.LENGTH_SHORT).show();
    }

    @KnifeLongClick(R.id.btn_simple)
    public void onLongClickSimpleButton(View view){
        Toast.makeText(this, "On Long Click  executed.", Toast.LENGTH_SHORT).show();
    }
}
