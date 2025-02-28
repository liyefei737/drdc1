package com.drdc1.medic.DataManagement;

import com.drdc1.medic.DataStructUtils.HelperMethods;

import java.util.Map;

/**
 * a soldier
 */

public class Soldier {
    private String name;
    private String id;

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.lastStatus = this.currentStatus;
        this.currentStatus = currentStatus;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }

    private String currentStatus;
    private String lastStatus;

    public String getFatigue() {
        return fatigue;
    }

    public void setFatigue(String fatigue) {
        this.fatigue = fatigue;
    }

    private String fatigue;

    private Map<String, Object> cuurentPhysioData = null;
    private Map<String, Object> lastPhysioData = null;

    public Soldier(String name, String id) {
        this.name = name;
        this.id = id;
    }


    /*********************
     * getters & setters
     **********************/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getPhysioData() {
        return cuurentPhysioData;
    }

    public void setPhysioData(Map<String, Object> physioData) {
        lastPhysioData = HelperMethods.deepCopy((Map) cuurentPhysioData);
        cuurentPhysioData = HelperMethods.deepCopy((Map) physioData);
        name = (String) physioData.get("name");
    }

    public String getBodyOrientation() {
        return (String) (cuurentPhysioData == null ? "" : cuurentPhysioData.get("bodypos"));
    }

    public String getOverallStatus() {
        return (String) (cuurentPhysioData == null ? "" : cuurentPhysioData.get("overall"));
    }

    public String getHeartRate() {
        return (String) (cuurentPhysioData == null ? "" : cuurentPhysioData.get("hr"));
    }

    public String getLastHeartRate() {
        return (String) (lastPhysioData == null ? "" : lastPhysioData.get("hr"));
    }

    public String getBreathingRate() {
        return (String) (cuurentPhysioData == null ? "" : cuurentPhysioData.get("br"));
    }

    public String getLastBreathingRate() {
        return (String) (lastPhysioData == null ? "" : lastPhysioData.get("br"));
    }

    public String getSkinTmp() {
        return (String) (cuurentPhysioData == null ? "" : cuurentPhysioData.get("skinTemp"));
    }

    public String getLastSkinTmp() {
        return (String) (lastPhysioData == null ? "" : lastPhysioData.get("skinTemp"));
    }

    public String getCoreTmp() {
        return (String) (cuurentPhysioData == null ? "" : cuurentPhysioData.get("coreTemp"));
    }

    public String getLastCoreTmp() {
        return (String) (lastPhysioData == null ? "" : lastPhysioData.get("coreTemp"));
    }

//    public int getFatigueLevel() {
//        return fatigueLevel;
//    }
}
