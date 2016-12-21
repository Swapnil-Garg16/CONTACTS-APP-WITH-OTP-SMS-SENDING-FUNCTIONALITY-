            package com.example.lenovo.otp.Adapter;


            import android.content.Context;
            import android.support.v7.widget.RecyclerView;
            import android.util.Log;
            import android.view.LayoutInflater;
            import android.view.View;
            import android.view.ViewGroup;
            import android.widget.RelativeLayout;
            import android.widget.TextView;

            import com.example.lenovo.otp.Presenter.MessageFragment;
            import com.example.lenovo.otp.Model.CustomSms;
            import com.example.lenovo.otp.R;

            import java.util.ArrayList;
            import java.util.List;


            public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.ViewHolder> {

                private static ArrayList<CustomSms> smsList = new ArrayList<CustomSms>();
                private static LayoutInflater inflater;  // think to remove this
                static   List<CustomSms> dbList;

                Context context;

                public class ViewHolder extends RecyclerView.ViewHolder {
                    public TextView receiverName,date,otp;


                    public ViewHolder(RelativeLayout v) {
                        super(v);
                        receiverName= (TextView) v.findViewById(R.id.name_receiver);
                        date= (TextView) v.findViewById(R.id.date);
                        otp= (TextView) v.findViewById(R.id.otp_sent);
                    }
                }



                public void updateList(ArrayList<CustomSms> data) {
                    smsList = data;
                    notifyDataSetChanged();
                }

                public SmsAdapter(Context context) {
                    this.context = context;
                }

                public SmsAdapter(LayoutInflater inflater, ArrayList<CustomSms> smsList, MessageFragment messageFragment, MessageFragment fragment) {
                    this.inflater = inflater;
                    this.smsList = smsList;
                }

               public SmsAdapter(Context context, List<CustomSms> dbList ){
                    this.dbList = new ArrayList<CustomSms>();
                    this.context = context;
                    this.dbList = dbList;

                }
                @Override
                public SmsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType)
                {
                    View itemMessage = inflater.inflate(R.layout.messages_row, parent, false);
                    ViewHolder vh = new ViewHolder((RelativeLayout)itemMessage);
                    return vh;
                }


                @Override

                public void onBindViewHolder(ViewHolder holder, int position) {
                    final CustomSms r = dbList.get(position);
                    holder.receiverName.setText(r.address);
                    holder.date.setText(r.date);
                    holder.otp.setText(r.otp);

                }


                @Override
                public int getItemCount() {
                    return dbList.size();

                }
            }

