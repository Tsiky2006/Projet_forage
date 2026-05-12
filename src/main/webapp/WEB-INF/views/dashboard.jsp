<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dashboard - Gestion Forage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
<h1>PROJET FORAGE</h1>
<div>
    <p>Total demandes : <strong>${totalDemandes}</strong></p>
    <p>Demandes créées : <strong>${demandesCreees}</strong></p>
    <p>Devis créés : <strong>${devisCrees}</strong></p>
    <p>Études en cours : <strong>${etudesEnCours}</strong></p>
    <p>Clôturées : <strong>${cloturees}</strong></p>
</div>
<div>
    <a href="${pageContext.request.contextPath}/demandes/nouvelle">Nouvelle demande</a>
    <a href="${pageContext.request.contextPath}/demandes/liste">Liste des demandes</a>
</div>
</body>
</html>
