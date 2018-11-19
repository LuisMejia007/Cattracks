package uc.cattracks.cattracksapp.dao_interface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Query;
import android.text.format.Time;

import java.util.ArrayList;
import java.util.List;

import uc.cattracks.cattracksapp.models.Bus;
import uc.cattracks.cattracksapp.models.C1;
import uc.cattracks.cattracksapp.models.C2;
import uc.cattracks.cattracksapp.models.E1;
import uc.cattracks.cattracksapp.models.E2;
import uc.cattracks.cattracksapp.models.FC;
import uc.cattracks.cattracksapp.models.G;
import uc.cattracks.cattracksapp.models.H;
import uc.cattracks.cattracksapp.models.HW;
import uc.cattracks.cattracksapp.models.stops;

// The daoAccess class is what touches our database (Cattracks.db).
// Here we can do all our queries (within @Query annotations)
// Here is a helpful link in how to do other SQL commands using Room functionality
// Link: https://developer.android.com/training/data-storage/room/accessing-data
@Dao
public interface daoAccess {


    // Returns Stop Names in ASC order (From A-Z)
    @Query( "SELECT DISTINCT s_name, Comments " +
            "FROM stops " +
            "ORDER BY s_name ASC;")
    public List<stops> getStops();

    @Query("SELECT * FROM C1")
    public List<C1> getC1();

    //Retrieves C1 stops
    @Query("SELECT DISTINCT s_name, Comments FROM C1, stops WHERE c1_abb = s_abb;")
    public List<stops> getC1StopNames();

    //Retrieves C2 stops
    @Query("SELECT DISTINCT s_name, Comments FROM C2, stops WHERE c2_abb = s_abb;")
    public List<stops> getC2StopNames();

    //Retrieves G stops
    @Query("SELECT DISTINCT s_name, Comments FROM G, stops WHERE g_abb = s_abb;")
    public List<stops> getGStopNames();

    //Retrieves H stops
    @Query("SELECT DISTINCT s_name, Comments FROM H, stops WHERE  h_abb = s_abb;")
    public List<stops> getHStopNames();

    //Retrieves HW stops
    @Query("SELECT DISTINCT s_name, Comments FROM HW, stops WHERE  hw_abb = s_abb;")
    public List<stops> getHWStopNames();

    //Retrieves FC stops
    @Query("SELECT DISTINCT s_name, Comments FROM FC, stops WHERE  fc_abb = s_abb;")
    public List<stops> getFCStopNames();

    //Retrieves E1 stops
    @Query("SELECT DISTINCT s_name, Comments FROM E1, stops WHERE  e1_abb = s_abb;")
    public List<stops> getE1StopNames();

    //Retrieves E2 stops
    @Query("SELECT DISTINCT s_name, Comments FROM E2, stops WHERE  e2_abb = s_abb;")
    public List<stops> getE2StopNames();

    @Query("SELECT abbrsQuery.stopNames as s_name, abbrsQuery.comments as Comments FROM" +
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
            "(SELECT s_abb as stopAbbrs, Comments as comments, s_name as stopNames FROM stops) as abbrsQuery) " +
            "WHERE stopsQuery.mainStop = abbrsQuery.stopAbbrs AND abbrsQuery.stopNames <> :userSelectedLocation" +
            " ORDER BY s_name ASC;")
    public List<stops> getFilteredDestinations(String userSelectedLocation);


    // Get stop abbreviation using stop name
    @Query("SELECT s_abb FROM stops WHERE s_name=:stopName;")
    public String getStopAbbFromName(String stopName);

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




    // Fast Cat Stop Queries
    @Query("SELECT * " +
            "FROM FC " +
            "WHERE (fc_abb LIKE :userDestinationAbb);")
    public List<FC> getFCTimesToDestination(String userDestinationAbb);

    @Query("SELECT * " +
            "FROM FC " +
            "WHERE (fc_abb LIKE :userLocationAbb);")
    public List<FC> getFCTimesFromLocation(String userLocationAbb);


    // C1 Stop Queries
    @Query("SELECT * " +
            "FROM C1 " +
            "WHERE (c1_abb LIKE :userDestinationAbb);")
    public List<C1> getC1TimesToDestination(String userDestinationAbb);

    @Query("SELECT * " +
            "FROM C1 " +
            "WHERE (c1_abb LIKE :userLocationAbb);")
    public List<C1> getC1TimesFromLocation(String userLocationAbb);



    // C2 Stop Queries
    @Query("SELECT * " +
            "FROM C2 " +
            "WHERE (c2_abb LIKE :userDestinationAbb);")
    public List<C2> getC2TimesToDestination(String userDestinationAbb);

    @Query("SELECT * " +
            "FROM C2 " +
            "WHERE (c2_abb LIKE :userLocationAbb);")
    public List<C2> getC2TimesFromLocation(String userLocationAbb);


    // E1 Stop Queries
    @Query("SELECT * " +
            "FROM E1 " +
            "WHERE (e1_abb LIKE :userDestinationAbb);")
    public List<E1> getE1TimesToDestination(String userDestinationAbb);

    @Query("SELECT * " +
            "FROM E1 " +
            "WHERE (e1_abb LIKE :userLocationAbb);")
    public List<E1> getE1TimesFromLocation(String userLocationAbb);

    // E2 Stop Queries
    @Query("SELECT * " +
            "FROM E2 " +
            "WHERE (e2_abb LIKE :userDestinationAbb);")
    public List<E2> getE2TimesToDestination(String userDestinationAbb);

    @Query("SELECT * " +
            "FROM E2 " +
            "WHERE (e2_abb LIKE :userLocationAbb);")
    public List<E2> getE2TimesFromLocation(String userLocationAbb);


    // G Stop Queries
    @Query("SELECT * " +
            "FROM G " +
            "WHERE (g_abb LIKE :userDestinationAbb);")
    public List<G> getGTimesToDestination(String userDestinationAbb);

    @Query("SELECT * " +
            "FROM G " +
            "WHERE (g_abb LIKE :userLocationAbb);")
    public List<G> getGTimesFromLocation(String userLocationAbb);


    // H Stop Queries
    @Query("SELECT * " +
            "FROM H " +
            "WHERE (h_abb LIKE :userDestinationAbb);")
    public List<H> getHTimesToDestination(String userDestinationAbb);

    @Query("SELECT * " +
            "FROM H " +
            "WHERE (h_abb LIKE :userLocationAbb);")
    public List<H> getHTimesFromLocation(String userLocationAbb);

    // HW Stop Queries
    @Query("SELECT * " +
            "FROM HW " +
            "WHERE (hw_abb LIKE :userDestinationAbb);")
    public List<HW> getHWTimesToDestination(String userDestinationAbb);

    @Query("SELECT * " +
            "FROM HW " +
            "WHERE (hw_abb LIKE :userLocationAbb);")
    public List<HW> getHWTimesFromLocation(String userLocationAbb);
}
