package uc.cattracks.cattracksapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import uc.cattracks.cattracksapp.dao_interface.daoAccess;
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

// The Cattracks Database class serves as an interface for the RoomDatabase.
// Room is Android's way of organizing our DB entities and making our jobs easier when querying from the database.
// Check HomeActivity to see how we connect the Cattracks.db (under assets/databases) to this class.
// The @Database annotation allows the CattracksDatabase to correctly read the info from Cattracks.db and passes command too
// ... the daoAccess class.
@Database(entities = {Bus.class, C1.class, C2.class, E1.class, E2.class, FC.class, G.class, H.class, HW.class, stops.class},
version = 1, exportSchema = true)
public abstract class CattracksDatabase extends RoomDatabase {
    public abstract daoAccess daoAccess();


}
