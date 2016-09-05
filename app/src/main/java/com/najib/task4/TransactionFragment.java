package com.najib.task4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.najib.task4.database.DatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionFragment extends Fragment implements Button.OnClickListener {
    Button expenses, income;
    EditText expanses_desc, expanses_amount, income_desc, income_amount;
    View view = null;
    private DatabaseHelper mydb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_transaction,container, false);
        view = inflater.inflate(R.layout.fragment_transaction, container, false);

        expenses = (Button) view.findViewById(R.id.button_transaction);
        income = (Button) view.findViewById(R.id.button2_transaction);
        expanses_desc = (EditText) view.findViewById(R.id.editText_transaction);
        expanses_amount = (EditText) view.findViewById(R.id.editText2_transaction);
        income_desc = (EditText) view.findViewById(R.id.editText3_transaction);
        income_amount = (EditText) view.findViewById(R.id.editText4_transaction);

        expenses.setOnClickListener(this);
        income.setOnClickListener(this);

        mydb = new DatabaseHelper(view.getContext());

        return view;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_transaction:
                String desc = expanses_desc.getText().toString();
                String amount = expanses_amount.getText().toString();
                String mode = "expanses";
                save_data(mode, desc, amount);
                break;
            case R.id.button2_transaction:
                String desc2 = income_desc.getText().toString();
                String amount2 = income_amount.getText().toString();
                String mode2 = "income";
                save_data(mode2, desc2, amount2);
                break;
            default:
                break;
        }
    }

    public void save_data(String mode, String desc, String amount) {
        if (mode.equals("expanses")) {
            if (!desc.isEmpty() && !amount.isEmpty()) {
                expanses_desc.setText("");
                expanses_amount.setText("");
                expanses_desc.setFocusable(true);
                expanses_amount.setFocusable(true);

                mydb.saveData("ex",desc,amount);
                Toast.makeText(view.getContext(), "Data Expenses was Saved..", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(view.getContext(), "Data Expanses's Empty..", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (!desc.isEmpty() && !amount.isEmpty()) {
                income_desc.setText("");
                income_amount.setText("");
                income_desc.setFocusable(true);
                income_amount.setFocusable(true);

                mydb.saveData("in",desc,amount);
                Toast.makeText(view.getContext(), "Data Income was Saved..", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(view.getContext(), "Data Income's Empty..", Toast.LENGTH_SHORT).show();
            }
        }
    }
}