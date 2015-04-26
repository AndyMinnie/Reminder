package andreacauchoix.reminder.BaseDeDonnee;

/**
 * Created by Franck on 25/04/2015.
 */
public class RappelData {

    private int id;
    private String rappel;
    private String date;
    private String lieu;

    public RappelData (){
    }

    public RappelData (String r, String d, String l){
        rappel = r;
        date = d;
        lieu = l;
    }


    public String getRappel() {
        return rappel;
    }


    public void setRappel(String titre) {
        this.rappel = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
       this.lieu = lieu;
    }

}
