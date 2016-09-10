package com.najib.task4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.najib.task4.database.DatabaseHelper;
import com.najib.task4.database.PopupUpdate;

public class DashboardFragment extends Fragment {
    ListView listView, listView2;
    String[][] value_text_ex,value_text_in;
    TextView value1, value2, balance;
    Integer jum1 = 0, jum2 = 0;
    DatabaseHelper mydb;

    /*private SimpleCursorAdapter adapter;
    final String[] from = new String[]{DatabaseHelper.COLUMN_DESC, DatabaseHelper.COLUMN_AMOUNT};
    final int[] to = new int[]{R.id.text_item_1, R.id.text_item_2};*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_dashboard,container, false);\
        View view = null;
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // instanstiasi kelas DBDataSource
       /* dataSource = new DBDataSource(view.getContext());
        dataSource.open();*/

        value1 = (TextView) view.findViewById(R.id.value_1);
        value2 = (TextView) view.findViewById(R.id.value_2);
        balance = (TextView) view.findViewById(R.id.textView3);
        listView = (ListView) view.findViewById(R.id.ListViewEx);
        listView2 = (ListView) view.findViewById(R.id.ListViewIn);

        mydb = new DatabaseHelper(view.getContext());

        Cursor datasEx = mydb.listData("ex");
        Cursor datasIn = mydb.listData("in");

         /*adapter = new SimpleCursorAdapter(view.getContext(), R.layout.list_item, datasEx, from, to, 0);
        adapter.notifyDataSetChanged();*/

        /* === MULAI EXPENSES === */
        value_text_ex = new String[datasEx.getCount()][3];
        Integer i = 0;
        while (datasEx.moveToNext()) {
            value_text_ex[i][0] = datasEx.getString(0);
            value_text_ex[i][1] = datasEx.getString(1);
            value_text_ex[i][2] = datasEx.getString(2);

            jum1 = jum1 + Integer.parseInt(datasEx.getString(2));
            i++;
        }
        CustomAdapter adapter = new CustomAdapter(getActivity(), value_text_ex);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String[] value = (String[]) listView.getItemAtPosition(position);
                       // Toast.makeText(view.getContext(), "Id: " + position + " Name Product: " + value[1], Toast.LENGTH_LONG).show();

                        String code = value[0];
                        String mDesc = value[1];
                        String mAmount = value[2];

                        Intent modify_intent = new Intent(view.getContext(), PopupUpdate.class);
                        modify_intent.putExtra("mode", "expenses");
                        modify_intent.putExtra("id", code);
                        modify_intent.putExtra("desc", mDesc);
                        modify_intent.putExtra("amount", mAmount);

                        startActivity(modify_intent);
                    }
                }
        );

        value1.setText("Rp. " + jum1);

        /* === AKHIR EXPENSES === */

        /* === MULAI INCOME === */
        value_text_in = new String[datasIn.getCount()][3];
        Integer j = 0;
        while (datasIn.moveToNext()) {
            value_text_in[j][0] = datasIn.getString(0);
            value_text_in[j][1] = datasIn.getString(1);
            value_text_in[j][2] = datasIn.getString(2);

            jum2 = jum2 + Integer.parseInt(datasIn.getString(2));
            j++;
        }
        CustomAdapter adapterIn = new CustomAdapter(getActivity(), value_text_in);

        listView2.setAdapter(adapterIn);
        listView2.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String[] value = (String[]) listView2.getItemAtPosition(position);
                        // Toast.makeText(view.getContext(), "Id: " + position + " Name Product: " + value[1], Toast.LENGTH_LONG).show();

                        String code = value[0];
                        String mDesc = value[1];
                        String mAmount = value[2];

                        Intent modify_intent = new Intent(view.getContext(), PopupUpdate.class);
                        modify_intent.putExtra("mode", "income");
                        modify_intent.putExtra("id", code);
                        modify_intent.putExtra("desc", mDesc);
                        modify_intent.putExtra("amount", mAmount);

                        startActivity(modify_intent);
                    }
                }
        );

        value2.setText("Rp. " + jum2);

        /* === AKHIR INCOME === */

        Integer jum = jum2 - jum1;
        balance.setText("Rp. " + jum.toString());

        return view;
    }
}
