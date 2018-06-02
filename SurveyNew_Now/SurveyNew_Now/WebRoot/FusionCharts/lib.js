d=document;w=window;m=Math;
l={};l.gt=function(id){return d.getElementById(id);};l.op=function(ur,nm,pr){w.open(ur,nm,pr||'menubar=0,statusbar=0,width=640,height=480,scrollbars=yes'); return false;};
g={};g.cn=function(ob,cn){l.gt(ob).className=cn;};g.sh=function(obs,obh){g.cn(obs,'visible');if(obh) g.cn(obh,'hidden');};


var GALLERY_RENDERER = "flash";
var tmpChart = new FusionCharts("Column2D.swf", "tmpChartId", "560", "400", "0", "0");
var NO_FLASH = tmpChart.options.renderer=="javascript";
tmpChart.dispose();
tmpChart = null;

if(NO_FLASH || /GALLERY_RENDERER=javascript/i.test(document.cookie) )
{
	GALLERY_RENDERER = 'javascript';
	
}


if (typeof jQuery != 'undefined') { 
		
	$(document).ready(function()
	{
		
		
		// automatic orphan location redirection
		if(navigator.userAgent.search(/MSIE/)>=0)
		{
			if(window.__orphanarticle!==false && top === self)	
			{
			   window.__orphanarticle = true;
			}
		}
		else 
		{
			if (window.__orphanarticle!==false && window.parent && (window.parent === window || window.parent.location.domain !== window.location.domain)) 
			{
				window.__orphanarticle = true;
			}
		}
		
		$("body").css("background", "none");
		
		
		
		$("div.qua-button-holder").html
		(
			'<a id="toggleView" class="qua qua-button view-chart-data jschart" href="javascript:void(0)" style="width:155px;'+(GALLERY_RENDERER.toLowerCase() =='javascript'?'display:none;':'')+'"><span>View JavaScript Chart</span></a>\n\
			<a id="toggleView" class="qua qua-button view-chart-data flashchart" href="javascript:void(0)" style="width:155px;'+(GALLERY_RENDERER.toLowerCase()  =='flash'?'display:none;':'')+'"><span>View Flash Chart</span></a>\n\
			<a class="qua qua-button view-chart-data view-xml" href="javascript:void(0)"><span>View XML</span></a>\n\
			<a class="qua qua-button view-chart-data view-json" href="javascript:void(0)" style="width:90px;"><span>View JSON</span></a>'
		);


		if(typeof isJSChartNotAvailable!="undefined" && isJSChartNotAvailable==true)
		{
			$("div.qua-button-holder .jschart").hide();
			$("div.qua-button-holder .flashchart").hide();
			
			$("div.qua-button-holder").css({"padding-left":"130px", "width":"350px", "margin":"0 auto" });
			
		}

		$("div.show-code-block").html('<div class="show-code-close-icon"><a href="javascript:void(0)"><img src="../assets/ui/images/close.gif" /></a></div><span id="titlebar">XML</span><div class="clear"></div><pre class="prettyprint"></pre><div class="show-code-close-btn"><a class="qua qua-button" href="javascript:void(0)"><span>Close</span></a></div>');


		$("a.view-xml").unbind("click").click( function() {
			
			$("#titlebar").html("XML Data");	
			var chartDATA = "";
			
			var chartCount = 0;
			for(var i in FusionCharts.items)
			{
				if(chartCount>0) 
				{
					chartDATA +="<hr style='height:10px; width:100%; border:none; border-bottom:1px dashed #ddd;'/><hr style='height:10px; width:100%; border:none; border:none;'/>";
				}
				chartCount++;				
				chartDATA += FusionCharts.items[i].getChartData('xml').replace(/\</gi, "&lt;").replace(/\>/gi, "&gt;");
			}

			showChartData(chartDATA);	
		});

		$("a.view-json").unbind("click").click( function() {
										 
			$("#titlebar").html("JSON Data");
			var chartDATA = "";

			var chartCount = 0;
			for(var i in FusionCharts.items)
			{
				
				if(chartCount>0) 
				{
					chartDATA +="<hr style='height:10px; width:100%; border:none; border-bottom:1px dashed #ddd;'/><hr style='height:10px; width:100%; border:none; border:none;'/>";
				}
				chartCount++;
				chartDATA += JSON.stringify(FusionCharts.items[i].getChartData('json'), null, 2);		

			}
		
			showChartData(chartDATA);	
		});
		
		
		$("a.jschart").unbind("click").click ( function()
			{
				$("a.jschart,a.flashchart").toggle();
				swapRenderer("javascript");
			}
		);
			
		$("a.flashchart").unbind("click").click ( function()
			{
				$("a.jschart,a.flashchart").toggle();
				swapRenderer("flash");
			}
		);

		$('.show-code-close-btn a').unbind("click").click(function() {
			$('.show-code-block').hide();
		});
	
	
		$('.show-code-close-icon a').click(function() {
			$('.show-code-block').hide();
		});


		if(NO_FLASH)
		{
			$("#toggleView").remove();
			$(".qua-button-holder").css({"margin":"0 auto", "width":"290px"});
		}
		
	});
 
}

$(document).ready(function(){
	if(NO_FLASH) $("#toggleView").hide();
});

function swapRenderer (name) {
	
	document.cookie = "GALLERY_RENDERER="+name.toLowerCase();
	
	$(".rtupdate").html(window.rtUpdateText);

	if(window.dataUpdateTimer) window.clearInterval(window.dataUpdateTimer);
	
	var repoCharts = [] ;
	
	for(var i in FusionCharts.items)
	{

		var newChart = [];
		var chartCount = 0;
		
		if (!(FusionCharts.items[i] instanceof FusionCharts)) 
		{
			continue;
		}
		
		//var newChart = FusionCharts.items[i].clone({ renderer: name , id:FusionCharts(i).id.replace(/_flash$|_javascript$/i, "") + "_"+name });
		
		var fcProperties = {};
		fcProperties["swfUrl"]  = FusionCharts.items[i].args.swfUrl;
		fcProperties["id"]  =  FusionCharts(i).id.replace(/_flash$|_javascript$/i, "") + "_"+name ;
		
		if(name.toLowerCase()!="flash" || name.toLowerCase()!="javascript" )
		{
			name = FusionCharts.items[i].options.renderer=="flash"?"javascript":"flash";
		}
		
		FusionCharts.items[i].options.renderer;
		fcProperties["renderer"]  = name ;
		fcProperties["dataSource"] = FusionCharts.items[i].getXMLData();
		fcProperties["dataFormat"] = "xml";
		fcProperties["height"]  = FusionCharts.items[i].args.height ;
		fcProperties["width"]  = FusionCharts.items[i].args.width;
		fcProperties["renderAt"] = FusionCharts.items[i].options.containerElementId;
		
		repoCharts.push(fcProperties);
		
		if(FusionCharts.items[i] && FusionCharts.items[i].dispose) FusionCharts.items[i].dispose(); 
		
		
	}
	
	window.setTimeout(function() {
		for (var charrConfigId in repoCharts)
		{
			var newChart = [];
			newChart[charrConfigId] = FusionCharts.render(repoCharts[charrConfigId]);
		}
	} ,0 );
		

	

}; 


function showChartData(data)
{
	$('pre.prettyprint').html( data );
	$('.show-code-block').css('height', ($(document).height() - 56) ).show();
	prettyPrint();

}

