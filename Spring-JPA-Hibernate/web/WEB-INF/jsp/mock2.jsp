<html>
	<head>
		<title>GharDekho.com</title>
		<link rel="stylesheet" type="text/css" href="css/stylesheet.css">

	</head>
	<!--body style="background-image:url(images/bg-body.gif);"-->
	<body style="background-color:papayawhip; padding: 0px; margin: 0px;">
		<center>
			<div style="width: 900px;margin: 0 auto;padding-top: 3px;">
				<!-- start header -->
				<%@ include file="header.jsp" %>

			<div style="width: 900px;background:white;min-height: 550px">
				<table>
					<tr style="background: url('images/img041.jpg') repeat-x;color: white;">
						<td colspan="3">
							<h2>
								<b> Search Criteria</b>
							</h2>
						</td>
					</tr>
					<tr>
						<td>
							<table width="300px" height="300px" style="font-size: 14px; color: black; background: url('images/Interior-Design-Ideas-Screensaver copy.jpg'); background-repeat: no-repeat; border: 1px solid grey;">
								<tr>
									<td colspan="2">
										<!--table>
						<tr>
							<td style="width: 100px; font-size: 15px; padding-left:19px;padding: 5px; background:url('images/img05.png') no-repeat;">Residential</td>
							<td style="width: 100px; font-size: 15px; padding-left:19px;padding: 5px; background:url('images/img05.png') no-repeat;">Commercial</td>
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
										<input type="radio"  value="1" checked="" name="view" />
										List View
										<input type="radio" value="2"  name="view" />
										Map View
									</td>
								</tr>
								<tr>
									<td></td>
									<td align="right" style="font-size: 15px;">
										<div style="border: 1px solid white; width: 70px; text-align:center; background: black;color: white;">
											<a href="mock3.html" style="color: white"> Search </a>
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
							<table id="mapTable" width="100%" cellspacing="1" cellpadding="1" class="infoBox" style="margin-top: 0px;height: 300px;width:580px;background: url('images/ieqr9d copy.jpg') no-repeat;border: 1px solid gray">
								<tbody>
									<tr >
										<td style="text-align: center;">
											<table cellspacing="0" cellpadding="0" border="0" align="center">
												<tbody>
													<tr>
														<TD colspan="2">
															<b>Search Results: </b>
														</TD>
														
													</tr>
													<tr>
													<td height="20">
														&nbsp;
													</td>
													
													</tr>
													<tr>
														<TD style="width: 200px">
															<b>Property(s) Found: </b>
														</TD>
														<TD>
															50
														</TD>
													</tr>
													<tr>
														<TD style="width: 200px" valign="top">
															<b>Mininum Rent:</b>
														</TD>
														<TD>
															20000 Rs
														</TD>
													</tr>
													<tr>
														<TD style="width: 200px" valign="top">
															<b>Maximun Value:</b>
														</TD>
														<TD>
														 50000 Rs
														</TD>
													</tr>
													<tr>
														<TD style="width: 200px">
															<b>Key:</b>
														</TD>
														<TD>
															Value
														</TD>
													</tr>
													<tr>
													<td height="20">
														&nbsp;
													</td>
													
													</tr>
													<tr>
														<TD colspan="2">
														Click here to see map for locality selection.
														</TD>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr style="background: url('images/img041.jpg') repeat-x; color: white">
									
									<td colspan="3">
										<h3>
											<b>Search Results </b>
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
										<img width="150" height="150" src="images/home1.gif" />
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
										<img width="150" height="150" src="images/home1.gif" />
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
										<img width="150" height="150" src="images/home1.gif" />
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
