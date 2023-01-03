package Classess;

import java.sql.Time;
import java.util.Date;

public class FirstLevelDivisions {

    private int division_ID;
    private String division;
    private Date create_Date;
    private String created_By;
    private Time last_Update;
    private String last_Updated_By;
    private int country_ID;


    public FirstLevelDivisions(int division_ID, String division, Date create_Date, String created_By, Time last_Update, String last_Updated_By, int country_ID){
        this.division_ID = division_ID;
        this.division = division;
        this.create_Date = create_Date;
        this.created_By = created_By;
        this.last_Update = last_Update;
        this.last_Updated_By = last_Updated_By;
        this.country_ID = country_ID;
    }

    public int getDivision_ID() {
        return division_ID;
    }

    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Date getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(Date create_Date) {
        this.create_Date = create_Date;
    }

    public String getCreated_By() {
        return created_By;
    }

    public void setCreated_By(String created_By) {
        this. created_By = created_By;
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
        this. last_Updated_By = last_Updated_By;
    }

    public int getCountry_ID() {
        return country_ID;
    }
     public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
     }


}
