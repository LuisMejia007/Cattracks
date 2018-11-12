package uc.cattracks.cattracksapp.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;


import java.sql.Time;

import uc.cattracks.cattracksapp.converters.TimeConverter;

@Entity
public class E1 {

    @PrimaryKey
    private int e1_id;
    private String e1_abb;


    private String e1_run1;


    private String e1_run2;


    private String e1_run3;


    private String e1_run4;


    private String e1_run5;


    private String e1_run6;


    private String e1_run7;


    private String e1_run8;


    private String e1_run9;

    public E1() {}

    public int getE1_id() {
        return e1_id;
    }

    public void setE1_id(int e1_id) {
        this.e1_id = e1_id;
    }

    public String getE1_abb() {
        return e1_abb;
    }

    public void setE1_abb(String e1_abb) {
        this.e1_abb = e1_abb;
    }

    public String getE1_run1() {
        return e1_run1;
    }

    public void setE1_run1(String e1_run1) {
        this.e1_run1 = e1_run1;
    }

    public String getE1_run2() {
        return e1_run2;
    }

    public void setE1_run2(String e1_run2) {
        this.e1_run2 = e1_run2;
    }

    public String getE1_run3() {
        return e1_run3;
    }

    public void setE1_run3(String e1_run3) {
        this.e1_run3 = e1_run3;
    }

    public String getE1_run4() {
        return e1_run4;
    }

    public void setE1_run4(String e1_run4) {
        this.e1_run4 = e1_run4;
    }

    public String getE1_run5() {
        return e1_run5;
    }

    public void setE1_run5(String e1_run5) {
        this.e1_run5 = e1_run5;
    }

    public String getE1_run6() {
        return e1_run6;
    }

    public void setE1_run6(String e1_run6) {
        this.e1_run6 = e1_run6;
    }

    public String getE1_run7() {
        return e1_run7;
    }

    public void setE1_run7(String e1_run7) {
        this.e1_run7 = e1_run7;
    }

    public String getE1_run8() {
        return e1_run8;
    }

    public void setE1_run8(String e1_run8) {
        this.e1_run8 = e1_run8;
    }

    public String getE1_run9() {
        return e1_run9;
    }

    public void setE1_run9(String e1_run9) {
        this.e1_run9 = e1_run9;
    }
}
