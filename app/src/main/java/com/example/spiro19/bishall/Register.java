package com.example.spiro19.bishall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {

    ProgressDialog pd;
    private static final String REGISTER_URL = "http://androidtestprojectss.xyz/Real%20estate/registration.php";


    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_PASSWORD = "password";


    Button reg;
    EditText editTextusername;
    EditText editTextemail;
    EditText editTextCPassword;
    EditText editTextPassword;
    Spinner country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextusername = (EditText) findViewById(R.id.e1);
        editTextemail = (EditText) findViewById(R.id.e2);
        editTextCPassword = (EditText) findViewById(R.id.e4);
        editTextPassword = (EditText) findViewById(R.id.e3);
        country = (Spinner) findViewById(R.id.s1);

        reg = (Button) findViewById(R.id.b1);
        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                registerUser();
            }

        });


    }

    protected void registerUser() {


        final String username = editTextusername.getText().toString().trim();
        final String email = editTextusername.getText().toString().trim();
        final String Country = country.getSelectedItem().toString().trim();
        final String password = editTextPassword.getText().toString().trim();


        pd = new ProgressDialog(Register.this);
        pd.setTitle("Loading...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                        if (response.trim().equals("Successfully Registered")) {
                            Intent it = new Intent(Register.this, Login.class);

                            startActivity(it);
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, error.toString(), Toast.LENGTH_LONG).show();
                        pd.dismiss();
                    }
                })

        {

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put(KEY_USERNAME, username);
                params.put(KEY_EMAIL, email);
                params.put(KEY_COUNTRY, Country);
                params.put(KEY_PASSWORD, password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
        requestQueue.add(stringRequest);


    }
}

}