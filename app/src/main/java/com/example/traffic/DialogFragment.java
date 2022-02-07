package com.example.traffic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class DialogFragment extends androidx.fragment.app.DialogFragment {

    String imagen;
    boolean gps, lento, accidente, cierre;
    Switch SwAccidente, SwTrafico_lento, SwCierre, SwGps;
    ToggleButton moto, auto, bus;
    Button confirmar;
    ImageView ivImagen;

    public static DataBase mDataBase;

    public DialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }

    private AlertDialog crearDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialog, null);
        builder.setView(v);

        mDataBase = new DataBase(getActivity(), Credentials.DB_NAME, null, Credentials.versionDB);

        ivImagen = v.findViewById(R.id.img_vehiculo);
        SwTrafico_lento = v.findViewById(R.id.SwLnto);
        SwAccidente = v.findViewById(R.id.SwAccidente);
        SwCierre = v.findViewById(R.id.SwCierre);
        SwGps = v.findViewById(R.id.SwGps);

        moto = v.findViewById(R.id.imgMoto);
        auto = v.findViewById(R.id.imgAuto);
        bus = v.findViewById(R.id.imgBus);

        confirmar = v.findViewById(R.id.confirmar);

        SwTrafico_lento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lento = true;
                } else {
                    lento = false;
                }
            }
        });

        SwAccidente.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    accidente = true;
                } else {
                    accidente = false;
                }
            }
        });

        SwCierre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cierre = true;
                } else {
                    cierre = false;
                }
            }
        });

        SwGps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gps = true;
                } else {
                    gps = false;
                }
            }
        });

        moto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                imagen = (String) view.getTag();
                Log.i("d_exception:", "Insert =  " + imagen);
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                imagen = (String) view.getTag();
                Log.i("d_exception:", "Insert =  " + imagen);
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                imagen = (String) view.getTag();
                Log.i("d_exception:", "Insert =  " + imagen);
            }
        });
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Insert_Report();
                getDialog().cancel();
            }
        });
        return builder.create();
    }


    public void Insert_Report() {

        String direccion = "Calle 80 # 72a - 305";

        if (gps) {

            try {
                ContentValues contenedor = new ContentValues();
                contenedor.put("imagen",imagen);
                contenedor.put("direccion", direccion);
                contenedor.put("lento", String.valueOf(lento));
                contenedor.put("accidente", String.valueOf(accidente));
                contenedor.put("cierre", String.valueOf(cierre));

                Log.i("d_exception:", "Insert =  " + imagen + " " + direccion + " " + lento + " "+ accidente+ " " + cierre);
                mDataBase.getWritableDatabase().insert("reportes",null,contenedor);
                mDataBase.close();

            } catch (Exception e) {
                Log.i("d_funciones","++++ FichesActivity Insert_Operation EXCEPTION e =  " + e );

            }

        } else {
            Log.i("d_exception:", "Encienda el gps ");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


}