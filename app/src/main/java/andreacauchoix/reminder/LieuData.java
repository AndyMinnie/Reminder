package andreacauchoix.reminder;

/**
 * Created by Franck on 25/04/2015.
 */
public class LieuData {

    int id;
    String name;
    String wifi_id;

    public LieuData (){
    }

    public String getWifi_id() {
        return wifi_id;
    }

    public void setWifi_id(String wifi_id) {
        this.wifi_id = wifi_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
