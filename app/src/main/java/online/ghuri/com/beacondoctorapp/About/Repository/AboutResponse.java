
package online.ghuri.com.beacondoctorapp.About.Repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutResponse {

    @SerializedName("about")
    @Expose
    private About about;

    public About getAbout() {
        return about;
    }

    public void setAbout(About about) {
        this.about = about;
    }

}
