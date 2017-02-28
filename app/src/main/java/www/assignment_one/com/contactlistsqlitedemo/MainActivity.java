package www.assignment_one.com.contactlistsqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView contectListView;
    Button addnewcontact;
    DatabaseHandler db;
    CustomListAdapter adapter1;
    ArrayList<Contact> contectlist = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contectListView = (ListView)findViewById(R.id.contectListView);
        addnewcontact = (Button)findViewById(R.id.addnewcontact);

        db = new DatabaseHandler(this);


        contectlist = db.getAllContacts();

        adapter1 = new CustomListAdapter(MainActivity.this,contectlist);
        contectListView.setAdapter(adapter1);
        addnewcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddContactActivity.class);
                startActivity(i);
            }
        });

        contectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db.deleteContact(contectlist.get(position));

                contectlist.remove(position);
                adapter1.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Contact Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
