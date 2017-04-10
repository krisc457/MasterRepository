package hello;

/**
 * Created by Administrator on 2017-03-29.
 */
public class Player {

    private String userName;
    private String majorNation;
    private String[] countriesOwned;
    private int treasury;
    private boolean fortification;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMajorNation() {
        return majorNation;
    }

    public void setMajorNation(String majorNation) {
        this.majorNation = majorNation;
    }

    public String[] getCountriesOwned() {
        return countriesOwned;
    }

    public void setCountriesOwned(String[] countriesOwned) {
        this.countriesOwned = countriesOwned;
    }

    public int getTreasury() {
        return treasury;
    }

    public void setTreasury(int treasury) {
        this.treasury = treasury;
    }

    public boolean isFortification() {
        return fortification;
    }

    public void setFortification(boolean fortification) {
        this.fortification = fortification;
    }
}
