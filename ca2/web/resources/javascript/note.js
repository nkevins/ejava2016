$(function() {
    $("#start").on("click", function() {
        var category = $("#category").val();
        $("#configuration").toggle();
        $("#noticeBoard").toggle();
        $("#title").text(category + ' Notice');
        start(category);
    });
    
});

function start(category) {
    var socket = new WebSocket("ws://localhost:8080/ca2/note/"+category);
    socket.onmessage = function(evt) {
        var data = JSON.parse(evt.data);

        for(var i = 0; i < data.length; i++) {
            var obj = data[i];

            var title = obj.title;
            var category = obj.category;
            var content = obj.content;
            var user = obj.user;
            var dt = new Date(obj.createdDate);
            var formattedDate = $.format.date(dt, 'dd-MMM-yy HH:mm:ss');
            
            $("#notes ul").prepend('<li><p><span class="title">'+category+' - '+title+'</span><br /></p><p><span>'+content+'</span></p><p class="no-pad-bottom date-posted">Created by '+user+', '+formattedDate+'</p></li>');
        }
    };
}