<html>
	<head>
		<title>GharDekho.com</title>
		<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
		<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAADwYjK8Sms9M0uv0qMSAQ9xTQv1zxJZOZ0Shu0YiL-JWh-215bhQc7jysCcQl9-_PwY3yDvIw9NxXOw" type="text/javascript"></script>
		<script type="text/javascript">

    //<![CDATA[

    function Tooltip(gmarker, markup, padding, width){
	this.marker = gmarker;
	this.text = markup;
	this.padding_ = padding;
	this.dragMode = false;
	this.divX = 0;
	this.divY = 0;
	this.offsetX = 0;
	this.offsetY = 0;
	this.mapCenter = new GLatLng(0, 0);
	this.div = null;
	this.map = null;
	this.width = width;
	}

	Tooltip.prototype = new GOverlay();

	Tooltip.prototype.initialize = function(objMap){
		this.map = objMap;
	    this.mapCenter = this.map.getCenter();

		this.div = document.createElement("div");
		this.div.innerHTML = this.text;
		this.div.className = 'tooltip';
		this.div.style.position = 'absolute';
		this.div.style.visibility = 'hidden';
		this.div.style.textAlign = 'center';
		if(this.width === undefined || this.width == 0)
		    this.width = calculateWidth(extractElementText(this.div.childNodes[0])) ;

		this.div.style.width = this.width + 'px';

		var pane = this.map.getPane(G_MAP_MAP_PANE).parentNode.parentNode.parentNode;
		pane.appendChild(this.div);

		if(this.map.TooltipsCollections == null) {
		    this.map.TooltipsCollections = new Array();
		}
		this.map.TooltipsCollections[this.map.TooltipsCollections.lenght] = this;

		var t = this;
		var f = function(){
	        t.reposition();
		};

		GEvent.addListener(this.map, "zoomend", f);
		GEvent.addListener(this.map, "dragend", f);
		GEvent.addListener(this.map, "dblclick", f);
		GEvent.addListener(this.map, "click", f);

		//$(this.div).bgiframe();
	}

	Tooltip.prototype.remove = function(){
		this.div.parentNode.removeChild(this.div);
	}

	Tooltip.prototype.copy = function(){
		return new Tooltip(this.marker,this.text,this.padding_);
	}

	Tooltip.prototype.redraw = function(force){
		if (!force) return;

	    this.mapCenter = this.map.getCenter();

		var markerPos = this.map.fromLatLngToDivPixel(this.marker.getPoint());
		var iconAnchor = this.marker.getIcon().iconAnchor;

		this.divX = Math.round(markerPos.x - this.div.clientWidth / 2);
		this.divY = markerPos.y - iconAnchor.y - this.div.clientHeight - this.padding_;

	    this.setPosition(new GSize(this.divX, this.divY));
	}

	Tooltip.prototype.reposition = function(){
	        var oldPos = this.map.fromLatLngToDivPixel(this.mapCenter);
	        var newPos = this.map.fromLatLngToDivPixel(this.map.getCenter());

	        this.offsetX += oldPos.x - newPos.x;
	        this.offsetY += oldPos.y - newPos.y;

	        this.mapCenter = this.map.getCenter();
	}

	Tooltip.prototype.show = function(){
	    this.reposition();

	    var sz = new GSize(this.offsetX, this.offsetY);
	    this.setPosition(sz);
		this.div.style.visibility = 'visible';
	}

	Tooltip.prototype.hide = function(){
		this.div.style.visibility = 'hidden';
	}


	Tooltip.prototype.setPosition = function(newOffset){
	    var markerPos = this.map.fromLatLngToDivPixel(this.marker.getPoint());
		var iconAnchor = this.marker.getIcon().iconAnchor;

		this.divX = Math.round(markerPos.x - this.div.clientWidth / 2);
		this.divY = markerPos.y - iconAnchor.y - this.div.clientHeight - this.padding_;

	    var offset = new GSize(this.divX + newOffset.width, this.divY + newOffset.height);

	    //decide where to put the tooltip
	    if ((this.div.clientWidth)  > (this.map.getContainer().clientWidth)) { //stretched throuhout whole map - on wide side - use default
		    this.divX = Math.round(markerPos.x - this.div.clientWidth / 2);
		    this.divY = markerPos.y - iconAnchor.y - this.div.clientHeight - this.padding_;
	    }
	    else if(offset.height < 0 && offset.width < 0){ //top left
		    this.divX = markerPos.x + this.padding_;
		    this.divY = markerPos.y + this.padding_;
	    }
	    else if (offset.height >= 0 && (offset.width + this.div.clientWidth)  > (this.map.getContainer().clientWidth)) { //middle right
		    this.divX = Math.round(markerPos.x - this.div.clientWidth);
		    this.divY = markerPos.y - iconAnchor.y - this.div.clientHeight - this.padding_;
	    }
	    else if (offset.height < 0 && (offset.width + this.div.clientWidth)  >= (this.map.getContainer().clientWidth)) { //top right
		    this.divX = Math.round(markerPos.x - this.div.clientWidth);
		    this.divY = markerPos.y + this.padding_;
	    }
	    else if (offset.height >= 0 && offset.width < 0) { //middle left
		    this.divX = markerPos.x + this.padding_;
		    this.divY = markerPos.y - iconAnchor.y - this.div.clientHeight - this.padding_;
	    }
	    else if (offset.height < 0 && offset.width >= 0) { //middle top
		    this.divX = Math.round(markerPos.x - this.div.clientWidth / 2);
		    this.divY = markerPos.y + this.padding_;
	    }
	    else{ //in the middle
		    this.divX = Math.round(markerPos.x - this.div.clientWidth / 2);
		    this.divY = markerPos.y - iconAnchor.y - this.div.clientHeight - this.padding_;
	    }

	    offset = new GSize(this.divX + newOffset.width, this.divY + newOffset.height);

		var pos = new GControlPosition(G_ANCHOR_TOP_LEFT, offset);
		pos.apply(this.div);
	}

	function calculateWidth(text){
	    return text.length * 7 + ( (text.length < 35) ? 20 : -20);
	}
	function extractElementText( element ) {
	    var text = '';
	    if( !element || element.nodeType == 8 ) // ignore html comments
	        return "";
	    var tagName = element.tagName ? element.tagName.toLowerCase() : "";
	    if( tagName == "input" || tagName == "textarea" )
	        return "";

	    var text = element.nodeValue || "";
	    for( var i = 0; i < element.childNodes.length; i++ )
	        text += extractElementText( element.childNodes[ i ] );

	    return text;
	}


    // Create our 'tiny' marker icon
    var customIcon = 'housePushpin.png';
    if(customIcon.length > 0){
        var icon = icon = new GIcon(); //see http://www.google.com/apis/maps/documentation/#Creating_Icons
        icon.image = customIcon;
        icon.shadow = customIcon;
        icon.iconSize = new GSize(12, 13);
        icon.shadowSize = new GSize(12, 13);
        icon.iconAnchor = new GPoint(3, 3);
        icon.infoWindowAnchor = new GPoint(6, 8);
    }



  	//create marker with html info
	function createMarker(map,point, htmlInfo, url, tooltipWidth,icon) {

    var marker = new GMarker(point, icon);
	if(!url)url = '#';

    GEvent.addListener(marker, 'click', function() {
        document.location = url;
    });

    var tooltip = new Tooltip(marker, htmlInfo, 4, tooltipWidth);
    marker.tooltip = tooltip;
    map.addOverlay(marker);
    map.addOverlay(tooltip);

    GEvent.addListener(marker,'mouseover',function(){
        this.tooltip.show();
    });
    GEvent.addListener(marker,'mouseout',function(){
        this.tooltip.hide();
    });
    map.addOverlay(marker);
    return marker;
  }


    function load() {
    }

    //]]>
    </script>
	</head>
	<!--body style="background-image:url(Images/bg-body.gif);"-->
	<body style="background-color:papayawhip; padding: 0px; margin: 0px;" onload="load()">
		<center>
			<div style="width: 900px;margin: 0 auto;padding-top: 3px;">
				<!-- start header -->
				<%@ include file="header.jsp" %>

			<div style="width: 900px;background:white;min-height: 550px">
				<table>
					<tr style="background: url('Images/img041.jpg') repeat-x;color: white;">
						<td colspan="3">
							<h2>
								<b> Search Criteria</b>
							</h2>
						</td>
					</tr>
					<tr>
						<td>
							<table width="300px" height="300px" style="font-size: 14px; color: black; background: url('Images/Interior-Design-Ideas-Screensaver copy.jpg'); background-repeat: no-repeat; border: 1px solid grey;">
								<tr>
									<td colspan="2">
										<!--table>
						<tr>
							<td style="width: 100px; font-size: 15px; padding-left:19px;padding: 5px; background:url('Images/img05.png') no-repeat;">Residential</td>
							<td style="width: 100px; font-size: 15px; padding-left:19px;padding: 5px; background:url('Images/img05.png') no-repeat;">Commercial</td>
						</tr>
					</table-->
									</td>
								</tr>
								<tr>
									<td>
										I want to
									</td>
									<td>
										<input type="radio" checked="checked" value="1" name="property_for" />
										Buy
										<input type="radio" value="2" name="property_for" />
										Rent
									</td>
								</tr>
								<tr>
									<td>
										Property
									</td>
									<td>
										<select style="width: 150px; height: 25px; font-size: 12px;" name="property_type">
											<option selected="" style="background-color: rgb(253, 246, 181);" value="1">
												Residential Property
											</option>
											<option value="3">
												Commercial Property
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										Property Type
									</td>
									<td>
										<select style="width: 150px; height: 25px; font-size: 12px;" name="property_type">
											<option selected="" style="background-color: rgb(253, 246, 181);" value="1">
												Apartment
											</option>
											<option value="4">
												Plot/Land
											</option>
											<option value="5">
												Builder Floor
											</option>
											<option value="6">
												Bungalow/Villa
											</option>
											<option value="7">
												Farm House
											</option>
											<option value="8">
												Service/Studio Apartment
											</option>
											<option value="9">
												Other Residential
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										Locality
									</td>
									<td>
										<select style="width: 150px; height: 25px; font-size: 12px;" name="locality">
											<option selected="" style="background-color: rgb(253, 246, 181); font-size: 12px;" value="1">
												Select Locality
											</option>
											<option value="3">
												Sec: 14
											</option>
											<option value="4">
												Sushant Lok
											</option>
											<option value="5">
												Sec: 53
											</option>
											<option value="6">
												Sec: 42
											</option>
											<option value="7">
												Sanjay Gram
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										Price range
									</td>
									<td>
										<select style="width: 68px; height: 25px; font-size: 12px;" name="start_price">
											<option style="background-color: rgb(253, 246, 181);" value="1">
												5 Lac
											</option>
											<option value="3">
												10 Lac
											</option>
											<option value="4">
												15 Lac
											</option>
											<option value="5">
												20 lac
											</option>
											<option value="6">
												25 Lac
											</option>
											<option value="7">
												30 Lac
											</option>
										</select>
										to
										<select style="width: 68px; height: 25px; font-size: 12px;" name="end_price">
											<option selected="" style="background-color: rgb(253, 246, 181);" value="1">
												10 Lac
											</option>
											<option value="3">
												15 Lac
											</option>
											<option value="4">
												20 Lac
											</option>
											<option value="5">
												25 lac
											</option>
											<option value="6">
												30 Lac
											</option>
											<option value="7">
												Above 30 Lac
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										Bedrooms
									</td>
									<td>
										<select style="width: 150px; height: 25px; font-size: 12px;" name="start_price">
											<option selected="" style="background-color: rgb(253, 246, 181);" value="1">
												1 BedRoom
											</option>
											<option value="3">
												2 BedRoom
											</option>
											<option value="4">
												3 BedRoom
											</option>
											<option value="5">
												4 BedRoom
											</option>
											<option value="6">
												5 BedRoom
											</option>
											<option value="7">
												6+ BedRoom
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<input type="radio" checked="" value="1" name="view" />
										List View
										<input type="radio" value="2" name="view" />
										Map View
									</td>
								</tr>
								<tr>
									<td></td>
									<td align="right" style="font-size: 15px;">
										<div style="border: 1px solid white; width: 70px; text-align:center; background: black;color: white;">
											<a href="/QuarkFramework/webapps/WEB-INF/jsp/mock2.html" style="color: white"> Search </a>
										</div>
									</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
								</tr>
							</table>
						</td>
						<td style="width: 2px">

						</td>
						<td>
							<table id="mapTable" width="100%" cellspacing="1" cellpadding="1" border="0" class="infoBox" style="margin-top: 0px;height: 300px;width:580px;">
								<tbody>
									<tr class="infoBoxContents">
										<td>
											<table cellspacing="0" cellpadding="0" border="0">
												<tbody>
													<tr>
														<td class="main">
															<div id="map" style="width: 580px; height: 300px"></div>
															<div id="message"></div>
															<script type="text/javascript">



    //<![CDATA[

    function load() {
      if (GBrowserIsCompatible()) {
        var map = new GMap2(document.getElementById("map"));

        try{
         	GEvent.addListener(map, 'load',MapLoaded);
	        GEvent.addListener(map, "moveend", function() {
		 	var center = map.getCenter();
		 		document.getElementById("message").innerHTML = center.toString();
			});
		}catch(e){}

		map.addControl(new GMapTypeControl());
		map.addControl(new GLargeMapControl());
		map.addMapType(G_PHYSICAL_MAP);
        map.addControl(new GScaleControl());
        map.addControl(new GSmallZoomControl());

		map.setCenter(new GLatLng(28.609239838396675, 77.2283935546875), 11);




        createMarker(map,new GLatLng(28.578938, 77.165053), '<table cellpadding="0" cellspacing="0" border="0" class="PropertyToolTip"  style="background-color: white;"><tr><td align="left">Click On the image to select locality</td></tr></table>', 'india-new-delhi-innatdelhi.html', 225, icon);
//		createMarker(map,new GLatLng(28.5879364013672, 77.2658004760742), '<table cellpadding="0" cellspacing="0" border="0" class="PropertyToolTip"  style="background-color: white;"><tr><td align="left"><img src="/inns/tiny/new-delhi-royalbedandbreakfastnewdelhi.jpg" /></td><td width="100%" style="padding-left:5px" valign="top" align="left"><strong>BnB New Delhi</strong><br />Rates:  $20 - $100<br />Rooms:  4<br />Rating:  <img  align="absBottom" src="<%= request.getContextPath()%>/Images/rating5.gif" width="75" height="15" border="0"></td></tr></table>','india-new-delhi-royalbedandbreakfastnewdelhi-page.html', 225, icon);
//		createMarker(map,new GLatLng(28.563925, 77.194394), '<table cellpadding="0" cellspacing="0" border="0" class="PropertyToolTip"  style="background-color: white;"><tr><td align="left"><img src="/inns/tiny/new-delhi-chip-peper.jpg" /></td><td width="100%" style="padding-left:5px" valign="top" align="left"><strong>Chip&Peper</strong><br />Rates:  $50 - $70<br />Rooms:  3<br />Rating:  <img  align="absBottom" src="/img/survey/rating4.gif" width="75" height="15" border="0"></td></tr></table>', 'india-new-delhi-chip-peper-page.html', 225, icon);

      }
    }

    //]]>
    </script>


														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr style="background: url('Images/img041.jpg') repeat-x; color: white">

									<td colspan="3">
										<h3>
											<b>Latest Property(s) </b>
										</h3>
									</td>
								</tr>
					<tr>
						<td colspan="3">
							<table cellpadding="0" cellspacing="0">
								<tr style="background: Gainsboro repeat-x; color:black">

									<td colspan="3" style="text-align: center;">

											<b>Property ID - 1234 (Property Name 1) </b>

									</td>
								</tr>
								<tr>
									<td>
										<img width="150" height="150" src="Images/home1.gif" />
									</td>
									<td>
										<TABLE>
											<tr>
												<TD style="width: 100px">
													<b>Rent :</b>
												</TD>
												<TD>
													20000 Rs
												</TD>
											</tr>
											<tr>
												<TD style="width: 100px" valign="top">
													<b>Description :</b>
												</TD>
												<TD>
													We are as close to swank malls as we are to traditional Tuesday markets, making our location very suitable for tourists who want to explore both sides of the ancient city of Delhi.
												</TD>
											</tr>
											<tr>
												<TD style="width: 100px" valign="top">
													<b>Address :</b>
												</TD>
												<TD>
													649, ground Floor,
													<br />
													Sec-14, Gurgaon,
													<br />
													Haryana, India
												</TD>
											</tr>
											<tr>
												<TD style="width: 100px">
													<b>Contact No :</b>
												</TD>
												<TD>
													9999033490
												</TD>
											</tr>
										</TABLE>
									</td>
								</tr>


							</table>

						</td>
					</tr>

					<tr>
						<td colspan="3">
							<table cellpadding="0" cellspacing="0">
								<tr style="background: Gainsboro repeat-x; color:black">

									<td colspan="3" style="text-align: center;">
											<b>Property ID - 4357 (Property Name 2) </b>
									</td>
								</tr>
								<tr>
									<td>
										<img width="150" height="150" src="Images/home1.gif" />
									</td>
									<td>
										<TABLE>
											<tr>
												<TD style="width: 100px">
													<b>Rent :</b>
												</TD>
												<TD>
													25000 Rs
												</TD>
											</tr>
											<tr>
												<TD style="width: 100px" valign="top">
													<b>Description :</b>
												</TD>
												<TD>
													We are as close to swank malls as we are to traditional Tuesday markets, making our location very suitable for tourists who want to explore both sides of the ancient city of Delhi.
												</TD>
											</tr>
											<tr>
												<TD style="width: 100px" valign="top">
													<b>Address :</b>
												</TD>
												<TD>
													649, ground Floor,
													<br />
													Sec-14, Gurgaon,
													<br />
													Haryana, India
												</TD>
											</tr>
											<tr>
												<TD style="width: 100px">
													<b>Contact No :</b>
												</TD>
												<TD>
													9999033490
												</TD>
											</tr>
										</TABLE>
									</td>
								</tr>


							</table>

						</td>
					</tr>

					<tr>
						<td colspan="3">
							<table cellpadding="0" cellspacing="0">
								<tr style="background: Gainsboro repeat-x; color:black">

									<td colspan="3" style="text-align: center;">
											<b>Property ID - 24356 (Property Name 3) </b>
									</td>
								</tr>
								<tr>
									<td>
										<img width="150" height="150" src="Images/home1.gif" />
									</td>
									<td>
										<TABLE>
											<tr>
												<TD style="width: 100px">
													<b>Rent :</b>
												</TD>
												<TD>
													15000 Rs
												</TD>
											</tr>
											<tr>
												<TD style="width: 100px" valign="top">
													<b>Description :</b>
												</TD>
												<TD>
													We are as close to swank malls as we are to traditional Tuesday markets, making our location very suitable for tourists who want to explore both sides of the ancient city of Delhi.
												</TD>
											</tr>
											<tr>
												<TD style="width: 100px" valign="top">
													<b>Address :</b>
												</TD>
												<TD>
													649, ground Floor,
													<br />
													Sec-14, Gurgaon,
													<br />
													Haryana, India
												</TD>
											</tr>
											<tr>
												<TD style="width: 100px">
													<b>Contact No :</b>
												</TD>
												<TD>
													9999033490
												</TD>
											</tr>
										</TABLE>
									</td>
								</tr>


							</table>

						</td>
					</tr>
				</table>
			</div>



		</center>
	</body>
</html>
