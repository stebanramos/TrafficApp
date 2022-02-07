package com.example.traffic;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CurrentStatisticsActivity extends AppCompatActivity {

    private BarChart barChart;
    private LineDataSet lineDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_statistics);


        long horaActual = System.currentTimeMillis();

        Log.i("Funciones", "horaActual is: " + horaActual);
        int intervaloHoras = 2;


        // Enlazamos al XML
        barChart = findViewById(R.id.barChart);


        request(22);


        //request();
    }


    public void request(long horaPartida) {


        String origen = "6.281918844474129,-75.58179616175146";
        String destino = "6.279380573460187,-75.57150059802822";
        //String horaPartida = "2343641500";
        String modo = "transit";
        String APY_KEY = "AIzaSyDNwLjK0NKU8aKSYVorwiRQMZfaeas3imI";

        Log.i("Parametros", "origen = " + origen + ", destino = " + destino);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + origen + "&destination=" + destino + "&departure_time=now&key=" + APY_KEY;
        //String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+origen+"&destination="+destino+"&key="+APY_KEY;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                        Log.i("Funciones", "Response is: " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.i("Funciones", "json is: " + jsonObject.toString());

                            String routes = jsonObject.getString("routes");

                            Log.i("Funciones", "String routes is: " + routes);

                            JSONArray jsonArray = new JSONArray(routes);
                            Log.i("Funciones", "jsonArray is: " + jsonArray.toString());

                            JSONObject jobj = jsonArray.getJSONObject(0);
                            String legs = jobj.getString("legs");
                            Log.i("Funciones", "String legs is: " + legs);

                            JSONArray jsonArrayLegs = new JSONArray(legs);
                            Log.i("Funciones", "jsonArrayLegs is: " + jsonArrayLegs.toString());

                            JSONObject jobjLegs = jsonArrayLegs.getJSONObject(0);

                            Log.i("Funciones", "jobjLegs is: " + jobjLegs.toString());

                            // Creamos un set de datos
                            ArrayList<BarEntry> rutaUno = new ArrayList<>();

                            JSONObject distance = (JSONObject) jobjLegs.get("distance");
                            JSONObject duration = (JSONObject) jobjLegs.get("duration");

                            String distancia = distance.getString("text");
                            Log.i("Funciones", "String distance is: " + distancia.toString());
                            String duracion = duration.getString("text");
                            Log.i("Funciones", "String duration is: " + duracion);


                            rutaUno.add(new BarEntry((float) 0, (float) 7));

                            // Unimos los datos al data set
                            BarDataSet barDataSet = new BarDataSet(rutaUno, "Rutas 1");

                            //Ponemos color a cada barra
                            barDataSet.setColor(R.color.AzulTraffic);

                            //Seperacion entre barras
                            barDataSet.setBarBorderWidth(0.9f);

                            // Asociamos al gráfico
                            BarData barData = new BarData();
                            barData.addDataSet(barDataSet);
                            barChart.setData(barData);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                Log.i("Funciones", "That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}