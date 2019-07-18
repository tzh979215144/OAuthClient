(function(){ 
	/*
	 * @author SEVEN
	 * @use 判断浏览器类型
	 */
	$.extend( { http:{} } );  
    $.extend($.http,{
    	isShowExport:function() {  //判断是否是谷歌、火狐、safari,opera浏览器
    	    var ua = window.navigator.userAgent;  
    	    var isIE = window.ActiveXObject != undefined && ua.indexOf("MSIE") != -1;  
    	    var isFirefox = ua.indexOf("Firefox") != -1; 
    	    var isOpera = window.opr != undefined;  
    	    var isChrome = ua.indexOf("Chrome") && window.chrome;  
    	    var isSafari = ua.indexOf("Safari") != -1 && ua.indexOf("Version") != -1;  
    	    if (isIE) {  
    	        return false;  
    	    } else if (isFirefox) {  
    	        return true;  
    	    } else if (isOpera) {  
    	        return true;  
    	    } else if (isChrome) {  
    	        return true;  
    	    } else if (isSafari) {  
    	        return true;  
    	    } else {  
    	        return false;  
    	    }  
    	},
    	bigIE9:function(errInfo){ //判断浏览器版本是否大于ie9
    		var browser=navigator.appName;
    		if(navigator.appName=="Microsoft Internet Explorer"){
    			var b_version=navigator.userAgent;
    			var b_version1=navigator.appVersion;
    			var version=b_version.split(";");
    			var trim_Version=version[1].replace(/[ ]/g,"");
    			if(trim_Version=="MSIE7.0"||trim_Version=="MSIE8.0"|| trim_Version=="MSIE6.0"||trim_Version=="MSIE9.0") { 
    				HRwin.alert(errInfo);
    				return false;
    			}
    		}
    		return true;
    	}
    })
})(jQuery);