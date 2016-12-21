            package com.example.lenovo.otp.Model;

            import android.os.Parcel;
            import android.os.Parcelable;



            public class CustomSms  {

                public String address;
                public String date;
                public String otp;
                private int id;

                public CustomSms(){};
                public CustomSms(int id,String address, String date, String otp) {
                    this.address = address;
                    this.date = date;
                    this.otp = otp;
                    this.id=id;
                }

                public CustomSms(String address, String date, String otp) {
                    this.address = address;
                    this.date = date;
                    this.otp = otp;

                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getOtp() {
                    return otp;
                }

                public void setOtp(String otp) {
                    this.otp = otp;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }
            }
