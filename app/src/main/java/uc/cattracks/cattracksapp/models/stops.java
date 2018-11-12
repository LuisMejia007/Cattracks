package uc.cattracks.cattracksapp.models;

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
    private String s_name;
    @Ignore
    private String s_abb;
    @Ignore
    private String Comments;


    public String getS_abb() {
        return s_abb;
    }

    public void setS_abb(String s_abb) {
        this.s_abb = s_abb;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

}
