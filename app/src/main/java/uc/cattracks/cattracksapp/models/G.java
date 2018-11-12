package uc.cattracks.cattracksapp.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.sql.Time;

import uc.cattracks.cattracksapp.converters.TimeConverter;

@Entity
public class G {

    @PrimaryKey
    private int g_id;
    private String g_abb;


    private String g_run1;


    private String g_run2;


    private String g_run3;


    private String g_run4;


    private String g_run5;


    private String g_run6;


    private String g_run7;


    private String g_run8;


    private String g_run9;


    private String g_run10;


    private String g_run11;


    private String g_run12;


    private String g_run13;


    private String g_run14;

    public G() {
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getG_abb() {
        return g_abb;
    }

    public void setG_abb(String g_abb) {
        this.g_abb = g_abb;
    }

    public String getG_run1() {
        return g_run1;
    }

    public void setG_run1(String g_run1) {
        this.g_run1 = g_run1;
    }

    public String getG_run2() {
        return g_run2;
    }

    public void setG_run2(String g_run2) {
        this.g_run2 = g_run2;
    }

    public String getG_run3() {
        return g_run3;
    }

    public void setG_run3(String g_run3) {
        this.g_run3 = g_run3;
    }

    public String getG_run4() {
        return g_run4;
    }

    public void setG_run4(String g_run4) {
        this.g_run4 = g_run4;
    }

    public String getG_run5() {
        return g_run5;
    }

    public void setG_run5(String g_run5) {
        this.g_run5 = g_run5;
    }

    public String getG_run6() {
        return g_run6;
    }

    public void setG_run6(String g_run6) {
        this.g_run6 = g_run6;
    }

    public String getG_run7() {
        return g_run7;
    }

    public void setG_run7(String g_run7) {
        this.g_run7 = g_run7;
    }

    public String getG_run8() {
        return g_run8;
    }

    public void setG_run8(String g_run8) {
        this.g_run8 = g_run8;
    }

    public String getG_run9() {
        return g_run9;
    }

    public void setG_run9(String g_run9) {
        this.g_run9 = g_run9;
    }

    public String getG_run10() {
        return g_run10;
    }

    public void setG_run10(String g_run10) {
        this.g_run10 = g_run10;
    }

    public String getG_run11() {
        return g_run11;
    }

    public void setG_run11(String g_run11) {
        this.g_run11 = g_run11;
    }

    public String getG_run12() {
        return g_run12;
    }

    public void setG_run12(String g_run12) {
        this.g_run12 = g_run12;
    }

    public String getG_run13() {
        return g_run13;
    }

    public void setG_run13(String g_run13) {
        this.g_run13 = g_run13;
    }

    public String getG_run14() {
        return g_run14;
    }

    public void setG_run14(String g_run14) {
        this.g_run14 = g_run14;
    }
}
