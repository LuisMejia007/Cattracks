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
            "WHERE s_name <>'Student Activites & Athletics Center' AND s_name <> 'Emigrant Pass at Scholar Lane' " +
            "ORDER BY s_name ASC;")
    public List<stops> getStops();

    @Query("SELECT * FROM C1")
    public List<C1> getC1();
}
