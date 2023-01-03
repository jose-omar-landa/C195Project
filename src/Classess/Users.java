package Classess;

import javafx.scene.text.Text;

import java.sql.Time;
import java.util.Date;

public class Users {

    private int user_ID;
    private String user_Name;
    private Text password;
    private Date create_Date;
    private String created_By;
    private Time last_Update;
    private String last_Updated_By;

    public Users(int user_ID, String user_Name, Text password, Date create_Date, String created_By, Time last_Update, String last_Updated_By){
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.password = password;
        this.create_Date = create_Date;
        this.created_By = created_By;
        this.last_Update = last_Update;
        this.last_Updated_By = last_Updated_By;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public Text getPassword() {
        return password;
    }

    public void setPassword(Text password) {
        this.password = password;
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
        this.created_By = created_By;
    }

    public Time getLast_Update() {
        return last_Update;
    }

    public void setLast_Update(Time last_update) {
        this.last_Update = last_update;
    }

    public String getLast_Updated_By() {
        return last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }

}
