package kodluyoruz.com.hurriyethaber;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kodluyoruz.com.hurriyethaber.Model.FavoriModel;


public class FragmentSecond extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static RecyclerView frecylerView;
    public static FavoriAdapter favoriAdapter;
    public static ArrayList<FavoriModel> favoriModelList = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;

    public static void deleteFavori(int position) {

        try {
            favoriModelList.remove(position);
            favoriAdapter.notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException hata) {
            Log.e("hataIndexOut", hata.toString());
        }


    }

    public static void addFavori(String id, String title, String description, String link) {
        FavoriModel favoriModel = new FavoriModel(id, title, description, link);
        favoriModelList.add(favoriModel);
        favoriAdapter.notifyDataSetChanged();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        frecylerView = view.findViewById(R.id.frecylerView);
        favoriAdapter = new FavoriAdapter(favoriModelList, getActivity());

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        frecylerView.setLayoutManager(linearLayoutManager);
        frecylerView.setAdapter(favoriAdapter);


        return view;
    }

    @Override
    public void onRefresh() {

        favoriAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }


}
