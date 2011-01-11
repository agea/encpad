function pwstrength(input){
	pw = input.value;
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
    if (v>48 && v<72){
        $('#okimg').attr('src','/img/smiley-neutral.png');       
    } else if (v>72 && v<90 ){
        $('#okimg').attr('src','/img/smiley-mr-green.png');       
    } else if (v>90){
        $('#okimg').attr('src','/img/smiley-evil.png');       
    } else {
        $('#okimg').attr('src','/img/smiley-sad.png');       
    }
return v;
}

$(function(){
	
//    $(window).resize(function(){
//    	$("#notepad").height(($("html").innerHeight()-120)+'px');
//    	$("#notepad").width(($("html").innerWidth()-20)+'px');
//    });
//
//    $(window).resize();
    
	$('#notepad').tabby();
    $('#notepad').focus();
	
	$(".password").keyup(function(){
        pwstrength(this);  
    });
    
    
    $(".password").change(function(){
        $(this).keyup();
    });
    
//    $(".action").hover(function(){
//    	$(this).find(".adesc").show(200);
//    },function(){
//    	$(this).find(".adesc").hide(100);
//    	
//    });
    
    $('#saveAction').click(function(e){
    	$('#savepanel').show(100);
    });
    
    $('#savepanel .cancel').click(function(){
    	$('#savepanel').hide(100);
    });
    
    $('#savepanel .ok').click(function(){
    	$('#save_cypher').val(sjcl.encrypt($('#save_password').val(),$('#notepad').val()));
    	$('#saveform').submit();
    });
});