package com.example.content_provider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;


import com.example.content_provider.model.Contact;

import java.util.ArrayList;

public class Danhba extends AppCompatActivity {

    ListView lvDanhba;
    ArrayList<Contact> dsDanhba;
    ArrayAdapter<Contact> adapterDanhba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhba);
        addControls();
        showAllContactFromDevice();
    }

    private void showAllContactFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        dsDanhba.clear();
        while (cursor.moveToNext()) {
            String tenCotname = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int vitriCotname = cursor.getColumnIndex(tenCotname);
            int vitriCotPhone = cursor.getColumnIndex(tenCotPhone);
            String name = cursor.getString(vitriCotname);
            String phone = cursor.getString(vitriCotPhone);

            Contact contact = new Contact(phone, name);
            dsDanhba.add(contact);
            adapterDanhba.notifyDataSetChanged();
        }
        cursor.close();
    }

    private void addControls() {
        lvDanhba = findViewById(R.id.lvDanhba);
        dsDanhba = new ArrayList<>();
        adapterDanhba = new ArrayAdapter<>(Danhba.this, android.R.layout.simple_list_item_1, dsDanhba);
        lvDanhba.setAdapter(adapterDanhba);
    }
}
