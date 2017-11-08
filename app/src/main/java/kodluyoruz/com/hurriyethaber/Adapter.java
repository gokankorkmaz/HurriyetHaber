package kodluyoruz.com.hurriyethaber;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kodluyoruz.com.hurriyethaber.Model.InfoViewModel;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<InfoViewModel> ınfoViewModelList;
    Context context;

    public Adapter(ArrayList<InfoViewModel> ınfoViewModelList, Context context) {
        this.ınfoViewModelList = ınfoViewModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new ViewHolder(v);

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final InfoViewModel ınfoViewModel = ınfoViewModelList.get(position);

        holder.newsTitle.setText(ınfoViewModel.getTitle());
        holder.newsDescription.setText(ınfoViewModel.getDescription());


        //Picasso ile Resim Alma
        if (ınfoViewModel.getFiles().size() > 0) {
            Picasso.with(context).load(ınfoViewModel.getFiles().get(0).getFileUrl()).into(holder.newsPicture);
        } else {
            holder.newsPicture.setImageResource(R.drawable.hurriyet);
        }


        holder.fav_resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = ınfoViewModel.getFiles().get(0).getFileUrl();
                String title = ınfoViewModel.getTitle();
                String description = ınfoViewModel.getDescription();
                String id = ınfoViewModel.getId();
                try {
                    FragmentSecond.addFavori(id, title, description, link);

                } catch (IndexOutOfBoundsException hata) {
                    Toast.makeText(context, "Beklenmeyen Hata" + hata, Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return ınfoViewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Her bir satır için gerekli ogeleri burada tanımlıyorum
        ImageView newsPicture;
        TextView newsTitle;
        TextView newsDescription;
        CardView cardView;
        ImageView fav_resim;

        public ViewHolder(final View itemView) {
            super(itemView);

            //Burada yukarıda tanımladıgım ve layout dosyamda olan ogelere erisiyorum
            newsPicture = itemView.findViewById(R.id.newsPicture);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            cardView = itemView.findViewById(R.id.cardView);
            fav_resim = itemView.findViewById(R.id.fav_resim);
        }

    }

}
