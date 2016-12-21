package com.example.lenovo.otp.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.otp.Adapter.ContactsAdapter;
import com.example.lenovo.otp.Model.Contacts;
import com.example.lenovo.otp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactsFragment extends Fragment {


    private List<Contacts> cList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactsAdapter cAdapter;

    public String number;
    public ContactsFragment() {
        // Required empty public constructor
    }


    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_contacts, container, false);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView);

        cAdapter=new ContactsAdapter(cList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);
        recyclerView.setHasFixedSize(true);

        loadJSONFromAsset();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("contacts");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                Contacts contacts = new Contacts();
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("name"));
                String name_value = jo_inside.getString("name");
                number=jo_inside.getString("no");

               contacts.setName(name_value);
               contacts.setNumber(number);
                cList.add(contacts);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        View transitionView = view.findViewById(R.id.row_image);
                        ViewCompat.setTransitionName(transitionView,"MYTRANSITIONVIEW" );

                        Intent i = new Intent(getContext(),ContactsInfoActivity.class);
                        Contacts c =cList.get(position);
                        i.putExtra("name",c.getName());
                        i.putExtra("number",c.getNumber());
                        Bundle bundle = null;
/*
                        ActivityOptions options = ActivityOptions
                                .makeSceneTransitionAnimation(getActivity(), transitionView, "MYTRANSITIONVIEW");*/
                        // start the new activity
                        startActivity(i/* options.toBundle()*/);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        return rootView;


    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("sample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }





}
