package uc.cattracks.cattracksapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Bus {

    @PrimaryKey
    @ColumnInfo(name = "b_id")
    private int b_id;

    @ColumnInfo(name = "b_name")
    private String b_name;

    public Bus() { }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }
}
