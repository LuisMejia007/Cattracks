package uc.cattracks.cattracksapp.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.sql.Time;

import uc.cattracks.cattracksapp.converters.TimeConverter;

@Entity
public class HW {

    @PrimaryKey
    private int hw_id;
    private String hw_abb;

    @TypeConverters(TimeConverter.class)
    private Time hw_run1;

    @TypeConverters(TimeConverter.class)
    private Time hw_run2;

    @TypeConverters(TimeConverter.class)
    private Time hw_run3;

    @TypeConverters(TimeConverter.class)
    private Time hw_run4;

    @TypeConverters(TimeConverter.class)
    private Time hw_run5;

    @TypeConverters(TimeConverter.class)
    private Time hw_run6;

    @TypeConverters(TimeConverter.class)
    private Time hw_run7;

    @TypeConverters(TimeConverter.class)
    private Time hw_run8;

    @TypeConverters(TimeConverter.class)
    private Time hw_run9;

    @TypeConverters(TimeConverter.class)
    private Time hw_run10;

    @TypeConverters(TimeConverter.class)
    private Time hw_run11;

    @TypeConverters(TimeConverter.class)
    private Time hw_run12;

    @TypeConverters(TimeConverter.class)
    private Time hw_run13;

    @TypeConverters(TimeConverter.class)
    private Time hw_run14;

    @TypeConverters(TimeConverter.class)
    private Time hw_run15;

    @TypeConverters(TimeConverter.class)
    private Time hw_run16;

    @TypeConverters(TimeConverter.class)
    private Time hw_run17;

    @TypeConverters(TimeConverter.class)
    private Time hw_run18;

    @TypeConverters(TimeConverter.class)
    private Time hw_run19;

    @TypeConverters(TimeConverter.class)
    private Time hw_run20;

    public int getHw_id() {
        return hw_id;
    }

    public void setHw_id(int hw_id) {
        this.hw_id = hw_id;
    }

    public String getHw_abb() {
        return hw_abb;
    }

    public void setHw_abb(String hw_abb) {
        this.hw_abb = hw_abb;
    }

    public Time getHw_run1() {
        return hw_run1;
    }

    public void setHw_run1(Time hw_run1) {
        this.hw_run1 = hw_run1;
    }

    public Time getHw_run2() {
        return hw_run2;
    }

    public void setHw_run2(Time hw_run2) {
        this.hw_run2 = hw_run2;
    }

    public Time getHw_run3() {
        return hw_run3;
    }

    public void setHw_run3(Time hw_run3) {
        this.hw_run3 = hw_run3;
    }

    public Time getHw_run4() {
        return hw_run4;
    }

    public void setHw_run4(Time hw_run4) {
        this.hw_run4 = hw_run4;
    }

    public Time getHw_run5() {
        return hw_run5;
    }

    public void setHw_run5(Time hw_run5) {
        this.hw_run5 = hw_run5;
    }

    public Time getHw_run6() {
        return hw_run6;
    }

    public void setHw_run6(Time hw_run6) {
        this.hw_run6 = hw_run6;
    }

    public Time getHw_run7() {
        return hw_run7;
    }

    public void setHw_run7(Time hw_run7) {
        this.hw_run7 = hw_run7;
    }

    public Time getHw_run8() {
        return hw_run8;
    }

    public void setHw_run8(Time hw_run8) {
        this.hw_run8 = hw_run8;
    }

    public Time getHw_run9() {
        return hw_run9;
    }

    public void setHw_run9(Time hw_run9) {
        this.hw_run9 = hw_run9;
    }

    public Time getHw_run10() {
        return hw_run10;
    }

    public void setHw_run10(Time hw_run10) {
        this.hw_run10 = hw_run10;
    }

    public Time getHw_run11() {
        return hw_run11;
    }

    public void setHw_run11(Time hw_run11) {
        this.hw_run11 = hw_run11;
    }

    public Time getHw_run12() {
        return hw_run12;
    }

    public void setHw_run12(Time hw_run12) {
        this.hw_run12 = hw_run12;
    }

    public Time getHw_run13() {
        return hw_run13;
    }

    public void setHw_run13(Time hw_run13) {
        this.hw_run13 = hw_run13;
    }

    public Time getHw_run14() {
        return hw_run14;
    }

    public void setHw_run14(Time hw_run14) {
        this.hw_run14 = hw_run14;
    }

    public Time getHw_run15() {
        return hw_run15;
    }

    public void setHw_run15(Time hw_run15) {
        this.hw_run15 = hw_run15;
    }

    public Time getHw_run16() {
        return hw_run16;
    }

    public void setHw_run16(Time hw_run16) {
        this.hw_run16 = hw_run16;
    }

    public Time getHw_run17() {
        return hw_run17;
    }

    public void setHw_run17(Time hw_run17) {
        this.hw_run17 = hw_run17;
    }

    public Time getHw_run18() {
        return hw_run18;
    }

    public void setHw_run18(Time hw_run18) {
        this.hw_run18 = hw_run18;
    }

    public Time getHw_run19() {
        return hw_run19;
    }

    public void setHw_run19(Time hw_run19) {
        this.hw_run19 = hw_run19;
    }

    public Time getHw_run20() {
        return hw_run20;
    }

    public void setHw_run20(Time hw_run20) {
        this.hw_run20 = hw_run20;
    }
}
