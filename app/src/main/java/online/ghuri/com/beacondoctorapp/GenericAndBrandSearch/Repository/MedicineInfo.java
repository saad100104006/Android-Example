
package online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineInfo {

    @SerializedName("companyName")
    @Expose
    private String company;
    @SerializedName("brandName")
    @Expose
    private String name;
    @SerializedName("genericStrengthDosageForm")
    @Expose
    private String generic;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

}
