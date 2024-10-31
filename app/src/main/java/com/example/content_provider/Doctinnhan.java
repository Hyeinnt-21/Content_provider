package com.example.content_provider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.content_provider.adapter.AdapterTinnhan;
import com.example.content_provider.model.Tinnhan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class Doctinnhan extends AppCompatActivity {
    private static final int REQUEST_CODE_ASK_PERMISSION = 1001;

    ListView lvDocTinnhan;
    ArrayList<Tinnhan> dsTinNhan;
    AdapterTinnhan adapterTinnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctinnhan);
        addControl();
        docToanBoTinNhan();
    }
    private void docToanBoTinNhan() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        dsTinNhan.clear();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int indexPhoneNumber = cursor.getColumnIndex("address");
                int indexTimeStamp = cursor.getColumnIndex("date");
                int indexBody = cursor.getColumnIndex("body");

                String phoneNumber = cursor.getString(indexPhoneNumber);
                String timeStamp = cursor.getString(indexTimeStamp);
                String body = cursor.getString(indexBody);

                if (phoneNumber != null && timeStamp != null && body != null) {
                    dsTinNhan.add(new Tinnhan(phoneNumber, sdf.format(Long.parseLong(timeStamp)), body));
                }
            }
            cursor.close();
            adapterTinnhan.notifyDataSetChanged();
        }
    }
    private  void addControl(){
        lvDocTinnhan = findViewById(R.id.lvDocTinNhan);
        dsTinNhan = new ArrayList<>();
        adapterTinnhan = new AdapterTinnhan(
                Doctinnhan.this,R.layout.item_tinnhan,dsTinNhan
        );
        lvDocTinnhan.setAdapter(adapterTinnhan);
    }
}