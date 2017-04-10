package hello;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {
    //Startar länderna utanför för att skriva om länderna hela tiden
    Board createInitBoard = new Board();
    List<Region> activeGameBoard = createInitBoard.getRegions();
    List<MajorNation> majorNations = new ArrayList<>();
    MajorNation britain = new MajorNation("BRITAIN");
    MajorNation germany = new MajorNation("GERMANY");
    MajorNation france = new MajorNation("FRANCE");
    MajorNation usa = new MajorNation("USA");
    MajorNation japan = new MajorNation("JAPAN");
    MajorNation russia = new MajorNation("RUSSIA");

    String activeCountry = "usa";

    @GetMapping("/map")
    public ModelAndView map(HttpSession session) {
        initMajorNationsList();
        ModelAndView mapmodel = new ModelAndView("map");
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/index.html");
        }
        return mapmodel;
    }

    @MessageMapping("/makeMove")
    @SendTo("/topic/gameRoom")
    public RegionInfo makeMove(String regionIdObject) throws Exception {
        JSONParser myJsonParser = new JSONParser();
        JSONObject myJson = (JSONObject) myJsonParser.parse(regionIdObject);
        //Vi använder teckenkombination !1 för att kunna använda split i Javascript och dela upp

        String gID = ((String) myJson.get("name")).substring(1);
        String majorNationTurn = (String) myJson.get("majorNationTurn");
        int gInt = Integer.parseInt(gID)-1;
        String namesOfAttackRegions = "";
        String idsForAdjacentRegions = "";

        switch (majorNationTurn) {
            case "Britain":
                for (String item : activeGameBoard.get(gInt).getAdjacentRegions()) {
                    if (britain.getRegionsOwned().contains(item)) {
                        namesOfAttackRegions += "!2" + activeGameBoard.get(Integer.parseInt(item.substring(1)) - 1).getName();
                    }
                }
                break;
            case "Germany":
                for (String item : activeGameBoard.get(gInt).getAdjacentRegions()) {
                    if (germany.getRegionsOwned().contains(item)) {
                        namesOfAttackRegions += "!2" + activeGameBoard.get(Integer.parseInt(item.substring(1)) - 1).getName();
                    }
                }
                break;
            case "France":
                for (String item : activeGameBoard.get(gInt).getAdjacentRegions()) {
                    if (france.getRegionsOwned().contains(item)) {
                        namesOfAttackRegions += "!2" + activeGameBoard.get(Integer.parseInt(item.substring(1)) - 1).getName();
                    }
                }
                break;
            case "Usa":
                for (String item : activeGameBoard.get(gInt).getAdjacentRegions()) {
                    if (usa.getRegionsOwned().contains(item)) {
                        namesOfAttackRegions += "!2" + activeGameBoard.get(Integer.parseInt(item.substring(1)) - 1).getName();
                    }
                }
                break;
            case "Japan":
                for (String item : activeGameBoard.get(gInt).getAdjacentRegions()) {
                    if (japan.getRegionsOwned().contains(item)) {
                        namesOfAttackRegions += "!2" + activeGameBoard.get(Integer.parseInt(item.substring(1)) - 1).getName();
                    }
                }
                break;
            case "Russia":
                for (String item : activeGameBoard.get(gInt).getAdjacentRegions()) {
                    if (russia.getRegionsOwned().contains(item)) {
                        namesOfAttackRegions += "!2" + activeGameBoard.get(Integer.parseInt(item.substring(1)) - 1).getName();
                    }
                }
                break;
        }

        for (String adjacent : activeGameBoard.get(gInt).getAdjacentRegions()) {
            idsForAdjacentRegions +="!3"+ adjacent;
        }
        idsForAdjacentRegions +="!3"+ myJson.get("name");

        RegionInfo info = new RegionInfo(namesOfAttackRegions, idsForAdjacentRegions, majorNationTurn);
        info.setTroops(""+activeGameBoard.get(gInt).getTroops());
        info.setNetworth(""+activeGameBoard.get(gInt).getNetworth());
        info.setClickedLand(activeGameBoard.get(gInt).getName());
        return info;
    }

    @MessageMapping("/cancelMove")
    @SendTo("/topic/gameRoom")
    public RegionInfo cancelMove() throws Exception {
    RegionInfo info = new RegionInfo();
    info.setCancelMove(true);
    return info;
    }

    @MessageMapping("/attack")
    @SendTo("/topic/gameRoom")
    public RegionInfo attack(String regionIdObject) throws Exception {
        JSONParser myJsonParser = new JSONParser();
        JSONObject myJson = (JSONObject) myJsonParser.parse(regionIdObject);
        //Vi använder teckenkombination !1 för att kunna använda split i Javascript och dela upp

        String gID = ((String) myJson.get("name"));
        String majorNationTurn = (String) myJson.get("majorNationTurn");
        String attackingFromRegion = (String) myJson.get("attackingRegion");
        int gInt = Integer.parseInt((gID).substring(1))-1;
        long defenderTroops = activeGameBoard.get(gInt).getTroops(); //motsvarar troops i regionen jag väljer

        long attackRegionTroops = 0;
        for (Region region : activeGameBoard) {
            if (region.getName().equalsIgnoreCase(attackingFromRegion)) {
                attackRegionTroops = region.getTroops();
                break;
            }
        }
        RegionInfo info = new RegionInfo();
        info.setMajorNationTurn(majorNationTurn);
        info.setClickedLand(gID);

        info.setAttackMove(true);

            if (attackRegionTroops >= defenderTroops) { //lägg logik och setAttackSuccess här inne
                System.out.println("Woho");
                info.setAttackSuccess(true);
                RemoveRegionFromEveryone(gID);

                switch (majorNationTurn) {
                    case "Britain":
                        britain.addToRegionsOwned(gID);
                        break;
                    case "Germany":
                        germany.addToRegionsOwned(gID);
                        break;
                    case "France":
                        france.addToRegionsOwned(gID);
                        break;
                    case "Usa":
                        usa.addToRegionsOwned(gID);
                        break;
                    case "Japan":
                        japan.addToRegionsOwned(gID);
                        break;
                    case "Russia":
                        russia.addToRegionsOwned(gID);
                        break;
                }

            }
            else {
                System.out.println("Oh no :(");
                info.setAttackSuccess(false);

            }

//        for (String adjacent : activeGameBoard.get(gInt).getAdjacentRegions()) {
//            idsForAdjacentRegions +="!3"+ adjacent;
//        }


        return info;
    }
    public void RemoveRegionFromEveryone(String gID) {
        britain.getRegionsOwned().remove(gID);
        germany.getRegionsOwned().remove(gID);
        france.getRegionsOwned().remove(gID);
        usa.getRegionsOwned().remove(gID);
        japan.getRegionsOwned().remove(gID);
        russia.getRegionsOwned().remove(gID);
    }

    public void initMajorNationsList() {
        if (britain != null){
            majorNations.add(britain);
        }
        if (germany != null){
            majorNations.add(germany);
        }
        if (france != null){
            majorNations.add(france);
        }
        if (usa != null){
            majorNations.add(usa);
        }
        if (japan != null){
            majorNations.add(japan);
        }
        if (russia != null){
            majorNations.add(russia);
        }
    }
}