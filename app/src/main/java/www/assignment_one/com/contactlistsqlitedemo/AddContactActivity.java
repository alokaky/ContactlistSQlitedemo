package www.assignment_one.com.contactlistsqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {
    EditText name,phonenumber;
    Button add;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name = (EditText)findViewById(R.id.nameEditText);
        phonenumber = (EditText)findViewById(R.id.phoneNumberEditText);
        add = (Button)findViewById(R.id.addButton);

        db = new DatabaseHandler(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addContact(new Contact(name.getText().toString(),phonenumber.getText().toString()));
                Toast.makeText(AddContactActivity.this, "Contact Sucessfully Added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddContactActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}
