package com.example.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.show();

    }


    public void showDialog() {

        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "Dialogo");

    }

    public void showReportes() {

        Intent intent = new Intent(MainActivity.this, ReportsActivity.class);
        startActivity(intent);
    }

    public void showStaticCurrent() {

        Intent intent = new Intent(MainActivity.this, CurrentStatisticsActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                showDialog();
                return true;

            case R.id.item2:
                showReportes();
                return true;

            case R.id.item3:
                showStaticCurrent();
                return true;

            case R.id.item4:

                return true;

            default:
                return false;

        }
    }
}