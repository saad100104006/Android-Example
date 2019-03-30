package online.ghuri.com.beacondoctorapp.Login.ViewModel;

/**
 * Created by Tanvir on 2/28/2018.
 */

public interface SignUpViewModel {
    String getMrSpinnnerTxt();
    String getNameText();
    String getMobileText();
    String getPassText();
    String getEmailText();
    String getMonthSpinnerText();
    String getdaySpinnnerText();
    String getyearSpinnerText();
    String getBmdcRegNoText();

    void showToast(String msg);

    void closeDialog();
}
