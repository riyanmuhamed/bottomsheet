package com.example.bottomsheetapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button openBottomSheetButton = findViewById(R.id.button_open_bottom_sheet);
        openBottomSheetButton.setOnClickListener(view -> openBottomSheet());

        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
    }

    private void openBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.bottom_sheet_layout, null);

        EditText editTextItem = bottomSheetView.findViewById(R.id.edit_text_item);
        Button buttonAddItem = bottomSheetView.findViewById(R.id.button_add_item);
        ListView listViewItems = bottomSheetView.findViewById(R.id.list_view_items);

        listViewItems.setAdapter(adapter);

        buttonAddItem.setOnClickListener(v -> {
            String item = editTextItem.getText().toString().trim();
            if (!item.isEmpty()) {
                itemList.add(item);
                adapter.notifyDataSetChanged();
                editTextItem.setText(""); // Clear the input field
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}
