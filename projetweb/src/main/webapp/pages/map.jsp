﻿<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
   
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="css/overcast/jquery-ui-1.10.4.custom.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/jquery-ui-1.10.4.custom.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="js/jquery-ui-timepicker-addon.js"></script>
    <script src="js/jqueryscript.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Bus VS voiture</title>
  </head>
  <body>
    <div style="display:none" id="header">
        
    </div>
    <div id="container">
        <div id="map">
            <p>Veuillez patienter pendant le chargement de la carte...</p>
        </div>
        <div id="login" class="arrondi">
          <p>${login_message}</p>
        </div>
        <div id="all_favoris" style="display:${display_fav}" class="arrondi">
          <legend class="arronditop">Favoris</legend>
          <c:choose>  
	   		 <c:when test="${not empty liste_favoris}">  
	            <ul>
	            	<c:set var="count" value="0" scope="page" />
					<c:forEach var="favorisValue" items="${liste_favoris}">
						<li id="favoris_${count}">${favorisValue}</li>
						<input id="favoris_depart_${count}" type="hidden" value="${favorisValue.depart}"/>
						<input id="favoris_arrivee_${count}" type="hidden" value="${favorisValue.arrivee}"/>
						<c:set var="count" value="${count + 1}" scope="page"/>
					</c:forEach>
				</ul>        	  
	         </c:when>  
	         <c:otherwise>  
	              Aucun favoris       
	         </c:otherwise>  
		</c:choose>  
        </div>
        <div id="cadre" class="arrondi">
          <form class="form-horizontal">
            <fieldset>

            <!-- Form Name -->
            <legend class="arronditop">Itinéraire</legend>
            <div id="fields">
              <!-- Text input-->
              <div class="control-group">
                <label class="control-label" for="adresse_depart">Adresse départ</label>
                <input id="adresse_depart" name="adresse_depart" type="text" placeholder="Rond point de Rennes" class="input-medium" required="">  
                  <input id="adresse_depart_id" type="hidden"/>
              </div>

              <!-- Text input-->
              <div class="control-group">
                <label class="control-label" for="date_depart">Date départ</label>
                 <input id="date_depart" name="date_depart" type="text" class="input-medium" required="">
              </div>

              <!-- Text input-->
              <div class="control-group">
                <label class="control-label" for="adresse_arrivee">Adresse arrivée</label>
                  <input id="adresse_arrivee" name="adresse_arrivee" type="text" placeholder="Place du cirque" class="input-medium" required="">
                  <input id="adresse_arrivee_id" type="hidden"/>
              </div>

               <!-- Text input-->
              <div class="control-group">
                <label class="control-label" for="date_retour">Date retour</label>
                  <input id="date_retour" name="date_retour" type="text" class="input-medium" required="">
              </div>


              <!-- Select Basic -->
              <div class="control-group">
                <label class="control-label" for="carburant">Carburant</label>
                  <select id="carburant" name="carburant" class="input-medium">
                    <option>Essence</option>
                    <option>Diesel</option>
                    <option>GPL</option>
                    <option>Electrique</option>
                  </select>
              </div>

              <!-- Multiple Radios (inline) -->
              <div class="control-group">
                <label class="control-label" for="abonnement_tan">Abonnement Tan</label>
                    <span style="float:right">
                    <input type="radio" name="abonnement_tan" id="abonnement_tan-0" value="true">
                    Oui
                    <input type="radio" name="abonnement_tan" id="abonnement_tan-1" value="false" checked="checked">
                    Non
                    </span>
              </div>
              <!-- Button -->
              <div class="control-group">
                  <center><button onclick="javascript:return false;"id="singlebutton" name="singlebutton" class="btn btn-primary">Bus ou Voiture ?</button></center>
              </div>
            </div>
            </fieldset>
            </form>
          
        </div>
        <div id="cadre_depart" style="display:none" class="arrondi">
          <form class="form-horizontal">
            <fieldset><legend class="arronditop">Adresse Départ</legend></fieldset>
            <div id="adresses_depart">
            </div>
          </form>
        </div>
        <div id="cadre_arrivee" style="display:none" class="arrondi">
          <form class="form-horizontal">
            <fieldset><legend class="arronditop">Adresse Arrivée</legend></fieldset>
            <div id="adresses_arrivee">
            </div>
          </form>
        </div>
        <div id="cadre_resultat" style="display:none" class="arrondi">
          <form class="form-horizontal">
            <fieldset><legend class="arronditop" style="margin:0px">Résultat</legend></fieldset>
            <div id="resultat">
              <div id="favoris">
				${ajouter_favoris}
              </div>
              <div id="info" style=" text-align: center;">
             	Comparaison Aller/retour      
  			  </div>
              <table>
                <tr>
                  <th class="bus">Bus</th>
                  <th class="voiture">Voiture</th>
                </tr>
                <tr>
                  <td>2.60€</td>
                  <td>3€</td>
                </tr>
                <tr>
                  <td>5 km</td>
                  <td>2 km</td>
                </tr>
                <tr>
                  <td>45 min</td>
                  <td>15 min</td>
                </tr>
              </table>
            </div>
            <div id="retry">
            	<center><a href="javascript: void(0)" id="reload_page">Nouveau trajet</a></center>
            </div>
          </form>
        </div>
    </div>
    <div style="display:none" id="footer">
        
    </div>
    <!-- Include Javascript -->
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script type="text/javascript" src="js/functions.js"></script>
  </body>
</html>