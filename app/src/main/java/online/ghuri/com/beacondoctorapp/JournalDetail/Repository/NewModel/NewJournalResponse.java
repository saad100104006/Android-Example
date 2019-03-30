
package online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewJournalResponse {

    @SerializedName("journals")
    @Expose
    private Journals journals;

    public Journals getJournals() {
        return journals;
    }

    public void setJournals(Journals journals) {
        this.journals = journals;
    }

}
