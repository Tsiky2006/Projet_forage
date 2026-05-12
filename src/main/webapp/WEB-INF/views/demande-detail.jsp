<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Détail de la demande</title>
</head>
<body>
<h1>DÉTAIL DEMANDE : ${demande.reference}</h1>
<div>
    <p>Nom demandeur : ${demande.nomDemandeur}</p>
    <p>Lieu forage : ${demande.lieuForage}</p>
    <p>Fokontany : ${demande.fokontany}</p>
    <p>District : ${demande.district}</p>
    <p>Région : ${demande.region}</p>
    <p>Date demande : ${demande.dateDemande}</p>
</div>
<div>
    <p>Statut actuel : <strong>${statutActuel}</strong></p>
</div>
<div>
    <a href="${pageContext.request.contextPath}/demandes/${demande.id}/devis/nouveau">Créer devis</a>
    <form action="${pageContext.request.contextPath}/demandes/${demande.id}/statut" method="post" style="display:inline;">
        <input type="hidden" name="statutNom" value="Étude en cours" />
        <button type="submit">Passer en étude</button>
    </form>
    <form action="${pageContext.request.contextPath}/demandes/${demande.id}/statut" method="post" style="display:inline;">
        <input type="hidden" name="statutNom" value="Forage suspendu" />
        <button type="submit">Suspendre</button>
    </form>
    <form action="${pageContext.request.contextPath}/demandes/${demande.id}/statut" method="post" style="display:inline;">
        <input type="hidden" name="statutNom" value="Demande clôturée" />
        <button type="submit">Clôturer</button>
    </form>
</div>
<div>
    <h2>Devis associé</h2>
    <c:choose>
        <c:when test="${not empty devis}">
            <p>Type devis : ${devis.typeDevis.nom}</p>
            <p>Observation : ${devis.observation}</p>
            <p>Date de création : ${devis.dateCreation}</p>
            <table border="1" cellpadding="6" cellspacing="0">
                <tr>
                    <th>Libellé</th>
                    <th>Quantité</th>
                    <th>Prix unitaire</th>
                    <th>Sous-total</th>
                </tr>
                <c:forEach var="ligne" items="${devis.lignes}">
                    <tr>
                        <td>${ligne.libelle}</td>
                        <td>${ligne.quantite}</td>
                        <td>${ligne.prixUnitaire}</td>
                        <td>${ligne.sousTotal}</td>
                    </tr>
                </c:forEach>
            </table>
            <p>Montant total : ${devis.montantTotal}</p>
        </c:when>
        <c:otherwise>
            <p>Aucun devis créé pour cette demande.</p>
        </c:otherwise>
    </c:choose>
</div>
<div>
    <a href="${pageContext.request.contextPath}/demandes/${demande.id}/statuts">Voir historique des statuts</a>
    <a href="${pageContext.request.contextPath}/demandes/liste">Retour à la liste</a>
</div>
</body>
</html>
