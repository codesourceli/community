$(function() {

    $("#question").show();
    $("#message").hide();
    $("#collection").hide();


    $("#a-question").click(function () {
        $("#question").show();
        $("#message").hide();
        $("#collection").hide();
    })
    $("#a-message").click(function () {
        $("#question").hide();
        $("#message").show();
        $("#collection").hide();
    })
    $("#a-collection").click(function () {
        $("#question").hide();
        $("#message").hide();
        $("#collection").show();
    })

})

/*title
description
tag*/

