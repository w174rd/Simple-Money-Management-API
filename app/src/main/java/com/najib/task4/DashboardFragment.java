package com.najib.task4;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.najib.task4.database.DatabaseHelper;

public class DashboardFragment extends Fragment {
    ListView listView,listView2;
    String[] valueTextEx,valueTextIn;
    TextView value1,value2,balance;
    Integer jum1=0,jum2=0;
    DatabaseHelper mydb;

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

        /* MULAI EXPENSES */
        valueTextEx = new String[datasEx.getCount()];
        Integer i = 0;
        while (datasEx.moveToNext()) {
            valueTextEx[i] = datasEx.getString(0)+" "+datasEx.getString(1)+" "+datasEx.getString(2);
            jum1 = jum1+Integer.parseInt(datasEx.getString(2));
            i++;
        }
        CustomAdapter adapter = new CustomAdapter(getActivity(), valueTextEx);
        listView.setAdapter(adapter);

        value1.setText("Rp. "+jum1);
        /* AKHIR EXPENSES */

        /* MULAI INCOME */
        valueTextIn = new String[datasIn.getCount()];
        Integer j = 0;
        while (datasIn.moveToNext()) {
            valueTextIn[j] = datasIn.getString(0)+" "+datasIn.getString(1)+" "+datasIn.getString(2);
            jum2 = jum2+Integer.parseInt(datasIn.getString(2));
            j++;
        }
        CustomAdapter adapterIn = new CustomAdapter(getActivity(), valueTextIn);
        listView2.setAdapter(adapterIn);

        value2.setText("Rp. "+jum2);
        /* AKHIR INCOME */

        Integer jum = jum2-jum1;
        balance.setText("Rp. "+jum.toString());

        return view;
    }
}
