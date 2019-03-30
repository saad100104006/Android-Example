package online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository;

import java.util.List;

/**
 * Created by Tanvir on 2/12/2018.
 */

public interface SearchDBCallBack {

    void getMedicineInfo(List<MedicineInfo> medicineInfoList,String query);
    void getErrorMsg(String msg);
}
