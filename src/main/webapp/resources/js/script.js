// Script JavaScript de base pour Projet_forage
function ajouterLigneDevis() {
    const table = document.getElementById('details-table');
    const row = table.insertRow(-1);

    const libelleCell = row.insertCell(0);
    const quantiteCell = row.insertCell(1);
    const prixCell = row.insertCell(2);

    libelleCell.innerHTML = '<input type="text" name="libelle" />';
    quantiteCell.innerHTML = '<input type="number" name="quantite" min="1" />';
    prixCell.innerHTML = '<input type="number" name="prixUnitaire" step="0.01" min="0" />';
}
