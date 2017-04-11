$(document).ready(function() {

    var isDragging = false;
    var clicked = false, clickY, clickX;
    $(document).on({
        "mousemove": function(e) {
            isDragging = true;
            clicked && updateScrollPos(e);
        },
        "mousedown": function(e) {
            $(this).mousemove(function(){
                $("g > g > a").removeAttr("data-toggle");
            });
            isDragging = false;
            clicked = true;
            clickY = e.pageY;
            clickX = e.pageX;
        },
        "mouseup": function() {
            $(this).mousemove(function(){
                $("g > g > a").attr("data-toggle", "modal");
            });
            clicked = false;
        }
    });

    var updateScrollPos = function(e) {
        $(window).scrollTop($(window).scrollTop() + (clickY - e.pageY));
        $(window).scrollLeft($(window).scrollLeft() + (clickX - e.pageX));
    }

    var majorNationTurn = "Britain";

    var windowHeight = $(window).height();
    var windowWidth = $(window).width();

    $("#majorNationsDropdown > li > a").click(function(){
        majorNationTurn = $(this).attr("id");
    });

    $('g > a').mouseup(function () {
        if(isDragging == false) {
            if ($(this).children().hasClass("chosen")) {
                $(".adjacent").removeClass("adjacent");
                $(".chosen").removeClass("chosen");
                $(".others").removeClass("others");
            } else {
                var myId = $(this).parent().parent().attr('id');
                stompClient.send("/app/makeMove", {}, JSON.stringify({
                    'name': myId,
                    "majorNationTurn": majorNationTurn
                }));
                setTimeout(function () {
                    $(".adjacent").removeClass("adjacent");
                    $(".chosen").removeClass("chosen");
                    $(".others").removeClass("others");
                    $("#" + clickedRegionToHaveAdjacents + " > g > a > path").addClass("toBeChosen");
                    // $("path:not(.adjacent):not(.chosen)").addClass("others");
                    clickedRegionToHaveAdjacents = "";
                    var gameMapTop = $("#gameMap").offset().top;
                    var gameMapleft = $("#gameMap").offset().left;
                    var chosenTop = $(".toBeChosen").offset().top;
                    var chosenLeft = $(".toBeChosen").offset().left;
                    var halfChosenHeight = $(".toBeChosen").height() / 2;
                    var halfChosenWidth = $(".toBeChosen").width() / 2;
                    var halfWindowHeight = windowHeight / 2;
                    var halfWindowWidth = windowWidth / 2;
                    console.log("XXXXXX: " + gameMapleft);
                    $("body, html").animate({
                        scrollTop: chosenTop - halfWindowHeight + halfChosenHeight,
                        scrollLeft: chosenLeft - halfWindowWidth + halfChosenWidth
                    }, 100, function () {
                        for (var i = 0; i < clickedRegionAdjacents.length; i++) {
                            $("#" + clickedRegionAdjacents[i] + " > g > a > path").addClass("adjacent");
                        }
                        $(".toBeChosen").addClass("chosen");
                        $(".toBeChosen").removeClass("toBeChosen");
                        $("path:not(.adjacent):not(.chosen)").addClass("others");
                    });
                }, 250);
            }
        }
    });

    $("#btnClose, .close").click(function(){
        stompClient.send("/app/cancelMove", {});
    });

    var width = $("#gameMap").width();
    var height = $("#gameMap").height();

    $("#zoomIn").click(function(e){
        var newWidth = width * 1.25;
        var newHeight = height * 1.25;
        var scrolledX = window.pageXOffset;
        var scrolledY = window.pageYOffset;
        $("#gameMap").css("transform-origin", 0 + "px" + scrolledY + "px");
        $("#gameMap").css("animation", "zoomIn 0.5s");
        /*
         setTimeout(function(){
         $("#gameMap").css("width", newWidth + "px");
         $("#gameMap").css("height", newHeight + "px");
         window.scrollTo(scrolledX * 1.25, scrolledY * 1.26);
         }, 500);
         */
    });

    $("#zoomOut").click(function(e){
        var newWidth = width * 0.8;
        var newHeight = height * 0.8;
        $("#gameMap").css("animation", "zoomOut 0.5s");
        $("#gameMap").css("animation", "zoomOut 0.5s");
        setTimeout(function(){
            $("#gameMap").css("width", newWidth + "px");
            $("#gameMap").css("height", newHeight + "px");
        }, 500);
    });


});

$(function () {
    $('.dropdown-toggle').dropdown();
    $('#majorNationsDropdown li > a').click(function () {
        $('#droptext').text($(this).html());
    });
});