                        package com.example.lenovo.otp.Presenter;

                        import android.os.Bundle;
                        import android.support.v7.app.AppCompatActivity;
                        import android.support.v7.widget.Toolbar;
                        import android.telephony.SmsManager;
                        import android.util.Log;
                        import android.view.View;
                        import android.widget.Button;
                        import android.widget.TextView;
                        import android.widget.Toast;

                        import com.example.lenovo.otp.DataBaseHelper.DatabaseHandler;
                        import com.example.lenovo.otp.Model.CustomSms;
                        import com.example.lenovo.otp.R;

                        import java.text.DateFormat;
                        import java.util.ArrayList;
                        import java.util.Date;
                        import java.util.Random;

                        public class SendMessageActivity extends AppCompatActivity {

                            Button send;
                            TextView OTP;
                            ArrayList<CustomSms> smsList ;
                            public static String number, message;
                            public static int otpNumber;
                            private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
                            DatabaseHandler db = new DatabaseHandler(this);

                            @Override
                            protected void onCreate(Bundle savedInstanceState) {
                                super.onCreate(savedInstanceState);
                                setContentView(R.layout.activity_send_message);
                                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                                setSupportActionBar(toolbar);
                                smsList = new ArrayList<>();
                                send = (Button) findViewById(R.id.final_send);
                                OTP = (TextView) findViewById(R.id.otp);

                                //Random OTP generation
                                Random rand = new Random();
                                otpNumber = 100000 + rand.nextInt(900000);

                                final String otpnum = String.valueOf(otpNumber);
                                OTP.setText(otpnum);

                                Bundle extras = getIntent().getExtras();
                                //getting name and number
                                final String number,name;
                                number = extras.getString("number");
                                name=extras.getString("name");

                                Log.d("number1" , number);
                                message = "Hi. Your OTP is " + otpnum;


                                send.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Toast.makeText(SendMessageActivity.this ,number  , Toast.LENGTH_SHORT).show();
                                        Log.d("num" , number);
                                        Log.d("otp", String.valueOf(otpNumber));

                                        try {
                                            SmsManager smsManager = SmsManager.getDefault();

                                            smsManager.sendTextMessage(number, null, message, null, null);

                                            Toast.makeText(getApplicationContext(), "SMS Sent!",
                                                    Toast.LENGTH_LONG).show();

                                            CustomSms sms = new CustomSms();
                                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        sms.setAddress(number);
                                            db.addContact(new CustomSms(name,currentDateTimeString,otpnum));
                                            Toast.makeText(SendMessageActivity.this , name+" "+ currentDateTimeString + " "+otpnum + " ", Toast.LENGTH_SHORT).show();

                                        } catch (Exception e) {
                                            Toast.makeText(getApplicationContext(),
                                                    "SMS failed, please try again later!",
                                                    Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }




                        }


