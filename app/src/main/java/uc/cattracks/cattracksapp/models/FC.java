package uc.cattracks.cattracksapp.models;

import java.sql.Time;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import uc.cattracks.cattracksapp.converters.TimeConverter;

@Entity
public class FC {

    @PrimaryKey
    private int fc_id;
    private String fc_abb;


    private String fc_run1;


    private String fc_run2;


    private String fc_run3;


    private String fc_run4;


    private String fc_run5;


    private String fc_run6;


    private String fc_run7;


    private String fc_run8;


    private String fc_run9;


    private String fc_run10;


    private String fc_run11;


    private String fc_run12;


    private String fc_run13;


    private String fc_run14;


    private String fc_run15;



    public FC() { }

    public int getFc_id() {
        return fc_id;
    }

    public void setFc_id(int fc_id) {
        this.fc_id = fc_id;
    }

    public String getFc_abb() {
        return fc_abb;
    }

    public void setFc_abb(String fc_abb) {
        this.fc_abb = fc_abb;
    }

    public String getFc_run1() {
        return fc_run1;
    }

    public void setFc_run1(String fc_run1) {
        this.fc_run1 = fc_run1;
    }

    public String getFc_run2() {
        return fc_run2;
    }

    public void setFc_run2(String fc_run2) {
        this.fc_run2 = fc_run2;
    }

    public String getFc_run3() {
        return fc_run3;
    }

    public void setFc_run3(String fc_run3) {
        this.fc_run3 = fc_run3;
    }

    public String getFc_run4() {
        return fc_run4;
    }

    public void setFc_run4(String fc_run4) {
        this.fc_run4 = fc_run4;
    }

    public String getFc_run5() {
        return fc_run5;
    }

    public void setFc_run5(String fc_run5) {
        this.fc_run5 = fc_run5;
    }

    public String getFc_run6() {
        return fc_run6;
    }

    public void setFc_run6(String fc_run6) {
        this.fc_run6 = fc_run6;
    }

    public String getFc_run7() {
        return fc_run7;
    }

    public void setFc_run7(String fc_run7) {
        this.fc_run7 = fc_run7;
    }

    public String getFc_run8() {
        return fc_run8;
    }

    public void setFc_run8(String fc_run8) {
        this.fc_run8 = fc_run8;
    }

    public String getFc_run9() {
        return fc_run9;
    }

    public void setFc_run9(String fc_run9) {
        this.fc_run9 = fc_run9;
    }

    public String getFc_run10() {
        return fc_run10;
    }

    public void setFc_run10(String fc_run10) {
        this.fc_run10 = fc_run10;
    }

    public String getFc_run11() {
        return fc_run11;
    }

    public void setFc_run11(String fc_run11) {
        this.fc_run11 = fc_run11;
    }

    public String getFc_run12() {
        return fc_run12;
    }

    public void setFc_run12(String fc_run12) {
        this.fc_run12 = fc_run12;
    }

    public String getFc_run13() {
        return fc_run13;
    }

    public void setFc_run13(String fc_run13) {
        this.fc_run13 = fc_run13;
    }

    public String getFc_run14() {
        return fc_run14;
    }

    public void setFc_run14(String fc_run14) {
        this.fc_run14 = fc_run14;
    }

    public String getFc_run15() {
        return fc_run15;
    }

    public void setFc_run15(String fc_run15) {
        this.fc_run15 = fc_run15;
    }
}
