package uc.cattracks.cattracksapp.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.sql.Time;

import uc.cattracks.cattracksapp.converters.TimeConverter;

@Entity
public class C2 {

    @PrimaryKey
    private int c2_id;
    private String c2_abb;

    @TypeConverters(TimeConverter.class)
    private Time c2_run1;

    @TypeConverters(TimeConverter.class)
    private Time c1_run2;

    @TypeConverters(TimeConverter.class)
    private Time c1_run3;

    @TypeConverters(TimeConverter.class)
    private Time c1_run4;

    @TypeConverters(TimeConverter.class)
    private Time c1_run5;

    @TypeConverters(TimeConverter.class)
    private Time c1_run6;

    @TypeConverters(TimeConverter.class)
    private Time c1_run7;

    @TypeConverters(TimeConverter.class)
    private Time c1_run8;

    @TypeConverters(TimeConverter.class)
    private Time c1_run9;

    @TypeConverters(TimeConverter.class)
    private Time c1_run10;

    @TypeConverters(TimeConverter.class)
    private Time c1_run11;

    @TypeConverters(TimeConverter.class)
    private Time c1_run12;

    @TypeConverters(TimeConverter.class)
    private Time c1_run13;

    @TypeConverters(TimeConverter.class)
    private Time c1_run14;

    @TypeConverters(TimeConverter.class)
    private Time c1_run15;

    @TypeConverters(TimeConverter.class)
    private Time c1_run16;

    public C2() { }

    public int getC2_id() {
        return c2_id;
    }

    public void setC2_id(int c2_id) {
        this.c2_id = c2_id;
    }

    public String getC2_abb() {
        return c2_abb;
    }

    public void setC2_abb(String c2_abb) {
        this.c2_abb = c2_abb;
    }

    public Time getC2_run1() {
        return c2_run1;
    }

    public void setC2_run1(Time c2_run1) {
        this.c2_run1 = c2_run1;
    }

    public Time getC1_run2() {
        return c1_run2;
    }

    public void setC1_run2(Time c1_run2) {
        this.c1_run2 = c1_run2;
    }

    public Time getC1_run3() {
        return c1_run3;
    }

    public void setC1_run3(Time c1_run3) {
        this.c1_run3 = c1_run3;
    }

    public Time getC1_run4() {
        return c1_run4;
    }

    public void setC1_run4(Time c1_run4) {
        this.c1_run4 = c1_run4;
    }

    public Time getC1_run5() {
        return c1_run5;
    }

    public void setC1_run5(Time c1_run5) {
        this.c1_run5 = c1_run5;
    }

    public Time getC1_run6() {
        return c1_run6;
    }

    public void setC1_run6(Time c1_run6) {
        this.c1_run6 = c1_run6;
    }

    public Time getC1_run7() {
        return c1_run7;
    }

    public void setC1_run7(Time c1_run7) {
        this.c1_run7 = c1_run7;
    }

    public Time getC1_run8() {
        return c1_run8;
    }

    public void setC1_run8(Time c1_run8) {
        this.c1_run8 = c1_run8;
    }

    public Time getC1_run9() {
        return c1_run9;
    }

    public void setC1_run9(Time c1_run9) {
        this.c1_run9 = c1_run9;
    }

    public Time getC1_run10() {
        return c1_run10;
    }

    public void setC1_run10(Time c1_run10) {
        this.c1_run10 = c1_run10;
    }

    public Time getC1_run11() {
        return c1_run11;
    }

    public void setC1_run11(Time c1_run11) {
        this.c1_run11 = c1_run11;
    }

    public Time getC1_run12() {
        return c1_run12;
    }

    public void setC1_run12(Time c1_run12) {
        this.c1_run12 = c1_run12;
    }

    public Time getC1_run13() {
        return c1_run13;
    }

    public void setC1_run13(Time c1_run13) {
        this.c1_run13 = c1_run13;
    }

    public Time getC1_run14() {
        return c1_run14;
    }

    public void setC1_run14(Time c1_run14) {
        this.c1_run14 = c1_run14;
    }

    public Time getC1_run15() {
        return c1_run15;
    }

    public void setC1_run15(Time c1_run15) {
        this.c1_run15 = c1_run15;
    }

    public Time getC1_run16() {
        return c1_run16;
    }

    public void setC1_run16(Time c1_run16) {
        this.c1_run16 = c1_run16;
    }
}
