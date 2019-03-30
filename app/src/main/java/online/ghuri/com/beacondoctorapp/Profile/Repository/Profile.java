
package online.ghuri.com.beacondoctorapp.Profile.Repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Profile extends RealmObject {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("degree")
    @Expose
    private String degree;
    @SerializedName("desig")
    @Expose
    private String desig;
    @SerializedName("dept")
    @Expose
    private String dept;
    @SerializedName("inst")
    @Expose
    private String inst;
    @SerializedName("chambera")
    @Expose
    private String chambera;
    @SerializedName("chamberb")
    @Expose
    private String chamberb;
    @SerializedName("marr")
    @Expose
    private String marr;
    @SerializedName("fb")
    @Expose
    private String fb;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

    public String getChambera() {
        return chambera;
    }

    public void setChambera(String chambera) {
        this.chambera = chambera;
    }

    public String getChamberb() {
        return chamberb;
    }

    public void setChamberb(String chamberb) {
        this.chamberb = chamberb;
    }

    public String getMarr() {
        return marr;
    }

    public void setMarr(String marr) {
        this.marr = marr;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
