package uc.cattracks.cattracksapp.dao_interface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import uc.cattracks.cattracksapp.models.C1;
import uc.cattracks.cattracksapp.models.stops;

// The daoAccess class is what touches our database (Cattracks.db).
// Here we can do all our queries (within @Query annotations)
// Here is a helpful link in how to do other SQL commands using Room functionality
// Link: https://developer.android.com/training/data-storage/room/accessing-data
@Dao
public interface daoAccess {

    @Query( " SELECT * FROM C1, stops WHERE  c1_abb LIKE :c1_abb and s_abb LIKE :s_abb ")
    public List<C1> getStops(String c1_abb, String s_abb);

    @Query("SELECT * FROM C1")
    public List<C1> getC1();

    //Retrieves C1 stops
    @Query("SELECT s_name FROM C1, stops WHERE  c1_abb = s_abb")
    public List<C1> getC1StopNames();

    //Retrieves C2 stops
    @Query("SELECT s_name FROM C2, stops WHERE  c2_abb = s_abb")
    public String[] getC2StopNames();

    //Retrieves G stops
    @Query("SELECT s_name FROM G, stops WHERE  g_abb = s_abb")
    public String[] getGStopNames();

    //Retrieves H stops
    @Query("SELECT s_name FROM H, stops WHERE  h_abb = s_abb")
    public String[] getHStopNames();

    //Retrieves HW stops
    @Query("SELECT s_name FROM HW, stops WHERE  hw_abb = s_abb")
    public String[] getHWStopNames();

    //Retrieves FC stops
    @Query("SELECT s_name FROM FC, stops WHERE  fc_abb = s_abb")
    public String[] getFCStopNames();

    //Retrieves E1 stops
    @Query("SELECT s_name FROM E1, stops WHERE  e1_abb = s_abb")
    public String[] getE1StopNames();

    //Retrieves E2 stops
    @Query("SELECT s_name FROM E2, stops WHERE  e2_abb = s_abb")
    public String[] getE2StopNames();
}
