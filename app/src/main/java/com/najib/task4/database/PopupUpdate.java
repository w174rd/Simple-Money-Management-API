package com.najib.task4.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.najib.task4.MainActivity;
import com.najib.task4.R;

/**
 * Created by w174rd on 9/9/2016.
 */
public class PopupUpdate extends Activity implements OnClickListener {
    private DatabaseHelper mydb;
    private EditText desc,amount;
    private Button update_btn,delete_btn;
    private long _id;
    private String table = "";

    String mode,id,mDesc,mAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_popup_update);

        mydb = new DatabaseHelper(this);

        desc = (EditText) findViewById(R.id.description);
        amount = (EditText) findViewById(R.id.amount);

        update_btn = (Button) findViewById(R.id.btn_update);
        delete_btn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        this.mode = intent.getStringExtra("mode");
        this.id = intent.getStringExtra("id");
        this.mDesc = intent.getStringExtra("desc");
        this.mAmount = intent.getStringExtra("amount");

        _id = Long.parseLong(id);

        desc.setText(mDesc);
        amount.setText(mAmount);

        update_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                if(this.mode.equals("expenses")){
                    table = "ex";
                }else{
                    table = "in";
                }
                String uDesc = desc.getText().toString();
                String uAmount = amount.getText().toString();
                mydb.updateData(table, this.id, uDesc, uAmount);

                this.returnHome();
                break;

            case R.id.btn_delete:
                if(this.mode.equals("expenses")){
                    table = "ex";
                }else{
                    table = "in";
                }
                mydb.deleteData(table, this.id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
