package uc.cattracks.cattracksapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Blob;
import java.sql.Time;

@Entity
public class stops {



    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "s_name")
    private String s_name;

    @ColumnInfo(name = "s_abb")
    private String s_abb;

    @ColumnInfo(name = "Comments")
    private String Comments;

    @ColumnInfo(name = "image")
    private String image;


    public String getS_abb() {
        return s_abb;
    }

    public void setS_abb(String s_abb) {
        this.s_abb = s_abb;
    }

    public String getS_name() {
        if (s_name.isEmpty()) { return ""; }
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getComments() {
        return this.Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
