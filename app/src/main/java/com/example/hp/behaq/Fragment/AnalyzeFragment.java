package com.example.hp.behaq.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.behaq.Adapter.BeritaAdapter;
import com.example.hp.behaq.Helper.AppConfig;
import com.example.hp.behaq.Modal.Article;
import com.example.hp.behaq.Modal.Berita;
import com.example.hp.behaq.Modal.SpaceItem;
import com.example.hp.behaq.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnalyzeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnalyzeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnalyzeFragment extends Fragment {
    RecyclerView rcyberita;
    ArrayList<Berita> beritaList = new ArrayList<>();
    Gson gson;
    RequestQueue requestQueue;
    ProgressDialog progress;
    EditText searchbar;
    ImageButton btn_search;
    BeritaAdapter beritaAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcyberita=(RecyclerView) getActivity().findViewById(R.id.recycler_view);

        progress=new ProgressDialog(getActivity());
        progress.setMessage("Please Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);

        searchbar = (EditText)getActivity().findViewById(R.id.tv_searchbar);
        btn_search = (ImageButton)getActivity().findViewById(R.id.searchbutton);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                coba();
            }
        });

//        for(int i=0;i<10;i++) {
//            beritaList.add(new Berita(1, "", "sdfsf", "www.faceboook.com", "Bismillah BismillahBismillah Bismillah Bismillah Bismillah Bismillah Bismillah", "090910092", 1, 0, 2, 3));
//        }
        rcyberita.setHasFixedSize(true);
        rcyberita.setLayoutManager(new LinearLayoutManager(getActivity()));
        beritaAdapter = new BeritaAdapter(getActivity(), beritaList);
        rcyberita.setAdapter(beritaAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_analyze, container, false);

    }

    private void coba() {

        requestQueue = Volley.newRequestQueue(getActivity());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        final String url = AppConfig.berita;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        Log.d("Response", response);

                        ResponBerita posts = new ResponBerita();
                        try {
                            posts = gson.fromJson(response,ResponBerita.class);
                        } catch (Exception e){
                            Log.d("response", e.toString());
                        }
                        Log.d("Response Post:",posts.getArticle().get(0).getName());

                        if(posts.isStatus() == true){
                            for (int i=0; i<posts.getArticle().size();i++) {
                                beritaList.add(new Berita(posts.getArticle().get(i).getId(),
                                        posts.getArticle().get(i).getQuery(),
                                        posts.getArticle().get(i).getName(),
                                        posts.getArticle().get(i).getUrl(),
                                        posts.getArticle().get(i).getSnippet(),
                                        posts.getArticle().get(i).getCount_verified(),
                                        posts.getArticle().get(i).getCount_hoax(),
                                        posts.getArticle().get(i).getSentiment(),
                                        posts.getArticle().get(i).getLabel(),
                                        posts.getArticle().get(i).getContent_id(),
                                        posts.getArticle().get(i).getSpace_id()));
                            }
                            Log.d("Response Name:",beritaList.get(0).getName());


                        }else{
                            Toast.makeText(getActivity(),"Tidak bisa", Toast.LENGTH_SHORT).show();
                            Log.d("Response :",response);
                        }
                        beritaAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("query", searchbar.getText().toString());
//                params.put("query", "bismillah");
                params.put("page", "0");
                return params;
            }
        };
        int x=2;
        postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                x, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }

    public class ResponBerita {
        private boolean status;
        private ArrayList<SpaceItem> space_items;
        private ArrayList<Article> article;
        private int offset;

        public ResponBerita(boolean status, ArrayList<SpaceItem> space_items, ArrayList<Article> article, int offset) {
            this.status = status;
            this.space_items = space_items;
            this.article = article;
            this.offset = offset;
        }

        public ResponBerita() {

        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public ArrayList<SpaceItem> getSpace_items() {
            return space_items;
        }

        public void setSpace_items(ArrayList<SpaceItem> space_items) {
            this.space_items = space_items;
        }

        public ArrayList<Article> getArticle() {
            return article;
        }

        public void setArticle(ArrayList<Article> article) {
            this.article = article;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }
    }
}
