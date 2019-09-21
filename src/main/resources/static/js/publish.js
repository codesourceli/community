$(function() {
   var title;var description;var tag;

    $(".btn-publish").click(function () {
        title=$("#title").val();description=$("#description").val(); tag=$("#tag").val();
        if (!title || title.toString().length>25){
            $("#error").text("标题错误").show();
            return false;
        }
        if (!description){
            $("#error").text("问题补充不能为空").show();
            return false;
        }
        if (!tag){
            $("#error").text("标签错误").show();
            return false;
        }
    })

})

/*title
description
tag*/

