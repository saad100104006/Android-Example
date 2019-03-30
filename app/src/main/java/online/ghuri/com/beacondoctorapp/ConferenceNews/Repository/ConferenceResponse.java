
package online.ghuri.com.beacondoctorapp.ConferenceNews.Repository;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConferenceResponse {

    @SerializedName("conferences")
    @Expose
    private List<Conference> conferences = null;

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }

}
