package com.example.hp.behaq.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
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
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.behaq.Adapter.BeritaAdapter;
import com.example.hp.behaq.Helper.AppConfig;
import com.example.hp.behaq.Helper.SharedPref;
import com.example.hp.behaq.Modal.Berita;
import com.example.hp.behaq.Modal.User;
import com.example.hp.behaq.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
   Gson gson;
   ArrayList<User> listProfile = new ArrayList<>();
   TextView nama;
   RequestQueue requestQueue;
   Button logout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama = (TextView) getActivity().findViewById(R.id.tv_nama_fragmentprofile);
        logout = (Button) getActivity().findViewById(R.id.btn_logout);

        Getdata();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                
            }
        });
    }

    private void Getdata() {
        requestQueue = Volley.newRequestQueue(getActivity());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    public class ResponProfile {
        private String status;
        private ArrayList<User> data ;

        public ResponProfile(String status, ArrayList<User> data) {
            this.status = status;
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public ArrayList<User> getData() {
            return data;
        }

        public void setData(ArrayList<User> data) {
            this.data = data;
        }
    }

}
