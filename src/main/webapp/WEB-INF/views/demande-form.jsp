<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Nouvelle demande</title>
</head>
<body>
<h1>Nouvelle demande de forage</h1>
<form action="/demandes/save" method="post">
    <label>Référence demande :</label>
    <input type="text" name="reference" readonly value="DEM-2026-0001" />
    <br />
    <label>Nom demandeur :</label>
    <input type="text" name="nomDemandeur" />
    <br />
    <label>Lieu forage :</label>
    <input type="text" name="lieuForage" />
    <br />
    <button type="submit">Enregistrer</button>
</form>
</body>
</html>
