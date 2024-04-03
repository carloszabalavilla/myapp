document.addEventListener("DOMContentLoaded", function () {
    // Hacer una solicitud HTTP para obtener los datos de los artistas
    fetch("/artists")
        .then(response => response.json())
        .then(data => {
            // Renderizar los datos en la tabla
            const artistsTableBody = document.getElementById("artistsTableBody");
            data.forEach(artist => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${artist.id}</td>
                    <td>${artist.name}</td>
                    <td>${artist.description}</td>
                    <td>${artist.category}</td>
                    <td>${artist.years}</td>
                `;
                artistsTableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error al obtener los artistas:", error));
});