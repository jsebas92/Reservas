package com.example.sebastian.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by sebastian on 30/05/16.
 */
public class AdapterCancha extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Cancha> canchaItems;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public AdapterCancha(Activity activity, List<Cancha> canchaItems) {
        this.activity = activity;
        this.canchaItems =canchaItems;
    }

    @Override
    public int getCount() {
        return canchaItems.size();
    }

    @Override
    public Object getItem(int location) {
        return canchaItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_cancha_row, null);

        //if (imageLoader == null)
          //  imageLoader = AppController.getInstance().getImageLoader();
        //NetworkImageView thumbNail = (NetworkImageView) convertView
                //.findViewById(R.id.thumbnail);
        TextView idCancha = (TextView) convertView.findViewById(R.id.txtCanchaId);
        TextView precio = (TextView) convertView.findViewById(R.id.txtCanchaPrecio);
        TextView descripcion = (TextView) convertView.findViewById(R.id.txtCanchaDescripcion);
        //TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Cancha c = canchaItems.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // id
        idCancha.setText(String.valueOf(c.getIdCancha()));

        // precio
        precio.setText("Precio: $" + String.valueOf(c.getPrecio()));



        // release year
        descripcion.setText(String.valueOf(c.getDescripcion()));

        return convertView;
    }

}
