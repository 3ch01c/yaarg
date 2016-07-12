<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	session = request.getSession();
	String action = request.getParameter("action");
	String email = "",
		firstName = "First name",
		lastName = "Last name",
		streetAddress = "Address",
		city = "City",
		state = "State",
		zip = "ZIP",
		country = "Country";
	if (action.equals("Update") || action.equals("Delete")) {
		email = session.getAttribute("email").toString();
		firstName = session.getAttribute("firstName").toString();
		lastName = session.getAttribute("lastName").toString();
		streetAddress = session.getAttribute("streetAddress").toString();
		city = session.getAttribute("city").toString();
		state = session.getAttribute("state").toString();
		zip = session.getAttribute("zip").toString();
		country = session.getAttribute("country").toString();
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=action%> Account</title>
<link href="css/all.css" rel="stylesheet" type="text/css" />
<link href="css/formval.css" rel="stylesheet" type="text/css" />
<script src="js/passwordCheck.js" type="text/javascript"></script>
<script type="text/javascript">
	// Fields that are not required are true by default.
	var errs = new Boolean(false);
	var firstNameValid = new Boolean(true);
	var lastNameValid = new Boolean(true);
	var phoneValid = new Boolean(true);
	var emailValid = new Boolean(false);
	var usernameValid = new Boolean(true);
	var passValid = new Boolean(false);
	var passMatch = new Boolean(false);

	// Notifications to user
	function msg(field, msgType, msg){
		var element = document.getElementById(field)
		element.textContent = msg;
		element.className = msgType;
	}
	
	function validateForm(form){
		if (firstNameValid.valueOf() && lastNameValid.valueOf() && phoneValid.valueOf() && emailValid.valueOf() && usernameValid.valueOf() && passValid.valueOf() && passMatch.valueOf()){
			document.getElementById("submitButton").disabled = false;
		} else {
			document.getElementById("submitButton").disabled = true;
		}
	};
	
	function validateFirstName(field, inf_field, required){
		if (field.value.length < 1){
			msg(inf_field, "error", "ERROR: You must enter a first name!");
			firstNameValid = false;
		} else {
			msg(inf_field, "valid", "First name is valid.");
			firstNameValid = true;
		}
	}
	
	function validateLastName(field, inf_field, required){
		if (field.value.length < 1){
			msg(inf_field, "error", "ERROR: You must enter a last name.");
			lastNameValid = false;
		} else {
			msg(inf_field, "valid", "Last name is valid.");
			lastNameValid = true;
		}
	}
	
	function validatePhone(field, inf_field, required){
		var number = /\d{10}?/ ;
		if (!number.test(field.value)){
			msg(inf_field, "error", "ERROR: Not a valid phone number.");
			phoneValid = false;
		} else {
			msg(inf_field, "valid", "Phone number is valid.");
			phoneValid = true;
		}
	}
	
	function validateEmail(field, inf_field, required){
		var email = /^[A-Za-z][\w.-]+@\w[\w.-]+\.[\w.-]*[A-Za-z][A-Za-z]$/  ;
		if (!email.test(field.value)){
			msg(inf_field, "error", "ERROR: Not a valid e-mail streetAddress.");
			emailValid = false;
			field.style.backgroundColor = "red";
			field.style.borderColor = "red";
		} else {
			msg(inf_field, "valid", "Email is valid.");
			emailValid = true;
			field.style.backgroundColor = "lightgreen";
			field.style.borderColor = "lightgreen";
		}
	}
	
	function validateUsername(field, inf_field, required){
		if (field == null || field == ""){
			msg(inf_field, "error", "Not a valid username.");
			usernameValid = false;
		} else {
			msg(inf_field, "valid", "Username is available.");
			usernameValid = true;
		}
	}
	
	function validatePass(field, inf_field, required){
		var strength = testPassword(field.value);
		if (strength < 25){
			msg(inf_field, "error", "Password is too weak.");
			passValid = false;
			field.style.backgroundColor = "red";
			field.style.borderColor = "red";
		} else {
			msg(inf_field, "valid", "Password is strong!");
			passValid = true;
			field.style.backgroundColor = "lightgreen";
			field.style.borderColor = "lightgreen";
		}
	}
	
	function verifyPass(field1, field2, inf_field, required){
		if (field2.value == "") {
			field2.style.backgroundColor = "";
			field2.style.borderColor = "";
			return;
		}
		if (!passValid.valueOf()) {
			msg(inf_field, "error", "ERROR: Please create a valid password.");
			passMatch = false;
			field2.style.backgroundColor = "red";
			field2.style.borderColor = "red";
		} else if (field1.value != field2.value){
			msg(inf_field, "error", "ERROR: Passwords do not match.");
			passMatch = false;
			field2.style.backgroundColor = "red";
			field2.style.borderColor = "red";
		} else {
			msg(inf_field, "valid", "Passwords match.");
			passMatch = true;
			field2.style.backgroundColor = "lightgreen";
			field2.style.borderColor = "lightgreen";
		}
	}

	function confirmDelete(){
		var r = confirm('Are you sure you want to delete your account? (This cannot be undone.)');
		if (r == true) {
			document.location = "AccountMgr?action=Delete&email=<%=email%>"
		}
	}
</script>
</head>
<body>
<h2><%=action%> Account</h2>
<noscript>Your browser does not support JavaScript or you
have it disabled. In any case, you cannot play this game without those
features.</noscript>
<form id="accountForm" name="accountForm" method="post" action="AccountMgr?action=<%=action%>" target="_top">
<fieldset><legend>Personal information</legend>
<% if (action.equals("Update")) { %>
	<div id="delete"><input type="button" onclick="confirmDelete()" value="Delete my account" /></div>
<% } %>
<p>This information is <b>not</b> required. It's used for two
things:</p>
<ul>
	<li>It makes it easier for other players to find you.</li>
	<li>You'll start out at your home streetAddress.</li>
</ul>
<table>
	<tr>
		<td width="130"><label for="firstName">Name</label></td>
		<td><input type="text" name="firstName" id="firstName"
			tabindex="1"
			onfocus="if(this.value.match('First name')) this.value = '';"
			onblur="if(this.value == '') this.value='First name';"
			value="<%=firstName%>" /></td>
		<td colspan="2"><input type="text" name="lastName" id="lastName"
			tabindex="3"
			onfocus="if(this.value.match('Last name')) this.value = '';"
			onblur="if(this.value == '') this.value='Last name';"
			value="<%=lastName%>" /></td>
	</tr>
	<tr>
		<td><label for="streetAddress">Address</label></td>
		<td colspan="4"><input name="streetAddress" type="text" id="streetAddress"
			tabindex="4" size="45"
			onfocus="if(this.value.match('Street address')) this.value = '';"
			onblur="if(this.value == '') this.value='Street address';"
			value="<%=streetAddress%>" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="text" name="city" id="city" tabindex="5"
			onfocus="if(this.value.match('City')) this.value = '';"
			onblur="if(this.value == '') this.value='City';" value="<%=city%>" /></td>
		<td><select name="state" id="state" tabindex="6">
			<option value="<%=state%>"><%=state%></option>
			<option value="AL">AL</option>
			<option value="AK">AK</option>
			<option value="AZ">AZ</option>
			<option value="AR">AR</option>
			<option value="CA">CA</option>
			<option value="CO">CO</option>
			<option value="CT">CT</option>
			<option value="DE">DE</option>
			<option value="FL">FL</option>
			<option value="GA">GA</option>
			<option value="HI">HI</option>
			<option value="ID">ID</option>
			<option value="IL">IL</option>
			<option value="IN">IN</option>
			<option value="IA">IA</option>
			<option value="KS">KS</option>
			<option value="KY">KY</option>
			<option value="LA">LA</option>
			<option value="ME">ME</option>
			<option value="MD">MD</option>
			<option value="MA">MA</option>
			<option value="MI">MI</option>
			<option value="MN">MN</option>
			<option value="MS">MS</option>
			<option value="MO">MO</option>
			<option value="MT">MT</option>
			<option value="NE">NE</option>
			<option value="NV">NV</option>
			<option value="NH">NH</option>
			<option value="NJ">NJ</option>
			<option value="NM">NM</option>
			<option value="NY">NY</option>
			<option value="NC">NC</option>
			<option value="ND">ND</option>
			<option value="OH">OH</option>
			<option value="OK">OK</option>
			<option value="OR">OR</option>
			<option value="PA">PA</option>
			<option value="RI">RI</option>
			<option value="SC">SC</option>
			<option value="SD">SD</option>
			<option value="TN">TN</option>
			<option value="TX">TX</option>
			<option value="UT">UT</option>
			<option value="VT">VT</option>
			<option value="VA">VA</option>
			<option value="WA">WA</option>
			<option value="DC">DC</option>
			<option value="WV">WV</option>
			<option value="WI">WI</option>
			<option value="WY">WY</option>
		</select></td>
		<td><input name="zip" type="text" id="zip" tabindex="7" size="8"
			maxlength="10" onfocus="if(this.value.match('ZIP')) this.value = ''"
			onblur="if(this.value == '') this.value='ZIP';" value="<%=zip%>" /></td>
	</tr>
	<tr>
		<td></td>
		<td colspan="3"><select name="country" id="country" tabindex="8"
			size="1" style="width: 300px;">
			<option value="<%=country%>"><%=country%></option>
			<option value="">Afghanistan</option>
			<option value="">&Aring;land Islands</option>
			<option value="">Albania</option>
			<option value="">Algeria</option>
			<option value="">American Samoa</option>
			<option value="">Andorra</option>
			<option value="">Angola</option>
			<option value="">Anguilla</option>
			<option value="">Antarctica</option>
			<option value="">Antigua and Barbuda</option>
			<option value="">Argentina</option>
			<option value="">Armenia</option>
			<option value="">Aruba</option>
			<option value="">Australia</option>
			<option value="">Austria</option>
			<option value="">Azerbaijan</option>
			<option value="">Bahamas</option>
			<option value="">Bahrain</option>
			<option value="">Bangladesh</option>
			<option value="">Barbados</option>
			<option value="">Belarus</option>
			<option value="">Belgium</option>
			<option value="">Belize</option>
			<option value="">Benin</option>
			<option value="">Bermuda</option>
			<option value="">Bhutan</option>
			<option value="">Bolivia</option>
			<option value="">Bosnia and Herzegovina</option>
			<option value="">Botswana</option>
			<option value="">Bouvet Island</option>
			<option value="">Brazil</option>
			<option value="">British Indian Ocean territory</option>
			<option value="">Brunei Darussalam</option>
			<option value="">Bulgaria</option>
			<option value="">Burkina Faso</option>
			<option value="">Burundi</option>
			<option value="">Cambodia</option>
			<option value="">Cameroon</option>
			<option value="">Canada</option>
			<option value="">Cape Verde</option>
			<option value="">Cayman Islands</option>
			<option value="">Central African Republic</option>
			<option value="">Chad</option>
			<option value="">Chile</option>
			<option value="">China</option>
			<option value="">Christmas Island</option>
			<option value="">Cocos (Keeling) Islands</option>
			<option value="">Colombia</option>
			<option value="">Comoros</option>
			<option value="">Congo</option>
			<option value="">Congo, Democratic Republic</option>
			<option value="">Cook Islands</option>
			<option value="">Costa Rica</option>
			<option value="">C&ocirc;te d'Ivoire (Ivory Coast)</option>
			<option value="">Croatia (Hrvatska)</option>
			<option value="">Cuba</option>
			<option value="">Cyprus</option>
			<option value="">Czech Republic</option>
			<option value="">Denmark</option>
			<option value="">Djibouti</option>
			<option value="">Dominica</option>
			<option value="">Dominican Republic</option>
			<option value="">East Timor</option>
			<option value="">Ecuador</option>
			<option value="">Egypt</option>
			<option value="">El Salvador</option>
			<option value="">Equatorial Guinea</option>
			<option value="">Eritrea</option>
			<option value="Estonia">Estonia</option>
			<option value="Ethiopia">Ethiopia</option>
			<option value="Falkland Islands">Falkland Islands</option>
			<option value="Faroe Islands">Faroe Islands</option>
			<option value="Fiji">Fiji</option>
			<option value="Finland">Finland</option>
			<option value="France">France</option>
			<option value="French Guiana">French Guiana</option>
			<option value="French Polynesia">French Polynesia</option>
			<option value="French Southern Territories">French Southern
			Territories</option>
			<option value="Gabon">Gabon</option>
			<option value="Gambia">Gambia</option>
			<option value="Georgia">Georgia</option>
			<option value="Germany">Germany</option>
			<option value="Ghana">Ghana</option>
			<option value="Gibraltar">Gibraltar</option>
			<option value="Greece">Greece</option>
			<option value="Greenland">Greenland</option>
			<option value="Grenada">Grenada</option>
			<option value="Guadeloupe">Guadeloupe</option>
			<option value="Guam">Guam</option>
			<option value="Guatemala">Guatemala</option>
			<option value="Guinea">Guinea</option>
			<option value="Guinea-Bissau">Guinea-Bissau</option>
			<option value="Guyana">Guyana</option>
			<option value="Haiti">Haiti</option>
			<option value="Heard and McDonald Islands">Heard and
			McDonald Islands</option>
			<option value="Honduras">Honduras</option>
			<option value="Hong Kong">Hong Kong</option>
			<option value="Hungary">Hungary</option>
			<option value="Iceland">Iceland</option>
			<option value="India">India</option>
			<option value="Iran">Iran</option>
			<option value="Iraq">Iraq</option>
			<option value="Ireland">Ireland</option>
			<option value="Israel">Israel</option>
			<option value="Italy">Italy</option>
			<option value="Jamaica">Jamaica</option>
			<option value="Japan">Japan</option>
			<option value="Jordan">Jordan</option>
			<option value="Kazakhstan">Kazakhstan</option>
			<option value="Kenya">Kenya</option>
			<option value="Kiribati">Kiribati</option>
			<option value="North Korea">Korea (north)</option>
			<option value="South Korea">Korea (south)</option>
			<option value="Kuwait">Kuwait</option>
			<option value="Kyrgyzstan">Kyrgyzstan</option>
			<option value="Lao People's Democratic Republic">Lao
			People's Democratic Republic</option>
			<option value="Latvia">Latvia</option>
			<option value="Lebanon">Lebanon</option>
			<option value="Lesotho">Lesotho</option>
			<option value="Liberia">Liberia</option>
			<option value="Libyan Arab Jamahiriya">Libyan Arab
			Jamahiriya</option>
			<option value="Liechtenstein">Liechtenstein</option>
			<option value="Lithuania">Lithuania</option>
			<option value="Luxembourg">Luxembourg</option>
			<option value="Macao">Macao</option>
			<option value="Macedonia, Former Yugoslav Republic Of">Macedonia,
			Former Yugoslav Republic Of</option>
			<option value="Madagascar">Madagascar</option>
			<option value="Malawi">Malawi</option>
			<option value="Malaysia">Malaysia</option>
			<option value="Maldives">Maldives</option>
			<option value="Mali">Mali</option>
			<option value="Malta">Malta</option>
			<option value="Marshall Islands">Marshall Islands</option>
			<option value="Martinique">Martinique</option>
			<option value="Mauritania">Mauritania</option>
			<option value="Mauritius">Mauritius</option>
			<option value="Mayotte">Mayotte</option>
			<option value="Mexico">Mexico</option>
			<option value="Micronesia">Micronesia</option>
			<option value="Moldova">Moldova</option>
			<option value="Monaco">Monaco</option>
			<option value="Mongolia">Mongolia</option>
			<option value="Montenegro">Montenegro</option>
			<option value="Montserrat">Montserrat</option>
			<option value="Morocco">Morocco</option>
			<option value="Mozambique">Mozambique</option>
			<option value="Myanmar">Myanmar</option>
			<option value="Namibia">Namibia</option>
			<option value="Nauru">Nauru</option>
			<option value="Nepal">Nepal</option>
			<option value="Netherlands">Netherlands</option>
			<option value="Netherlands Antilles">Netherlands Antilles</option>
			<option value="New Caledonia">New Caledonia</option>
			<option value="New Zealand">New Zealand</option>
			<option value="Nicaragua">Nicaragua</option>
			<option value="Niger">Niger</option>
			<option value="Nigeria">Nigeria</option>
			<option value="Niue">Niue</option>
			<option value="Norfolk Island">Norfolk Island</option>
			<option value="Northern Mariana Islands">Northern Mariana
			Islands</option>
			<option value="Norway">Norway</option>
			<option value="Oman">Oman</option>
			<option value="Pakistan">Pakistan</option>
			<option value="Palau">Palau</option>
			<option value="Palestinian Territories">Palestinian
			Territories</option>
			<option value="Panama">Panama</option>
			<option value="Papua New Guinea">Papua New Guinea</option>
			<option value="Paraguay">Paraguay</option>
			<option value="Peru">Peru</option>
			<option value="Philippines">Philippines</option>
			<option value="Pitcairn">Pitcairn</option>
			<option value="Poland">Poland</option>
			<option value="Portugal">Portugal</option>
			<option value="Puerto Rico">Puerto Rico</option>
			<option value="Qatar">Qatar</option>
			<option value="R&eacute;union">R&eacute;union</option>
			<option value="Romania">Romania</option>
			<option value="Russian Federation">Russian Federation</option>
			<option value="Rwanda">Rwanda</option>
			<option value="Saint Helena">Saint Helena</option>
			<option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
			<option value="Saint Lucia">Saint Lucia</option>
			<option value="Saint Pierre and Miquelon">Saint Pierre and
			Miquelon</option>
			<option value="Saint Vincent and the Grenadines">Saint
			Vincent and the Grenadines</option>
			<option value="Samoa">Samoa</option>
			<option value="San Marino">San Marino</option>
			<option value="Sao Tome and Principe">Sao Tome and Principe</option>
			<option value="Saudi Arabia">Saudi Arabia</option>
			<option value="Senegal">Senegal</option>
			<option value="Serbia">Serbia</option>
			<option value="Seychelles">Seychelles</option>
			<option value="Sierra Leone">Sierra Leone</option>
			<option value="Singapore">Singapore</option>
			<option value="Slovakia">Slovakia</option>
			<option value="Slovenia">Slovenia</option>
			<option value="Solomon Islands">Solomon Islands</option>
			<option value="Somalia">Somalia</option>
			<option value="South Africa">South Africa</option>
			<option value="South Georgia and the South Sandwich Islands">South
			Georgia and the South Sandwich Islands</option>
			<option value="Spain">Spain</option>
			<option value="Sri Lanka">Sri Lanka</option>
			<option value="Sudan">Sudan</option>
			<option value="Suriname">Suriname</option>
			<option value="Svalbard and Jan Mayen Islands">Svalbard and
			Jan Mayen Islands</option>
			<option value="Swaziland">Swaziland</option>
			<option value="Sweden">Sweden</option>
			<option value="Switzerland">Switzerland</option>
			<option value="Syria">Syria</option>
			<option value="Taiwan">Taiwan</option>
			<option value="Tajikistan">Tajikistan</option>
			<option value="Tanzania">Tanzania</option>
			<option value="Thailand">Thailand</option>
			<option value="Togo">Togo</option>
			<option value="Tokelau">Tokelau</option>
			<option value="Tonga">Tonga</option>
			<option value="Trinidad and Tobago">Trinidad and Tobago</option>
			<option value="Tunisia">Tunisia</option>
			<option value="Turkey">Turkey</option>
			<option value="Turkmenistan">Turkmenistan</option>
			<option value="Turks and Caicos Islands">Turks and Caicos
			Islands</option>
			<option value="Tuvalu">Tuvalu</option>
			<option value="Uganda">Uganda</option>
			<option value="Ukraine">Ukraine</option>
			<option value="United Arab Emirates">United Arab Emirates</option>
			<option value="United Kingdom">United Kingdom</option>
			<option value="United States of America">United States of
			America</option>
			<option value="Uruguay">Uruguay</option>
			<option value="Uzbekistan">Uzbekistan</option>
			<option value="Vanuatu">Vanuatu</option>
			<option value="Vatican City">Vatican City</option>
			<option value="Venezuela">Venezuela</option>
			<option value="Vietnam">Vietnam</option>
			<option value="Virgin Islands (British)">Virgin Islands
			(British)</option>
			<option value="Virgin Islands (US)">Virgin Islands (US)</option>
			<option value="Wallis and Futuna Islands">Wallis and Futuna
			Islands</option>
			<option value="Western Sahara">Western Sahara</option>
			<option value="Yemen">Yemen</option>
			<option value="Zaire">Zaire</option>
			<option value="Zambia">Zambia</option>
			<option value="Zimbabwe">Zimbabwe</option>
		</select></td>
	</tr>
	<!--
	<tr>
		<td><label for="phone">Phone</label></td>
		<td><input type="text" name="phone" id="phone" tabindex="9"
			size="10" maxlength="10" onchange="" /></td>
	</tr>
-->
</table>
</fieldset>
<fieldset><legend>Login information</legend>
<p>This information is required.</p>
<table>
	<tr>
		<td width="130"><label for="email">* Email</label></td>
		<td><input type="text" name="email" id="email" tabindex="10"
			onchange="validateEmail(this, 'inf_email', true); validateForm(this.parent);"
			value="<%=email%>" /></td>
		<td>
		<div id="inf_email"></div>
		</td>
	</tr>
	<tr>
		<td><label for="passwd">* Enter password</label></td>
		<td><input type="password" name="passwd" id="passwd" tabindex="11"
			onkeyup="validatePass(this, 'inf_pass', true); msg('inf_verifyPass', 'info', ''); verifyPass(this, document.getElementById('pass2'), 'inf_verifyPass', true); validateForm(this.parent);" /></td>
		<td>
		<div id="inf_pass"></div>
		</td>
	</tr>
	<tr>
		<td><label for="pass2">* Re-enter password</label></td>
		<td><input type="password" name="pass2" id="pass2" tabindex="12"
			onkeyup="verifyPass(document.getElementById('passwd'), this, 'inf_verifyPass', true); validateForm(this.parent);" /></td>
		<td>
		<div id="inf_verifyPass"></div>
		</td>
	</tr>
</table>
</fieldset>
<input type="submit" name="submitButton" id="submitButton"
	value="<%=action%>" tabindex="12" disabled="disabled" onclick="this.value = 'Please wait...';" />&nbsp;<input
	type="reset" name="resetButton" id="resetButton" value="Reset form"
	tabindex="13" />&nbsp;<a href="index.jsp" target="_top"><input
	type="button" name="resetButton" id="resetButton" value="Cancel" /></a></form>
<% if (action.equals("Update")) { %>
	<script type="text/javascript">validateEmail(document.getElementById("email"), 'inf_email', true); validateForm(document.getElementById("accountForm"));</script>
<% } %>
</body>
</html>