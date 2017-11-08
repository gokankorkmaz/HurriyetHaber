package kodluyoruz.com.hurriyethaber;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kodluyoruz.com.hurriyethaber.Model.FavoriModel;

public class FavoriAdapter extends RecyclerView.Adapter<FavoriAdapter.FavoriHolder> {
    public ArrayList<FavoriModel> favoriModelList;
    Context context;

    public FavoriAdapter(ArrayList<FavoriModel> favoriModelList, Context context) {
        this.favoriModelList = favoriModelList;
        this.context = context;
    }

    @Override
    public FavoriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favori_list, parent, false);
        return new FavoriHolder(v);

    }


    @Override
    public void onBindViewHolder(final FavoriHolder holder, final int position) {
        final FavoriModel favoriModel = favoriModelList.get(position);

        holder.favTitle.setText(favoriModel.getTitle());
        holder.favDescription.setText(favoriModel.getDescription());
        Picasso.with(context).load(favoriModel.getLink()).into(holder.favPicture);


        holder.favResim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSecond.deleteFavori(position);

            }
        });


    }


    @Override
    public int getItemCount() {

        return favoriModelList.size();

    }

    class FavoriHolder extends RecyclerView.ViewHolder {
        ImageView favPicture;
        TextView favTitle;
        TextView favDescription;
        CardView favoriCardView;
        ImageView favResim;


        public FavoriHolder(final View itemView) {
            super(itemView);
            //Burada yukarıda tanımladıgım ve layout dosyamda olan ogelere erisiyorum
            favPicture = itemView.findViewById(R.id.favPicture);
            favTitle = itemView.findViewById(R.id.favTitle);
            favDescription = itemView.findViewById(R.id.favDescription);
            favoriCardView = itemView.findViewById(R.id.favoriCardView);
            favResim = itemView.findViewById(R.id.favResim);

        }
    }


}
