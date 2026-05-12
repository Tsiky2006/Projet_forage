<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Création du devis</title>
    <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</head>
<body>
<h1>CRÉATION DU DEVIS</h1>
<div>
    <p>Demande liée : ${demande.reference}</p>
    <p>Demandeur : ${demande.nomDemandeur}</p>
    <p>Lieu : ${demande.lieuForage}</p>
    <p>Date demande : ${demande.dateDemande}</p>
</div>
<form action="${pageContext.request.contextPath}/demandes/${demande.id}/devis/nouveau" method="post">
    <div>
        <label>Type devis :</label>
        <select name="typeDevisId" required>
            <c:forEach var="type" items="${typesDevis}">
                <option value="${type.id}">${type.nom}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label>Observation :</label>
        <textarea name="observation" rows="4" cols="50"></textarea>
    </div>
    <h2>Détails du devis</h2>
    <table id="details-table" border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>Libellé</th>
            <th>Quantité</th>
            <th>Prix unitaire</th>
        </tr>
        <tr>
            <td><input type="text" name="libelle" /></td>
            <td><input type="number" name="quantite" min="1" /></td>
            <td><input type="number" name="prixUnitaire" step="0.01" min="0" /></td>
        </tr>
    </table>
    <div>
        <button type="button" onclick="ajouterLigneDevis()">+ Ajouter une ligne</button>
    </div>
    <div>
        <button type="submit">Enregistrer le devis</button>
        <a href="${pageContext.request.contextPath}/demandes/detail/${demande.id}">Annuler</a>
    </div>
</form>
</body>
</html>
