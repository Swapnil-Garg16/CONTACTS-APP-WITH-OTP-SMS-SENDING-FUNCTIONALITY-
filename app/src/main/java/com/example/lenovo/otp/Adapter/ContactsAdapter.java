package com.example.lenovo.otp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.lenovo.otp.Model.Contacts;
import com.example.lenovo.otp.R;

import java.util.List;

/**
 * Created by lenovo on 20-12-2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    public List<Contacts> contactsList ;
    public ImageView imageView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name ;


        public MyViewHolder(View view)
        {
            super(view);
            name = (TextView)view.findViewById(R.id.contact_name);
            imageView=(ImageView)view.findViewById(R.id.row_image);
        }
    }

    public ContactsAdapter (List<Contacts> list)
    {
        this.contactsList=list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_row, parent, false);


        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TextDrawable drawable = TextDrawable.builder()
                .buildRound("A", R.color.colorPrimary);


        imageView.setImageDrawable(drawable);

        Contacts contacts= contactsList.get(position);
        holder.name.setText(contacts.getName());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
