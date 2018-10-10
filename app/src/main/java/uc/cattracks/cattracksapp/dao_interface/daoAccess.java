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
}
