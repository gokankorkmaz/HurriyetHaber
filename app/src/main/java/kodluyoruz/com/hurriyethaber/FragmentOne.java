package kodluyoruz.com.hurriyethaber;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kodluyoruz.com.hurriyethaber.Model.InfoViewModel;
import kodluyoruz.com.hurriyethaber.Volley.AppController;

public class FragmentOne extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recylerView;
    SwipeRefreshLayout refreshLayout;
    Adapter adapter;
    List<InfoViewModel> liste = new ArrayList<>();
    ArrayList<InfoViewModel> ınfoViewModelList = new ArrayList<>();
    private String TAG = "JsonRequest";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        recylerView = view.findViewById(R.id.recylerView);

        adapter = new Adapter(ınfoViewModelList, getContext());

        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recylerView.setLayoutManager(linearLayoutManager);
        recylerView.setAdapter(adapter);


        jsonRequest();

        return view;
    }

    public void jsonRequest() {
        String jsonRequest = "JsonRequest";

        final String url = "https://api.hurriyet.com.tr/v1/articles?apikey=fc07f48ba2f14e7a9431c53c8c0372cd&$top=20";


        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
        progressDialog.setMessage("Haberler Yükleniyor");

        StringRequest request = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //Olumlu Cevapp
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                liste = Arrays.asList(gson.fromJson(response, InfoViewModel[].class));

                ArrayList<InfoViewModel> listem = new ArrayList<>(liste);

                Adapter adapter = new Adapter(listem, getContext());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

                recylerView.setLayoutManager(linearLayoutManager);

                recylerView.setAdapter(adapter);

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Olumsuz Cevap

                Toast.makeText(getContext(), TAG + error.getMessage() + " ", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }) {

            //encoding ayarlamak icin yazilmali.
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {

                String charset = "UTF-8";
                String parsed;
                try {
                    if (charset != null) {
                        parsed = new String(response.data, charset);
                    } else {
                        parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    }
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        // requesti kuyruga ekler.
        AppController.getInstance().addToRequestQueue(request, jsonRequest);


    }

    @Override
    public void onRefresh() {
        jsonRequest();
        Toast.makeText(getContext(), "Haberler Güncellendi ", Toast.LENGTH_SHORT).show();
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }
}
