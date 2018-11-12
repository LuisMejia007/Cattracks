package uc.cattracks.cattracksapp.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.sql.Date;
import java.sql.Time;

import uc.cattracks.cattracksapp.converters.TimeConverter;

@Entity
public class E2 {

    @PrimaryKey
    private int e2_id;
    private String e2_abb;


    private String e2_run1;


    private String e2_run2;


    private String e2_run3;


    private String e2_run4;


    private String e2_run5;


    private String e2_run6;


    private String e2_run7;


    private String e2_run8;


    private String e2_run9;


    private String e2_run10;



    public E2() { }

    public int getE2_id() {
        return e2_id;
    }

    public void setE2_id(int e2_id) {
        this.e2_id = e2_id;
    }

    public String getE2_abb() {
        return e2_abb;
    }

    public void setE2_abb(String e2_abb) {
        this.e2_abb = e2_abb;
    }

    public String getE2_run1() {
        return e2_run1;
    }

    public void setE2_run1(String e2_run1) {
        this.e2_run1 = e2_run1;
    }

    public String getE2_run2() {
        return e2_run2;
    }

    public void setE2_run2(String e2_run2) {
        this.e2_run2 = e2_run2;
    }

    public String getE2_run3() {
        return e2_run3;
    }

    public void setE2_run3(String e2_run3) {
        this.e2_run3 = e2_run3;
    }

    public String getE2_run4() {
        return e2_run4;
    }

    public void setE2_run4(String e2_run4) {
        this.e2_run4 = e2_run4;
    }

    public String getE2_run5() {
        return e2_run5;
    }

    public void setE2_run5(String e2_run5) {
        this.e2_run5 = e2_run5;
    }

    public String getE2_run6() {
        return e2_run6;
    }

    public void setE2_run6(String e2_run6) {
        this.e2_run6 = e2_run6;
    }

    public String getE2_run7() {
        return e2_run7;
    }

    public void setE2_run7(String e2_run7) {
        this.e2_run7 = e2_run7;
    }

    public String getE2_run8() {
        return e2_run8;
    }

    public void setE2_run8(String e2_run8) {
        this.e2_run8 = e2_run8;
    }

    public String getE2_run9() {
        return e2_run9;
    }

    public void setE2_run9(String e2_run9) {
        this.e2_run9 = e2_run9;
    }

    public String getE2_run10() {
        return e2_run10;
    }

    public void setE2_run10(String e2_run10) {
        this.e2_run10 = e2_run10;
    }
}
