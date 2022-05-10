package com.example.exam_2_b18dccn100;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exam_2_b18dccn100.model.Items;

public class AddActivity extends AppCompatActivity {

    private EditText nameTxt, idTxt;
    private Button addBtn;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        nameTxt = findViewById(R.id.add_nameTxt);
        idTxt = findViewById(R.id.add_idTxt);
        addBtn = findViewById(R.id.add_addBtn);
        Items i = (Items) getIntent().getSerializableExtra("item");
        if(i!=null){
            addBtn.setText("Edit");
            idTxt.setText(Integer.toString(i.getId()));
            nameTxt.setText(i.getName());
        }



        databaseHandler = new DatabaseHandler(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameTxt.getText().toString().trim();
                int id = Integer.parseInt(idTxt.getText().toString().trim());
                if (name == null) {
                    Toast.makeText(AddActivity.this, "DIen ten", Toast.LENGTH_SHORT).show();
                } else {
                    Items items = new Items(name, id);
                    switch (addBtn.getText().toString().trim()) {
                        case "Add":
                            databaseHandler.addItem(items);
                            break;
                        case "Edit":
                            databaseHandler.editItem(items);
                            break;
                    }
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}