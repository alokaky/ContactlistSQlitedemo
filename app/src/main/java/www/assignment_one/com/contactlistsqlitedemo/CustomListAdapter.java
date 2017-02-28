package www.assignment_one.com.contactlistsqlitedemo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alok on 28/Dec/16.
 */

public class CustomListAdapter extends ArrayAdapter<Contact> {

    private final Activity context;
    private final ArrayList<Contact> contectlist;

    public CustomListAdapter(Activity context, ArrayList<Contact> contectlist) {
        super(context,R.layout.row_layout,contectlist);
        this.context = context;
        this.contectlist = contectlist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = context.getLayoutInflater();
        View rowview = inf.inflate(R.layout.row_layout,null,true);

        TextView name = (TextView)rowview.findViewById(R.id.nameTextView);
        TextView phoneNumber = (TextView)rowview.findViewById(R.id.phoneNumberTextView);
        name.setText("Name : "+contectlist.get(position).name);
        phoneNumber.setText("Phone Number : "+contectlist.get(position).phone_number);

        return rowview;
    }
}
