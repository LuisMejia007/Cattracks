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

/*
    @Query("SELECT abbrsQuery.stopNames, abbrsQuery.stopAbbrs\n" +
            "    FROM \n" +
            "    (      (\n" +
            "            SELECT s_abb AS mainStop FROM stops as s WHERE s.s_name = 'Granville Apartments'\n" +
            "            UNION\n" +
            "            SELECT c1_abb FROM C1\n" +
            "            UNION \n" +
            "            SELECT c2_abb FROM C2\n" +
            "            UNION\n" +
            "            SELECT fc_abb FROM FC\n" +
            "            UNION\n" +
            "            SELECT g_abb FROM G\n" +
            "            UNION\n" +
            "            SELECT h_abb FROM H\n" +
            "            UNION\n" +
            "            SELECT hw_abb FROM HW\n" +
            "            ) as stopsQuery,\n" +
            "            \n" +
            "            (SELECT s_abb as stopAbbrs, s_name as stopNames \n" +
            "             FROM stops) as abbrsQuery\n" +
            ")\n" +
            "    WHERE stopsQuery.mainStop = abbrsQuery.stopAbbrs AND abbrsQuery.stopNames <> 'Granville Apartments';\n" +
            "    \n" +
            "\n" +
            "\n")
    public List<stops> getFilteredDestinations();*/
}
