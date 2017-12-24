package com.fakuzan.mengenalhewan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.fakuzan.mengenalhewan.R;
import com.fakuzan.mengenalhewan.interfaces.CustomItemClickListener;
import com.fakuzan.mengenalhewan.model.Amfibi;
import com.fakuzan.mengenalhewan.model.Aves;
import com.fakuzan.mengenalhewan.model.Hewan;
import com.fakuzan.mengenalhewan.model.Insect;
import com.fakuzan.mengenalhewan.model.Mamalia;
import com.fakuzan.mengenalhewan.model.Reptil;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.ColorFilterTransformation;

/**
 * Created by intel on 19/12/2017.
 */

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Hewan> hewan;

    CustomItemClickListener listener;

    public KelasAdapter(Context context, ArrayList<Hewan> hewan, CustomItemClickListener listener) {
        this.context = context;
        this.hewan = hewan;
        this.listener = listener;
    }

    MultiTransformation multi = new MultiTransformation(
            new ColorFilterTransformation(Color.parseColor("#80000000")),
            new CenterCrop(),
            new RoundedCorners(20)
    );

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kelas, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, myViewHolder.getAdapterPosition());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String nama = "";
        String bg = "";
        try {
            if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Mamalia.class.getName()))){
                nama = ((Mamalia) hewan.get(position)).getNamaKelas();
                bg = ((Mamalia) hewan.get(position)).getGambarKelas();
            }
            if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Amfibi.class.getName()))){
                nama = ((Amfibi) hewan.get(position)).getNamaKelas();
                bg = ((Amfibi) hewan.get(position)).getGambarKelas();
            }
            if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Aves.class.getName()))){
                nama = ((Aves) hewan.get(position)).getNamaKelas();
                bg = ((Aves) hewan.get(position)).getGambarKelas();
            }
            if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Insect.class.getName()))){
                nama = ((Insect) hewan.get(position)).getNamaKelas();
                bg = ((Insect) hewan.get(position)).getGambarKelas();
            }
            if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Reptil.class.getName()))){
                nama = ((Reptil) hewan.get(position)).getNamaKelas();
                bg = ((Reptil) hewan.get(position)).getGambarKelas();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Glide.with(context)
                .load(getImage(bg))
                .apply(RequestOptions.bitmapTransform(multi))
                .into(holder.bg);
        holder.name.setText(nama);
    }

    @Override
    public int getItemCount() {
        return hewan.size();
    }

    private int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView bg;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            bg = (ImageView) itemView.findViewById(R.id.iv_item_list_bg);
            name = (TextView) itemView.findViewById(R.id.tv_item_list_name);
        }
    }
}
