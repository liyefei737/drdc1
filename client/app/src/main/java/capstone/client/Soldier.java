package capstone.client;

/**
 * Created by Grace on 2017-02-04.
 */

public class Soldier {
    private String soldierID;
    private String name;
    private int age;
    private int weight;
    private int height;

    public Soldier() {
        soldierID = "";
        name = "";
        age = -1;
        weight = -1;
        height = -1;
    }

    public Soldier(String strId, String strName, int iAge, int iWeight, int iHeight) {
        soldierID = strId;
        name = strName;
        age = iAge;
        weight = iWeight;
        height = iHeight;
    }

    public String getSoldierID() {
        return soldierID;
    }

    public void setSoldierID(String newID) {
        soldierID = newID;
    }

    public String getSoldierName() {
        return name;
    }

    public void setSoldierName(String newName) {
        name = newName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        age = newAge;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int newWeight) {
        weight = newWeight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int newHeight) {
        height = newHeight;
    }
}
