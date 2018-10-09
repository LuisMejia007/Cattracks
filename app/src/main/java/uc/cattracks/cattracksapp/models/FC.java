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

    @TypeConverters(TimeConverter.class)
    private Time fc_run1;

    @TypeConverters(TimeConverter.class)
    private Time fc_run2;

    @TypeConverters(TimeConverter.class)
    private Time fc_run3;

    @TypeConverters(TimeConverter.class)
    private Time fc_run4;

    @TypeConverters(TimeConverter.class)
    private Time fc_run5;

    @TypeConverters(TimeConverter.class)
    private Time fc_run6;

    @TypeConverters(TimeConverter.class)
    private Time fc_run7;

    @TypeConverters(TimeConverter.class)
    private Time fc_run8;

    @TypeConverters(TimeConverter.class)
    private Time fc_run9;

    @TypeConverters(TimeConverter.class)
    private Time fc_run10;

    @TypeConverters(TimeConverter.class)
    private Time fc_run11;

    @TypeConverters(TimeConverter.class)
    private Time fc_run12;

    @TypeConverters(TimeConverter.class)
    private Time fc_run13;

    @TypeConverters(TimeConverter.class)
    private Time fc_run14;

    @TypeConverters(TimeConverter.class)
    private Time fc_run15;



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

    public Time getFc_run1() {
        return fc_run1;
    }

    public void setFc_run1(Time fc_run1) {
        this.fc_run1 = fc_run1;
    }

    public Time getFc_run2() {
        return fc_run2;
    }

    public void setFc_run2(Time fc_run2) {
        this.fc_run2 = fc_run2;
    }

    public Time getFc_run3() {
        return fc_run3;
    }

    public void setFc_run3(Time fc_run3) {
        this.fc_run3 = fc_run3;
    }

    public Time getFc_run4() {
        return fc_run4;
    }

    public void setFc_run4(Time fc_run4) {
        this.fc_run4 = fc_run4;
    }

    public Time getFc_run5() {
        return fc_run5;
    }

    public void setFc_run5(Time fc_run5) {
        this.fc_run5 = fc_run5;
    }

    public Time getFc_run6() {
        return fc_run6;
    }

    public void setFc_run6(Time fc_run6) {
        this.fc_run6 = fc_run6;
    }

    public Time getFc_run7() {
        return fc_run7;
    }

    public void setFc_run7(Time fc_run7) {
        this.fc_run7 = fc_run7;
    }

    public Time getFc_run8() {
        return fc_run8;
    }

    public void setFc_run8(Time fc_run8) {
        this.fc_run8 = fc_run8;
    }

    public Time getFc_run9() {
        return fc_run9;
    }

    public void setFc_run9(Time fc_run9) {
        this.fc_run9 = fc_run9;
    }

    public Time getFc_run10() {
        return fc_run10;
    }

    public void setFc_run10(Time fc_run10) {
        this.fc_run10 = fc_run10;
    }

    public Time getFc_run11() {
        return fc_run11;
    }

    public void setFc_run11(Time fc_run11) {
        this.fc_run11 = fc_run11;
    }

    public Time getFc_run12() {
        return fc_run12;
    }

    public void setFc_run12(Time fc_run12) {
        this.fc_run12 = fc_run12;
    }

    public Time getFc_run13() {
        return fc_run13;
    }

    public void setFc_run13(Time fc_run13) {
        this.fc_run13 = fc_run13;
    }

    public Time getFc_run14() {
        return fc_run14;
    }

    public void setFc_run14(Time fc_run14) {
        this.fc_run14 = fc_run14;
    }

    public Time getFc_run15() {
        return fc_run15;
    }

    public void setFc_run15(Time fc_run15) {
        this.fc_run15 = fc_run15;
    }
}
