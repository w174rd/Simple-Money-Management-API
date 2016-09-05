package com.najib.task4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by w174rd on 9/4/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    private final Activity _context;
    private final String[] _text;
    private String[] kata;

    public CustomAdapter(Activity context, String[] text) {
        super(context, R.layout.list_item, text);
        this._context = context;
        this._text = text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String kalimat = _text[position];
        kata = kalimat.split(" ");
        LayoutInflater inflater = _context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.text);
        txtTitle.setText(kata[1]);

        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.text2);
        txtTitle2.setText("Rp. "+kata[2]);

        return rowView;
    }

}