package uc.cattracks.cattracksapp.dao_interface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
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


    // Returns Stop Names in ASC order (From A-Z)
    @Query( "SELECT DISTINCT s_name " +
            "FROM stops " +
            "ORDER BY s_name ASC;")
    public List<stops> getStops();

    @Query("SELECT * FROM C1")
    public List<C1> getC1();


    @Query("SELECT abbrsQuery.stopNames as s_name FROM" +
            "(("+
            " SELECT s_abb AS mainStop FROM stops as s WHERE s.s_name = :userSelectedLocation" +
            " UNION" +
            " SELECT c1_abb FROM C1" +
            " UNION" +
            " SELECT c2_abb FROM C2" +
            " UNION " +
            " SELECT fc_abb FROM FC" +
            " UNION " +
            " SELECT g_abb FROM G" +
            " UNION" +
            " SELECT h_abb FROM H" +
            " UNION" +
            " SELECT hw_abb FROM HW" +
            " UNION" +
            " SELECT e1_abb FROM E1" +
            " UNION" +
            " SELECT e2_abb FROM E2" +
            ") as stopsQuery, " +
            "(SELECT s_abb as stopAbbrs, s_name as stopNames FROM stops) as abbrsQuery) " +
            "WHERE stopsQuery.mainStop = abbrsQuery.stopAbbrs AND abbrsQuery.stopNames <> :userSelectedLocation")
    public List<stops> getFilteredDestinations(String userSelectedLocation);
}
