var currentDate = new Date();
currentDate.addSeconds(-6*8);


var dataString ='<chart manageResize="1" animation="0" numDisplaySets="10" showRealTimeValue="1" rotateLabels="1" slantLabels="1" \n\
	caption="1#蜗轮负荷"  subcaption="(1分钟刷新)" >\n\
<categories>\n\
<category label="'+currentDate.addSeconds(60).toString("HH:mm") +'" />\n\
<category label="'+currentDate.addSeconds(60).toString("HH:mm") +'" />\n\
<category label="'+currentDate.addSeconds(60).toString("HH:mm") +'" />\n\
<category label="'+currentDate.addSeconds(60).toString("HH:mm") +'" />\n\
<category label="'+currentDate.addSeconds(60).toString("HH:mm") +'" />\n\
<category label="'+currentDate.addSeconds(60).toString("HH:mm") +'" />\n\
<category label="'+currentDate.addSeconds(60).toString("HH:mm") +'" />\n\
<category label="'+currentDate.addSeconds(60).toString("HH:mm") +'" />\n\
<category label="' + currentDate.addSeconds(60).toString("HH:mm") + '" />\n\
<category label="' + currentDate.addSeconds(60).toString("HH:mm") + '" />\n\
</categories>\n\
<dataset seriesName="Unique Visitors" showValues="0" >\n\
<set value="2800" />\n\
<set value="3200" />\n\
<set value="3000" />\n\
<set value="2500" />\n\
<set value="3000" />\n\
<set value="3200" />\n\
<set value="3400" />\n\
<set value="3000" />\n\
<set value="2000" />\n\
<set value="1300" />\n\
</dataset>\n\
<styles>\n\
      <definition>\n\
         <style type="font" name="BigFont" size="35"  bgColor="DEF1FF" BorderColor ="2C516D"  />\n\
      </definition>\n\
      <application>\n\
         <apply toObject="REALTIMEVALUE" styles="BigFont" />\n\
      </application>\n\
   </styles>\n\
</chart>'; 