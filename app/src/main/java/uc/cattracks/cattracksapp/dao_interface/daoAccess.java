package uc.cattracks.cattracksapp.dao_interface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import uc.cattracks.cattracksapp.models.C1;
import uc.cattracks.cattracksapp.models.stops;

@Dao
public interface daoAccess {

    @Query( " SELECT * FROM C1, stops WHERE  (c1_abb = :c1_abb = 'GRA') and s_abb= :s_abb LIKE 'SAAC' ")
    public List<C1> getStops(String c1_abb, String s_abb);

    @Query("SELECT * FROM C1")
    public List<C1> getC1();
}
