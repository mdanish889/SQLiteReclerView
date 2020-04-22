package com.example.sqlitereclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button createDbBtn,addValuesBtn,getValuesBtn;
    private SQLiteDbClass objectSQLiteDbClass;

    private EditText nameEt,addressET;
private TextView getValuesTV;



private ArrayList<DbModelClass> objectDbModelClassArrayList;
private RecyclerView objectRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectXML();
    }
    private void connectXML()
    {
        try {
            createDbBtn=findViewById(R.id.createDbBtn);
            objectSQLiteDbClass=new SQLiteDbClass(this);

            nameEt=findViewById(R.id.nameET);
            addressET=findViewById(R.id.addressET);

            addValuesBtn=findViewById(R.id.addValuesBtn);
            getValuesBtn=findViewById(R.id.getValuesBtn);

            getValuesTV=findViewById(R.id.getValuesTV);
           objectDbModelClassArrayList=new ArrayList<>();

           objectRecyclerView=findViewById(R.id.RV);


            createDbBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createDb();
                }
            });
            addValuesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addValuesToDatabase();
                }
            });getValuesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getValuesFromDb();
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(this,"connectXML"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void createDb(){

        try {
           objectSQLiteDbClass.getReadableDatabase();
        }
        catch (Exception e)
        {
            Toast.makeText(this,"createDb"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    private void addValuesToDatabase(){

        try {
         if(!nameEt.getText().toString().isEmpty()&&!addressET.getText().toString().isEmpty())
         {
                SQLiteDatabase objectSqlLiteDatabase=objectSQLiteDbClass.getWritableDatabase();
                if(objectSqlLiteDatabase!=null)
                {
                    ContentValues objectContentValues=new ContentValues();
                    objectContentValues.put("Name",nameEt.getText().toString());

                    objectContentValues.put("Address",addressET.getText().toString());
               long check= objectSqlLiteDatabase.insert("classtable",null,objectContentValues);
                if(check!=-1)
                {
                    Toast.makeText(this,"values inserted",Toast.LENGTH_SHORT).show();
                    nameEt.setText(null);
                    addressET.setText(null);
                    nameEt.requestFocus();
                }
                else{
                    Toast.makeText(this,"Fail to add values",Toast.LENGTH_SHORT).show();
                }
                }
                else
                {
                    Toast.makeText(this,"DB object is null",Toast.LENGTH_SHORT).show();

                }

         }
         else if(nameEt.getText().toString().isEmpty())
         {
             Toast.makeText(this,"Enter the name",Toast.LENGTH_SHORT).show();
             nameEt.requestFocus();

         }
         else if(addressET.getText().toString().isEmpty())
         {
             Toast.makeText(this,"Enter the adress",Toast.LENGTH_SHORT).show();
             addressET.requestFocus();

         }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"addValuesToDatabase"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void getValuesFromDb()
    {

        try
        {
         SQLiteDatabase objectSqLiteDatabase=objectSQLiteDbClass.getReadableDatabase();
         if(objectSqLiteDatabase!=null)
         {
            Cursor objectCursor= objectSqLiteDatabase.rawQuery("select *from classtable",null);
            StringBuffer objectStringBuffer=new StringBuffer();

             if (objectCursor.getCount() != 0) {
                 while (objectCursor.moveToNext())
                 {
                     DbModelClass objectDbModelClass=new DbModelClass();
                     objectDbModelClass.setName(objectCursor.getString(0));

                     objectDbModelClass.setAddress(objectCursor.getString(1));

                     objectDbModelClassArrayList.add(objectDbModelClass);


    //                 objectStringBuffer.append("Name"+objectCursor.getString(0)+"\n");
  //                   objectStringBuffer.append("Address"+objectCursor.getString(1)+"\n");
                 }
      //           getValuesTV.setText(objectStringBuffer);
                 objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                 DbAdapterClass objectDbAdapterClass=new DbAdapterClass(objectDbModelClassArrayList);

                 objectRecyclerView.setAdapter(objectDbAdapterClass);
             }
             else {
                 Toast.makeText(this,"No Value Retrirved",Toast.LENGTH_SHORT).show();

             }
         }
         else
         {
             Toast.makeText(this,"Db object is null",Toast.LENGTH_SHORT).show();
         }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"getValuesFromDb"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}





