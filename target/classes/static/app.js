var stompClient = null;
var clickedRegionToHaveAdjacents;
var clickedRegionAdjacents = [];

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

$(document).ready(function () {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/topic/gameRoom', function (greeting) {
            updateGame(
                JSON.parse(greeting.body).namesOfAttackRegions,
                JSON.parse(greeting.body).idsForAdjacentRegions,
                JSON.parse(greeting.body).majorNationTurn,
                JSON.parse(greeting.body).cancelMove,
                JSON.parse(greeting.body).attackMove,
                JSON.parse(greeting.body).attackSuccess,
                JSON.parse(greeting.body).clickedLand,
                JSON.parse(greeting.body).troops,
                JSON.parse(greeting.body).networth,
                JSON.parse(greeting.body).moveTroops,
                JSON.parse(greeting.body).namesOfPossibleMoveRegions
            );
        });
    });
});
/*
 function disconnect() {
 if (stompClient != null) {
 stompClient.disconnect();
 }
 setConnected(false);
 console.log("Disconnected");
 }
 */
/*
 function sendGameTurnData() {
 stompClient.send("/app/endTurn", {}, JSON.stringify({'name': $("#name").val()}));
 }
 */
function updateGame(namesOfAttackRegions, idsForAdjacentRegions, majorNationTurn, cancelMove, attackMove, attackSuccess, clickedLand, troops, networth, moveTroops, namesOfPossibleMoveRegions) {
    clickedRegionAdjacents.length = 0;
    if (namesOfAttackRegions != null) {
        var namesOfAttackRegions = namesOfAttackRegions.split("!2");
    } else {
        namesOfAttackRegions = "";
    }

    if (idsForAdjacentRegions != null) {
        var idsForAdjacentRegions = idsForAdjacentRegions.split("!3");
    } else {
        idsForAdjacentRegions = "";
    }
    if (namesOfPossibleMoveRegions != null) {
        var namesOfPossibleMoveRegions = namesOfPossibleMoveRegions.split("!4");
        console.log(namesOfPossibleMoveRegions);
    } else {
        console.log("Variabeln är tom...");
        namesOfPossibleMoveRegions = "";
    }

    var majorNationTurn = majorNationTurn;
    var chosenRegion = idsForAdjacentRegions[idsForAdjacentRegions.length - 1];

    if (cancelMove) {
        $(".adjacent").removeClass("adjacent");
        $(".chosen").removeClass("chosen");
        $(".others").removeClass("others");
        return;
    }
    else if (moveTroops) {
        $(".adjacent").removeClass("adjacent");
        $(".chosen").removeClass("chosen");
        $(".others").removeClass("others");
        //gör moveTroops stuff here
        console.log("moveTroops: " + moveTroops);
    }
    else if (attackMove) {
        $(".adjacent").removeClass("adjacent");
        $(".chosen").removeClass("chosen");
        $(".others").removeClass("others");

        console.log("attackSuccess: " + attackSuccess);

        if (attackSuccess) {
            switch (majorNationTurn) {
                case "Britain":
                    $("#" + clickedLand + " > g > a > path").removeClass();
                    $("#" + clickedLand + " > g > a > path").addClass("active purple");
                    break;
                case "Germany":
                    $("#" + clickedLand + " > g > a > path").removeClass();
                    $("#" + clickedLand + " > g > a > path").addClass("active blue");
                    break;
                case "France":
                    $("#" + clickedLand + " > g > a > path").removeClass();
                    $("#" + clickedLand + " > g > a > path").addClass("active green");
                    break;
                case "Usa":
                    $("#" + clickedLand + " > g > a > path").removeClass();
                    $("#" + clickedLand + " > g > a > path").addClass("active teal");
                    break;
                case "Japan":
                    $("#" + clickedLand + " > g > a > path").removeClass();
                    $("#" + clickedLand + " > g > a > path").addClass("active yellow");
                    break;
                case "Russia":
                    $("#" + clickedLand + " > g > a > path").removeClass();
                    $("#" + clickedLand + " > g > a > path").addClass("active red");
                    break;
            }
        }
        return;
    }

    $("#CountryName").html(clickedLand);
    $("#CountryValues").html("<p>Troops :" + troops + "</p><p>Networth : " + networth + "</p>");

    // $(".adjacent").removeClass("adjacent");
    // $(".chosen").removeClass("chosen");
    // $(".others").removeClass("others");
    // for(var i=1; i<idsForAdjacentRegions.length-1; i++){
    //     $("#" + idsForAdjacentRegions[i] + " > g > a > path").addClass("adjacent");
    // }
    //
    // $("#" + chosenRegion + " > g > a > path").addClass("chosen");
    // $("path:not(.adjacent):not(.chosen)").addClass("others");

    clickedRegionToHaveAdjacents = chosenRegion;
    for (var i = 1; i < idsForAdjacentRegions.length - 1; i++) {
        clickedRegionAdjacents.push(idsForAdjacentRegions[i]);
    }

    var attackRegionOutput = "";
    for (var i = 1; i < namesOfAttackRegions.length; i++) {
        attackRegionOutput += "<button type='button' class='btn btn-default attackFrom' data-dismiss='modal' value='" + namesOfAttackRegions[i] + "'>" + namesOfAttackRegions[i] + "</button><br>";
    }
    var moveFromRegion = "";
    for (var i = 1; i < namesOfPossibleMoveRegions.length; i++) {
        moveFromRegion += "<button type='button' class='btn btn-default moveFrom' data-dismiss='modal' value='" + namesOfPossibleMoveRegions[i] + "'>" + namesOfPossibleMoveRegions[i] + "</button><br>";
    }
    $("#ifAttackIsPossible").append().html("<h4>Du kan attackera från:</h4>" + attackRegionOutput);
    $("#ifMoveTroopsTrue").append().html("<h4>Du kan flytta från:</h4>" + moveFromRegion);

    $(".attackFrom").click(function () { //ta den här funktionen för att replikera en movetroops-funktion.
        var attackingRegion = $(this).attr('value');
        stompClient.send("/app/attack", {}, JSON.stringify({
            'name': chosenRegion,
            "majorNationTurn": majorNationTurn,
            'attackingRegion': attackingRegion
        }));
    });

    //
    // for (var i = 1; i < namesOfAttackRegions.length; i++) {
    //     attackRegionOutput += "<button type='button' class='btn btn-default attackFrom' data-dismiss='modal' value='" + namesOfAttackRegions[i] + "'>" + namesOfAttackRegions[i] + "</button><br>";
    // }

    // $("#rangeValue1").click(function ()  {
    //     var testVar =
    // })

    $(".moveFrom").click(function () {
        var moveToRegion = clickedLand;
        var moveFromRegion = $(this).attr('value');
        var testVar = $("#rangeValue1").attr('text');
        stompClient.send("/app/moveTroops", {}, JSON.stringify({
            'moveToRegion': moveToRegion,
            'name': chosenRegion,
            "majorNationTurn": majorNationTurn,
            'moveFromRegion': moveFromRegion
        }));
    });

}

/*
 function disconnect() {
 if (stompClient != null) {
 stompClient.disconnect();
 }
 setConnected(false);
 console.log("Disconnected");
 }
 */

/*
 function sendGameTurnData() {
 stompClient.send("/app/endTurn", {}, JSON.stringify({'name': $("#name").val()}));
 }
 */


/*
 $(function () {
 $("form").on('submit', function (e) {
 e.preventDefault();
 });
 $( "#connect" ).click(function() { connect(); });
 $( "#disconnect" ).click(function() { disconnect(); });
 $( "#endTurn" ).click(function() { sendGameTurnData(); });
 });
 */

/*
 function disconnect() {
 if (stompClient != null) {
 stompClient.disconnect();
 }
 setConnected(false);
 console.log("Disconnected");
 }
 */

/*
 function sendGameTurnData() {
 stompClient.send("/app/endTurn", {}, JSON.stringify({'name': $("#name").val()}));
 }
 */