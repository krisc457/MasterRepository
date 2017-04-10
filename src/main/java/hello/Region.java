package hello;

import java.util.ArrayList;
import java.util.List;

public class Region {

    private String regionID;
    private String name;
    private long troops;
    private long networth;
    private List<String> adjacentRegions = new ArrayList<>();

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTroops() {
        return troops;
    }

    public void setTroops(long troops) {
        this.troops = troops;
    }

    public long getNetworth() {
        return networth;
    }

    public void setNetworth(long networth) {
        this.networth = networth;
    }

    public Region(){
    }

    public Region(String regionID, String name) {
        this.regionID = regionID;
        this.name = name;
    }

    public Region(String regionID, String name, long troops, long networth) {
        this.regionID = regionID;
        this.name = name;
        this.troops = troops;
        this.networth = networth;
    }

    public List<String> getAdjacentRegions() {
        return adjacentRegions;
    }

    public void addToAdjacentRegions(String addRegionString) {
        this.adjacentRegions.add(addRegionString);
    }

    /*
    countries = new Region[37];
    countries[1] = new Region("Columbia");
    countries[2] = new Region("Russian America");
    countries[3] = new Region("Northwest Territories");
    countries[4] = new Region("Alberta");
    countries[5] = new Region("Nunavut");
    countries[6] = new Region("Quebec");
    countries[7] = new Region("Ontario");
    countries[8] = new Region("Eastern USA");
    countries[9] = new Region("Central USA");
    countries[10] = new Region("Western USA");
    countries[11] = new Region("Southern USA");
    countries[12] = new Region("Mexico");
    countries[13] = new Region("Cuba");
    countries[14] = new Region("Jamaica");
    countries[15] = new Region("Haiti");
    countries[16] = new Region("Puerto Rico");
    countries[17] = new Region("Guatemala");
    countries[18] = new Region("Belize");
    countries[19] = new Region("El Salavador");
    countries[20] = new Region("Honduras");
    countries[21] = new Region("Nicaragua");
    countries[22] = new Region("Costa Rica");
    countries[23] = new Region("Granada");
    countries[24] = new Region("Venezuela");
    countries[25] = new Region("Guiana");
    countries[26] = new Region("Brazil");
    countries[27] = new Region("Ecuador");
    countries[28] = new Region("Peru");
    countries[29] = new Region("Bolivia");
    countries[30] = new Region("Paraguay");
    countries[31] = new Region("Chile");
    countries[32] = new Region("Argentine Republic");
    countries[33] = new Region("Uruguay");
    countries[34] = new Region("Patagonia");
    countries[35] = new Region("Tierra del Fuego");
    countries[36] = new Region("Falkland Isles");
    countries[202] = new Region("Hawaii");


    countries[1].adjacentCountries = new TreeSet<Region>();
    countries[1].adjacentCountries.add(countries[10]);
    countries[1].adjacentCountries.add(countries[4]);
    countries[1].adjacentCountries.add(countries[3]);

    countries[2].adjacentCountries = new TreeSet<Region>();
    countries[2].adjacentCountries.add(countries[1]);

    countries[3].adjacentCountries = new TreeSet<Region>();
    countries[3].adjacentCountries.add(countries[1]);
    countries[3].adjacentCountries.add(countries[4]);
    countries[3].adjacentCountries.add(countries[5]);

    countries[4].adjacentCountries = new TreeSet<Region>();
    countries[4].adjacentCountries.add(countries[1]);
    countries[4].adjacentCountries.add(countries[10]);
    countries[4].adjacentCountries.add(countries[9]);
    countries[4].adjacentCountries.add(countries[7]);
    countries[4].adjacentCountries.add(countries[5]);
    countries[4].adjacentCountries.add(countries[3]);

    countries[5].adjacentCountries = new TreeSet<Region>();
    countries[5].adjacentCountries.add(countries[3]);
    countries[5].adjacentCountries.add(countries[4]);
    countries[5].adjacentCountries.add(countries[7]);

    countries[6].adjacentCountries = new TreeSet<Region>();
    countries[6].adjacentCountries.add(countries[7]);
    countries[6].adjacentCountries.add(countries[8]);

    countries[7].adjacentCountries = new TreeSet<Region>();
    countries[7].adjacentCountries.add(countries[4]);
    countries[7].adjacentCountries.add(countries[5]);
    countries[7].adjacentCountries.add(countries[6]);
    countries[7].adjacentCountries.add(countries[8]);
    countries[7].adjacentCountries.add(countries[9]);

    countries[8].adjacentCountries = new TreeSet<Region>();
    countries[8].adjacentCountries.add(countries[6]);
    countries[8].adjacentCountries.add(countries[7]);
    countries[8].adjacentCountries.add(countries[9]);
    countries[8].adjacentCountries.add(countries[11]);

    countries[9].adjacentCountries = new TreeSet<Region>();
    countries[9].adjacentCountries.add(countries[10]);
    countries[9].adjacentCountries.add(countries[4]);
    countries[9].adjacentCountries.add(countries[7]);
    countries[9].adjacentCountries.add(countries[8]);
    countries[9].adjacentCountries.add(countries[11]);

    countries[10].adjacentCountries = new TreeSet<Region>();
    countries[10].adjacentCountries.add(countries[1]);
    countries[10].adjacentCountries.add(countries[4]);
    countries[10].adjacentCountries.add(countries[9]);
    countries[10].adjacentCountries.add(countries[11]);
    countries[10].adjacentCountries.add(countries[12]);

    countries[11].adjacentCountries = new TreeSet<Region>();
    countries[11].adjacentCountries.add(countries[8]);
    countries[11].adjacentCountries.add(countries[9]);
    countries[11].adjacentCountries.add(countries[10]);
    countries[11].adjacentCountries.add(countries[12]);

    countries[12].adjacentCountries = new TreeSet<Region>();
    countries[12].adjacentCountries.add(countries[10]);
    countries[12].adjacentCountries.add(countries[11]);
    countries[12].adjacentCountries.add(countries[17]);
    countries[12].adjacentCountries.add(countries[18]);

    countries[13].adjacentCountries = new TreeSet<Region>();

    countries[14].adjacentCountries = new TreeSet<Region>();

    countries[15].adjacentCountries = new TreeSet<Region>();

    countries[16].adjacentCountries = new TreeSet<Region>();

    countries[17].adjacentCountries = new TreeSet<Region>();
    countries[17].adjacentCountries.add(countries[12]);
    countries[17].adjacentCountries.add(countries[18]);
    countries[17].adjacentCountries.add(countries[20]);
    countries[17].adjacentCountries.add(countries[19]);

    countries[18].adjacentCountries = new TreeSet<Region>();
    countries[18].adjacentCountries.add(countries[12]);
    countries[18].adjacentCountries.add(countries[17]);

    countries[19].adjacentCountries = new TreeSet<Region>();
    countries[19].adjacentCountries.add(countries[17]);
    countries[19].adjacentCountries.add(countries[20]);

    countries[20].adjacentCountries = new TreeSet<Region>();
    countries[20].adjacentCountries.add(countries[17]);
    countries[20].adjacentCountries.add(countries[19]);
    countries[20].adjacentCountries.add(countries[21]);

    countries[21].adjacentCountries = new TreeSet<Region>();
    countries[21].adjacentCountries.add(countries[20]);
    countries[21].adjacentCountries.add(countries[22]);

    countries[22].adjacentCountries = new TreeSet<Region>();
    countries[22].adjacentCountries.add(countries[21]);
    countries[22].adjacentCountries.add(countries[23]);

    countries[23].adjacentCountries = new TreeSet<Region>();
    countries[23].adjacentCountries.add(countries[22]);
    countries[23].adjacentCountries.add(countries[24]);
    countries[23].adjacentCountries.add(countries[26]);
    countries[23].adjacentCountries.add(countries[27]);

    countries[24].adjacentCountries = new TreeSet<Region>();
    countries[24].adjacentCountries.add(countries[23]);
    countries[24].adjacentCountries.add(countries[25]);
    countries[24].adjacentCountries.add(countries[26]);

    countries[25].adjacentCountries = new TreeSet<Region>();
    countries[25].adjacentCountries.add(countries[24]);
    countries[25].adjacentCountries.add(countries[26]);

    countries[26].adjacentCountries = new TreeSet<Region>();
    countries[26].adjacentCountries.add(countries[23]);
    countries[26].adjacentCountries.add(countries[24]);
    countries[26].adjacentCountries.add(countries[25]);
    countries[26].adjacentCountries.add(countries[27]);
    countries[26].adjacentCountries.add(countries[28]);
    countries[26].adjacentCountries.add(countries[29]);
    countries[26].adjacentCountries.add(countries[30]);
    countries[26].adjacentCountries.add(countries[32]);
    countries[26].adjacentCountries.add(countries[33]);

    countries[27].adjacentCountries = new TreeSet<Region>();
    countries[27].adjacentCountries.add(countries[28]);
    countries[27].adjacentCountries.add(countries[26]);
    countries[27].adjacentCountries.add(countries[23]);

    countries[28].adjacentCountries = new TreeSet<Region>();
    countries[28].adjacentCountries.add(countries[27]);
    countries[28].adjacentCountries.add(countries[26]);
    countries[28].adjacentCountries.add(countries[29]);

    countries[29].adjacentCountries = new TreeSet<Region>();
    countries[29].adjacentCountries.add(countries[28]);
    countries[29].adjacentCountries.add(countries[26]);
    countries[29].adjacentCountries.add(countries[30]);
    countries[29].adjacentCountries.add(countries[32]);
    countries[29].adjacentCountries.add(countries[31]);

    countries[30].adjacentCountries = new TreeSet<Region>();
    countries[30].adjacentCountries.add(countries[26]);
    countries[30].adjacentCountries.add(countries[29]);
    countries[30].adjacentCountries.add(countries[32]);

    countries[31].adjacentCountries = new TreeSet<Region>();
    countries[31].adjacentCountries.add(countries[29]);
    countries[31].adjacentCountries.add(countries[32]);
    countries[31].adjacentCountries.add(countries[34]);

    countries[32].adjacentCountries = new TreeSet<Region>();
    countries[32].adjacentCountries.add(countries[31]);
    countries[32].adjacentCountries.add(countries[29]);
    countries[32].adjacentCountries.add(countries[30]);
    countries[32].adjacentCountries.add(countries[26]);
    countries[32].adjacentCountries.add(countries[33]);
    countries[32].adjacentCountries.add(countries[34]);

    countries[33].adjacentCountries = new TreeSet<Region>();
    countries[33].adjacentCountries.add(countries[26]);
    countries[33].adjacentCountries.add(countries[32]);

    countries[34].adjacentCountries = new TreeSet<Region>();
    countries[34].adjacentCountries.add(countries[32]);
    countries[34].adjacentCountries.add(countries[31]);
    countries[34].adjacentCountries.add(countries[35]);

    countries[35].adjacentCountries = new TreeSet<Region>();
    countries[35].adjacentCountries.add(countries[34]);

    countries[36].adjacentCountries = new TreeSet<Region>();

    countries[202].adjacentCountries = new TreeSet<Region>();
*/
}