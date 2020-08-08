package com.numad.numadsu_alangrinberg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;


public class LinkActivity extends AppCompatActivity implements DialogBox.DialogBoxListener {
    ArrayList<String> links = new ArrayList<>();
    ArrayList<String> urlValues;
    private ArrayAdapter<String> adapter;


//    protected ArrayList<String> getLinks(ArrayList<LinkItem> items) {
//        for (LinkItem link: items) {
//            urlValues.add(link.toString());
//        }
//        return urlValues;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_collector);

        links.add(new LinkItem("Alan", "https://www.yahoo.com").toString());

        ListView linksView = (ListView) findViewById(R.id.links_list);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, links);
        linksView.setAdapter(adapter);

//      TODO: placement of these methods, adjust or no?
        linksView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String url = Objects.requireNonNull(adapter.getItem(position));
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


    }

    public void openDialog() {
        DialogBox dialogBox = new DialogBox();
        dialogBox.show(getSupportFragmentManager(), "link entry dialog");
    }

    @Override
    public void applyTextValues(String name, String link) {
//        TODO make sure to take values and return a LinkItem to be displayed
        LinkItem item = new LinkItem(name, link);
        links.add(item.toString());
        Snackbar.make(findViewById(R.id.links_list), "Link " + name + " added successfully",
                Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void onDialogPositiveClick(DialogBox dialog) {
        adapter.notifyDataSetChanged();
    }
}