package com.github.androidproject;

import java.util.List;

public class Hero {
    private String _id;
    private String name;
    private Integer rarity;
    private String classType;
    private String element;
    private String zodiac;
    private String memoryImprintAttribute;
    private String filedId;
    private List<String> buffs;
    private List<String> debuffs;

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public Integer getRarity() {
        return rarity;
    }

    public String getClassType() {
        return classType;
    }

    public String getElement() {
        return element;
    }

    public String getZodiac() {
        return zodiac;
    }

    public String getMemoryImprintAttribute() {
        return memoryImprintAttribute;
    }

    public String getFiledId() {
        return filedId;
    }

    public List<String> getBuffs() {
        return buffs;
    }

    public List<String> getDebuffs() {
        return debuffs;
    }
}
