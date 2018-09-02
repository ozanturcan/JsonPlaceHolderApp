package ozanturcan.com.myapplication.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geo {
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("lng")
    @Expose
    public String lng;
}
