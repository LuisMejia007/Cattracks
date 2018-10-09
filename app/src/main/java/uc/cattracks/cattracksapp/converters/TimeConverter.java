package uc.cattracks.cattracksapp.converters;

import android.arch.persistence.room.TypeConverter;

import java.sql.Time;

public class TimeConverter {
    @TypeConverter
    public static Time toTime(Long timestamp) {
        return timestamp == null ? null : new Time(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Time time) {
        return time == null ? null : time.getTime();
    }
}
