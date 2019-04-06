package com.example.skvayzer.diamondauthority;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {
    EditText et;
    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et=(EditText) findViewById(R.id.words);
        btn=(ImageButton) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("et", et.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
