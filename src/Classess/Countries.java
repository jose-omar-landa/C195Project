package Classess;

import java.sql.Date;
import java.sql.Time;
import java.time.DateTimeException;

public class Countries {

    private int country_ID;
    private String country;
    private Date create_Date;
    private String created_By;
    private Time last_Update;
    private String last_Updated_By;


    public Countries(int country_ID, String country, Date create_Date, String created_By, Time last_Update, String last_Updated_By) {
        this.country_ID = country_ID;
        this.country = country;
        this.create_Date = create_Date;
        this.created_By = created_By;
        this.last_Update = last_Update;
        this.last_Updated_By = last_Updated_By;
    }



    public int getCountry_ID() {
        return country_ID;
    }

    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(Date create_Date) {
        this. create_Date = create_Date;
    }

    public String getCreated_By(){
        return created_By;
    }

    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }

    public Time getLast_Update() {
        return last_Update;
    }

    public void setLast_Update(Time last_Update) {
        this.last_Update = last_Update;
    }

    public String getLast_Updated_By() {
        return last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }


}
