package www.assignment_one.com.contactlistsqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alok on 28/Dec/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final  int database_version = 1;
    private static  final  String database_name = "contactlist";
    private static  final String table_contacts = "contacts";

    private  static final String key_Id = "id";
    private  static  final String key_Name = "name";
    private static  final String key_Ph_No = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, database_name, null, database_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_Contacts_Table = "create table "+ table_contacts + "("
                + key_Id + " integer primary key,"+key_Name+" text,"
                + key_Ph_No +" text" + ")";
        db.execSQL(create_Contacts_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exits " + table_contacts);

        onCreate(db);
    }


    //all CRUD

    void addContact(Contact contact){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_Name,contact.getName());
        values.put(key_Ph_No,contact.getPhone_number());

        db.insert(table_contacts,null,values);
        db.close();
    }

    Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table_contacts,new String[]{key_Id,key_Name,key_Ph_No},key_Id + "=?",
                                                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor != null)
        {
            cursor.moveToFirst();

        }
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return contact;
    }

    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contactlist = new ArrayList<Contact>();

        String selectQuery = "select * from " + table_contacts;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone_number(cursor.getString(2));

                contactlist.add(contact);
            }while (cursor.moveToNext());
        }
        return contactlist;
    }


    public int updateContact (Contact contact){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_Name,contact.getName());
        values.put(key_Ph_No,contact.getPhone_number());
        return db.update(table_contacts,values,key_Id + "=?",
                new String[]{String.valueOf(contact.getId())});

    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(table_contacts,key_Id + " =?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getContactCount()
    {
        String countQuery = "select * from "+table_contacts;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }
}
