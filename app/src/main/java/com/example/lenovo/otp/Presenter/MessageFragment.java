            package com.example.lenovo.otp.Presenter;

            import android.content.Context;
            import android.os.Bundle;
            import android.support.v4.app.Fragment;
            import android.support.v7.widget.DefaultItemAnimator;
            import android.support.v7.widget.LinearLayoutManager;
            import android.support.v7.widget.RecyclerView;
            import android.view.LayoutInflater;
            import android.view.View;
            import android.view.ViewGroup;

            import com.example.lenovo.otp.Adapter.SmsAdapter;
            import com.example.lenovo.otp.DataBaseHelper.DatabaseHandler;
            import com.example.lenovo.otp.Model.CustomSms;
            import com.example.lenovo.otp.R;

            import java.util.ArrayList;
            import java.util.List;


            public class MessageFragment extends Fragment {

                DatabaseHandler db ;
                private RecyclerView recyclerView;
                private SmsAdapter sAdapter;
                private List<CustomSms> cList ;
                RecyclerView.Adapter mAdapter;
                public MessageFragment() {
                    // Required empty public constructor
                }

                public static MessageFragment newInstance(String param1, String param2) {
                    MessageFragment fragment = new MessageFragment();
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
                    db = new DatabaseHandler(getActivity());
                    cList=new ArrayList<CustomSms>();
                    cList=db.getAllContacts();
                    View rootView= inflater.inflate(R.layout.fragment_message, container, false);
                    recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView_message);
                   mAdapter = new SmsAdapter(getActivity(),cList);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(llm);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    LayoutInflater li = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setHasFixedSize(true);
                    return rootView;
                }


            }