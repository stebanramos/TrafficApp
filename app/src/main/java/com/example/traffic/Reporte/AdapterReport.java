package com.example.traffic.Reporte;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traffic.R;

import java.util.List;

public class AdapterReport extends RecyclerView.Adapter<AdapterReport.ProductoViewHolder>{

    private List<Report> listaProductos;

    public AdapterReport(List<Report>listaProductos){
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reportes_card_view, viewGroup, false);

        return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder productoViewHolder, int i) {
        productoViewHolder.ivImagen.setImageResource(listaProductos.get(i).getImagen());
        productoViewHolder.tvDireccion.setText(listaProductos.get(i).getDireccion());
        productoViewHolder.rbAccidente.setChecked(listaProductos.get(i).isAcidente());
        productoViewHolder.rbCierre.setChecked(listaProductos.get(i).isCierre());
        productoViewHolder.rbTrafico_lento.setChecked(listaProductos.get(i).isTrafico_lento());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImagen;
        TextView tvDireccion;
        RadioButton rbAccidente, rbTrafico_lento, rbCierre;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.img_vehiculo);
            tvDireccion = itemView.findViewById(R.id.direccion);
            rbAccidente = itemView.findViewById(R.id.accidente);
            rbCierre = itemView.findViewById(R.id.cierre);
            rbTrafico_lento = itemView.findViewById(R.id.trafico_lento);
        }
    }
}
