package andreacauchoix.reminder.Wifi;

/**
 * Created by isen on 26/04/2015.
 */
public class WifiItem {
    private String APName;
    private String AdresseMac;
    private int ForceSignal;

    public String getAPName() {
        return APName;
    }
    public void setAPName(String aPName) {
        APName = aPName;
    }
    public String getAdresseMac() {
        return AdresseMac;
    }
    public void setAdresseMac(String adresseMac) {
        AdresseMac = adresseMac;
    }
    public int getForceSignal() {
        return ForceSignal;
    }
    public void setForceSignal(int forceSignal) {
        ForceSignal = forceSignal;
    }

}
