package hello;

public class RegionInfo {

    private String troops;
    private String networth;
    private String namesOfAttackRegions;
    private String idsForAdjacentRegions;
    private String majorNationTurn;

    private String clickedLand;

    private boolean cancelMove = false;

    private boolean attackMove = false;

    private boolean attackSuccess = false;


    public RegionInfo() {
    }

    public RegionInfo(String namesOfAttackRegions, String idsForAdjacentRegions, String majorNationTurn) {
        this.namesOfAttackRegions = namesOfAttackRegions;
        this.idsForAdjacentRegions = idsForAdjacentRegions;
        this.majorNationTurn = majorNationTurn;
    }

    public void setNamesOfAttackRegions(String namesOfAttackRegions) {
        this.namesOfAttackRegions = namesOfAttackRegions;
    }

    public String getNamesOfAttackRegions() {
        return namesOfAttackRegions;
    }

    public String getIdsForAdjacentRegions() {
        return idsForAdjacentRegions;
    }

    public void setIdsForAdjacentRegions(String idsForAdjacentRegions) {
        this.idsForAdjacentRegions = idsForAdjacentRegions;
    }

    public String getMajorNationTurn() {
        return majorNationTurn;
    }

    public void setMajorNationTurn(String majorNationTurn) {
        this.majorNationTurn = majorNationTurn;
    }
    public boolean isCancelMove() {
        return cancelMove;
    }
    public void setCancelMove(boolean cancelMove) {
        this.cancelMove = cancelMove;
    }
    public boolean isAttackMove() {
        return attackMove;
    }

    public void setAttackMove(boolean attackMove) {
        this.attackMove = attackMove;
    }

    public boolean isAttackSuccess() {
        return attackSuccess;
    }

    public void setAttackSuccess(boolean attackSuccess) {
        this.attackSuccess = attackSuccess;
    }
    public String getClickedLand() {
        return clickedLand;
    }

    public void setClickedLand(String clickedLand) {
        this.clickedLand = clickedLand;
    }

    public String getTroops() {
        return troops;
    }

    public void setTroops(String troops) {
        this.troops = troops;
    }

    public String getNetworth() {
        return networth;
    }

    public void setNetworth(String networth) {
        this.networth = networth;
    }
}
