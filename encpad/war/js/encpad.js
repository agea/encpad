function pwstrength(pw){
    var v = 0;
    if (new RegExp("[a-z]").test(pw)){
        v+=12;
    }
    if (new RegExp("[A-Z]").test(pw)){
        v+=12;
    }
    if (new RegExp("[0-9]").test(pw)){
        v+=12;
    }
    if (new RegExp("\\W").test(pw)){
        v+=16;
    }
    if (pw.length>7){
        v+=12;
    }
    if (pw.length>9){
        v+=12;
    }
    if (pw.length>11){
        v+=12;
    }
    if (pw.length>13){
        v+=12;
    }
    p = $('#pwstrength');
    p.css('width',v+'%');
    if (v>33 && v<66){
        p.css('background', '-webkit-gradient(linear, 0 0, 0 bottom, from(#ff9000), to(#bb4000))');
        p.css('background', '-moz-linear-gradient(#ff9000, #bb4000)');
        p.css('background', 'linear-gradient(#ff9000, #bb4000)');        
    } else if (v>66){
        p.css('background', '-webkit-gradient(linear, 0 0, 0 bottom, from(#00ff00), to(#00bb00))');
        p.css('background', '-moz-linear-gradient(#00ff00, #00bb00)');
        p.css('background', 'linear-gradient(#00ff00, #00bb00)');
    } else {
        p.css('background', '-webkit-gradient(linear, 0 0, 0 bottom, from(#ff0000), to(#bb0000))');
        p.css('background', '-moz-linear-gradient(#ff0000, #bb0000)');
        p.css('background', 'linear-gradient(#ff0000, #bb0000)');
    }
return v;
}

$(function(){
    $("#password").keyup(function(){
        pwstrength(this.value);  
    });
    
    
    $("#password").change(function(){
        $(this).keyup();
    });
    
    $(".action").hover(function(){
    	$(this).find(".adesc").show(100);
    },function(){
    	$(this).find(".adesc").hide();
    	
    });
    
});