
package online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineInfoRoot {

    @SerializedName("MedicineInfo")
    @Expose
    private List<MedicineInfo> medicineInfo = null;

    public List<MedicineInfo> getMedicineInfo() {
        return medicineInfo;
    }

    public void setMedicineInfo(List<MedicineInfo> medicineInfo) {
        this.medicineInfo = medicineInfo;
    }

}
