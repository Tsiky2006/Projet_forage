<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Historique des statuts</title>
</head>
<body>
<h1>HISTORIQUE DES STATUTS</h1>
<div>
    <p>Demande : <strong>${demande.reference}</strong></p>
    <p>Demandeur : <strong>${demande.nomDemandeur}</strong></p>
</div>
<table border="1" cellpadding="6" cellspacing="0">
    <tr>
        <th>Date</th>
        <th>Statut</th>
    </tr>
    <c:forEach var="item" items="${historique}">
        <tr>
            <td>${item.dateChangement}</td>
            <td>${item.statut.nom}</td>
        </tr>
    </c:forEach>
</table>
<div>
    <a href="${pageContext.request.contextPath}/demandes/detail/${demande.id}">Retour au détail</a>
</div>
</body>
</html>
