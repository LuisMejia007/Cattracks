package uc.cattracks.cattracksapp.dao_interface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Query;

import java.util.List;

import uc.cattracks.cattracksapp.models.Bus;
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
            "WHERE stopsQuery.mainStop = abbrsQuery.stopAbbrs AND abbrsQuery.stopNames <> :userSelectedLocation" +
            " ORDER BY s_name ASC;")
    public List<stops> getFilteredDestinations(String userSelectedLocation);


    // Get stop abbreviations using stop names

    @Query("SELECT s_abb FROM stops WHERE s_name =:userSelectedLocation OR s_name =:userSelectedDestination;")
    public List<String> getStopAbbsFromNames(String userSelectedLocation, String userSelectedDestination);



    // Get Bus IDs based on stop abbreviations
    @Query("SELECT *" +
            "FROM (" +
            "        SELECT c11.c1_id as c1_ID" +
            "        FROM C1 as c11, C1 as c12" +
            "        WHERE c11.c1_abb LIKE :userLocationAbb and c12.c1_abb LIKE :userDestinationAbb" +
            "        UNION" +
            "        SELECT c21.c2_id as c2_ID" +
            "        FROM C2 as c21, C2 as c22" +
            "        WHERE c21.c2_abb LIKE :userLocationAbb and c22.c2_abb LIKE :userDestinationAbb" +
            "        UNION" +
            "        SELECT fc1.fc_id as fc_ID" +
            "        FROM FC as fc1, FC as fc2" +
            "        WHERE fc1.fc_abb LIKE :userLocationAbb and fc2.fc_abb LIKE :userDestinationAbb" +
            "        UNION" +
            "        SELECT g1.g_id As g_ID" +
            "        FROM G as g1, G as g2" +
            "        WHERE g1.g_abb LIKE :userLocationAbb and g2.g_abb LIKE :userDestinationAbb" +
            "        UNION" +
            "        SELECT h1.h_id as h_ID" +
            "        FROM H as h1, H as h2" +
            "        WHERE h1.h_abb LIKE :userLocationAbb and h2.h_abb LIKE :userDestinationAbb" +
            "        UNION" +
            "        SELECT hw1.hw_id as hw_ID" +
            "        FROM HW as hw1, HW as hw2" +
            "        WHERE hw1.hw_abb LIKE :userLocationAbb and hw2.hw_abb LIKE :userDestinationAbb" +
            "        UNION" +
            "        SELECT e11.e1_id as e1_ID" +
            "        FROM E1 as e11, E1 as e12" +
            "        WHERE e11.e1_abb LIKE :userLocationAbb and e12.e1_abb LIKE :userDestinationAbb" +
            "        UNION" +
            "        SELECT e21.e2_id as e2_ID" +
            "        FROM E2 as e21, E2 as e22" +
            "        WHERE e21.e2_abb LIKE :userLocationAbb and e22.e2_abb LIKE :userDestinationAbb" +
            ");")
    public List<Integer> getBusIDsFromStopAbbs(String userLocationAbb, String userDestinationAbb);


    // Get Bus Name From Bus ID
    @Query("SELECT b_name FROM Bus WHERE b_id =:busID;")
   public String getBusNameFromBusID(int busID);
}
