package com.example.bloomingwithbirdie;

import androidx.room.TypeConverter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {
    Gson gson = new Gson();

    @TypeConverter
    public static List<Badge> stringToBadgeList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type Badge = new TypeToken<List<Badge>>() {}.getType();

        return gson.fromJson(data, Badge);
    }

    @TypeConverter
    public static String badgeListToString(List<Badge> someObjects) {
        String converted = null;

        for(Badge badge: someObjects) {
            badge.getName();
        }

        return gson.toJson(Badge);
    }
}
