package com.example.traffic;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    public static DataBase mDataBase;

    String nombre, correo, confirCorreo, contraseña;
    TextView txtNombre, txtCorreo, txtConfirmCorreo, txtPassword;
    CheckBox check;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDataBase = new DataBase(this, Credentials.DB_NAME, null, Credentials.versionDB);

        txtNombre = findViewById(R.id.nombre);
        txtCorreo = findViewById(R.id.correo);
        txtConfirmCorreo = findViewById(R.id.confirm_correo);
        txtPassword = findViewById(R.id.contraseña);
        check = findViewById(R.id.check);
        btnRegister = findViewById(R.id.Register);
        //btnRegister.setEnabled(false);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    public void Register() {

        nombre = txtNombre.getText().toString();
        correo =  txtCorreo.getText().toString();
        confirCorreo =  txtConfirmCorreo.getText().toString();
        contraseña =  txtPassword.getText().toString();

        if (nombre.isEmpty() || correo.isEmpty() || confirCorreo.isEmpty() || contraseña.isEmpty()){
            Log.i("d_exception:", "Lllene todos los campos  " );
        }else {

            if (correo.equals(confirCorreo)){
                if (check.isChecked()){
                    btnRegister.setEnabled(true);
                    Insert_Usuario();
                }else {
                    Log.i("d_exception:", "Acepte los terminos y condicones  " );
                }
            }else {
                Log.i("d_exception:", "ingrese el mismo correo  " );
            }

        }

    }

    public void login() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void Insert_Usuario() {

        try {
            ContentValues contenedor = new ContentValues();
            contenedor.put("nombre", nombre);
            contenedor.put("correo", correo);
            contenedor.put("contraseña", contraseña);


            Log.i("d_exception:", "Insert usuario =  " + nombre + " " + correo + " " + contraseña );
            mDataBase.getWritableDatabase().insert("usuarios", null, contenedor);
            mDataBase.close();

            login();

        } catch (Exception e) {
            Log.i("d_funciones", "++++ FichesActivity Insert_Operation EXCEPTION e =  " + e);

        }


    }
}