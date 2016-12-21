package com.example.lenovo.otp.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.lenovo.otp.R;

public class ContactsInfoActivity extends AppCompatActivity {

    TextView name,no;
    Button send;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        name = (TextView)findViewById(R.id.name);
        no=(TextView)findViewById(R.id.no);
        send=(Button)findViewById(R.id.send);
        iv=(ImageView)findViewById(R.id.contact_image) ;

        setSupportActionBar(toolbar);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound("A", R.color.colorPrimary);
        iv.setImageDrawable(drawable);

        Bundle extras = getIntent().getExtras();
        //getting name and number
        final String value1, value2;
            value1 = extras.getString("number");
            value2 = extras.getString("name");

        name.setText(value2);
        no.setText(value1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ContactsInfoActivity.this , SendMessageActivity.class);
                i.putExtra("number",value1);
                i.putExtra("name",value2);
                startActivity(i);
            }
        });
    }

}
