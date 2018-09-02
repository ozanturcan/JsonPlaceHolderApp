package ozanturcan.com.myapplication.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("username")
        @Expose
        public String username;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("address")
        @Expose
        public Address address;
        @SerializedName("phone")
        @Expose
        public String phone;
        @SerializedName("website")
        @Expose
        public String website;
        @SerializedName("company")
        @Expose
        public Company company;
    }
