package com.example.bloomingwithbirdie;

import androidx.room.TypeConverter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<Badge> stringToBadgeList(String data) {
        if (data == null || data == "null") {
            return Collections.emptyList();
        }
        //else
        List<Badge> newList = new ArrayList<Badge>();
        String[] tokens = data.split(",");
        String name = null;
        String filepath = null;
        for (int i=0;i<tokens.length;i+=2){
            tokens[i] = name;
            tokens[i+1] = filepath;
            Badge tempBadge = new Badge(name, filepath);
            newList.add(tempBadge);
        }
        return newList;
    }

    @TypeConverter
    public static String badgeListToString(List<Badge> someObjects) {
        if (someObjects == null){
            return "null";
        }

        String converted = null;
        for(Badge badge: someObjects) {
            converted += badge.getName()+","+badge.getFilePath()+",";
        }
        return converted;
    }
}
