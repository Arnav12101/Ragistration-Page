package com.example.ragistration_page;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


EditText name, phone, email, dob, password , state;
Button btnsubmit, btnreset, btnupdate, btnview, btndelete;
dbhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.namefield);
        password = findViewById(R.id.passwordfield);
        email = findViewById(R.id.emailfield);
        phone = findViewById(R.id.phonefield);
        dob = findViewById(R.id.dobfield);
        state = findViewById(R.id.statefield);
    

        btnsubmit = (Button) findViewById(R.id.submitbutton);
        btnupdate = (Button) findViewById(R.id.updatebutton);
        btnview = (Button) findViewById(R.id.display);
        btnreset = (Button) findViewById(R.id.reset);
        btndelete = (Button) findViewById(R.id.deletebutton);
        DB = new dbhelper(this);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                password.setText("");
                email.setText("");
                phone.setText("");
                dob.setText("");
                state.setText("");
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String contacttxt = phone.getText().toString();
                String dobtxt = dob.getText().toString();
                String passwordtxt = password.getText().toString();
                String emailtxt =  email.getText().toString();
                String statetxt = state.getText().toString();

                //String name, String passsword, String dob, String email, String phone, String state
                Boolean checkinsertdata = DB.inputuserdata(nametxt, passwordtxt, dobtxt, emailtxt, contacttxt, statetxt);
                if(checkinsertdata == true)
                {
                    Toast.makeText(MainActivity.this, "DATA ADDED", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "failed to add the data", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String contacttxt = phone.getText().toString();
                String dobtxt = dob.getText().toString();
                String passwordtxt = password.getText().toString();
                String emailtxt =  email.getText().toString();
                String statetxt = state.getText().toString();

                //String name, String password, String dob, String email, String phone, String state
                Boolean updateuserdata = DB.updateuserdata(nametxt, passwordtxt, dobtxt, emailtxt, contacttxt, statetxt);
                if(updateuserdata == true)
                {
                    Toast.makeText(MainActivity.this, "DATA UPDATED", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "FAILED TO UPDATE DATA", Toast.LENGTH_LONG).show();
                }
            }
        });


        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String contacttxt = phone.getText().toString();
                String dobtxt = dob.getText().toString();
                String passwordtxt = password.getText().toString();
                String emailtxt =  email.getText().toString();
                String statetxt = state.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdata(nametxt);
                if(checkdeletedata == true)
                {
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed to delete", Toast.LENGTH_LONG).show();
                }
            }
        });



        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "No record found", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Name: "+res.getString(0)+"\n");
                    buffer.append("Password: "+res.getString(1)+"\n");
                    buffer.append("DOB: "+res.getString(2)+"\n");
                    buffer.append("email: "+res.getString(3)+"\n");
                    buffer.append("Phone number: "+res.getString(4)+"\n");
                    buffer.append("State: "+res.getString(5)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
