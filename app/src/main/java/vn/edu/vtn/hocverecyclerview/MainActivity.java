package vn.edu.vtn.hocverecyclerview;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.vtn.adapter.ContactsAdapter;
import vn.edu.vtn.model.Contact;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvTest;
    ContactsAdapter contactsAdapter;
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        rvTest = findViewById(R.id.rvTest);
        contacts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            contacts.add(new Contact("Test " + i, i % 2 == 0));
        }
        contactsAdapter = new ContactsAdapter(MainActivity.this, contacts);
        rvTest.setAdapter(contactsAdapter);
        rvTest.setLayoutManager(new LinearLayoutManager(this));

    }

    private void addEvents() {
        contactsAdapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Contact contact = contacts.get(position);
                Toast.makeText(MainActivity.this, contact.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
