package com.digicon_valley.database_1;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper mydb;
    EditText edit_name,edit_sureName,edit_marks,edit_id;
    Button btn_add,view_all;
    Button btn_update;
    Button btn_Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Delete=findViewById(R.id.button_delete);
        mydb=new DataBaseHelper(this);
        edit_id=findViewById(R.id.editText_id);
        edit_name=findViewById(R.id.editText_name);
        edit_sureName=findViewById(R.id.editText_surname);
        edit_marks=findViewById(R.id.editText_Marks);
        btn_add=findViewById(R.id.button_add);
        view_all=findViewById(R.id.button_viewAll);
        btn_update=findViewById(R.id.button_update);
        AddData();
        viewAll();
        UpdateData();
        Delete_Data();
    }
    public void Delete_Data(){
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deleteRow=mydb.deletDAta(edit_id.getText().toString());
                if (deleteRow>0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void UpdateData(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isupdated =mydb.upDateData(edit_id.getText().toString(),edit_name.getText().toString()
                        ,edit_sureName.getText().toString()
                        ,edit_marks.getText().toString());
                if (isupdated==true)
                    Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_SHORT).show();



            }
        });
    }

    public void AddData(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             boolean isInserted= mydb.inseartData(edit_name.getText().toString()
                        ,edit_sureName.getText().toString()
                        ,edit_marks.getText().toString());
             if(isInserted==true)
                 Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                 else
                     Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                 edit_name.getText();

            }
        });
    }
    public void viewAll(){
        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res= mydb.getAllData();
               if(res.getCount()==0){
                   showMessage("Error","Nothing found");
                   return;
               }StringBuffer buffer=new StringBuffer();
               while(res.moveToNext()){
                   buffer.append("id :"+res.getString(0)+"\n");
                   buffer.append("Name :"+res.getString(1)+"\n");
                   buffer.append("SureName :"+res.getString(2)+"\n");
                   buffer.append("Marks :"+res.getString(3)+"\n\n");
               }

                showMessage("Data",buffer.toString());

            }
        });

    }
    public  void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);

        builder.show();
    }

}
