<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Nouvelle demande</title>
</head>
<body>
<h1>Nouvelle demande de forage</h1>
<form action="${pageContext.request.contextPath}/demandes/nouvelle" method="post">
    <div>
        <label>Nom demandeur :</label>
        <input type="text" name="nomDemandeur" value="${demande.nomDemandeur}" required />
    </div>
    <div>
        <label>Lieu du forage :</label>
        <input type="text" name="lieuForage" value="${demande.lieuForage}" required />
    </div>
    <div>
        <label>Fokontany :</label>
        <input type="text" name="fokontany" value="${demande.fokontany}" required />
    </div>
    <div>
        <label>District :</label>
        <input type="text" name="district" value="${demande.district}" required />
    </div>
    <div>
        <label>Région :</label>
        <input type="text" name="region" value="${demande.region}" required />
    </div>
    <div>
        <button type="submit">Enregistrer la demande</button>
        <a href="${pageContext.request.contextPath}/demandes/liste">Annuler</a>
    </div>
</form>
</body>
</html>
