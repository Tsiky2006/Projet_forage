<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des demandes</title>
</head>
<body>
<h1>Liste des demandes</h1>
<p><a href="${pageContext.request.contextPath}/demandes/nouvelle">+ Nouvelle demande</a></p>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Référence</th>
        <th>Demandeur</th>
        <th>Lieu</th>
        <th>Région</th>
        <th>Statut actuel</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="demande" items="${demandes}">
        <tr>
            <td>${demande.reference}</td>
            <td>${demande.nomDemandeur}</td>
            <td>${demande.lieuForage}</td>
            <td>${demande.region}</td>
            <td>${statusMap[demande.id]}</td>
            <td>
                <a href="${pageContext.request.contextPath}/demandes/detail/${demande.id}">Voir détail</a>
                <a href="${pageContext.request.contextPath}/demandes/${demande.id}/devis/nouveau">Créer devis</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
