document.addEventListener("DOMContentLoaded", function () {
    // Hacer una solicitud HTTP para obtener los datos de las canciones
    fetch("/songs")
        .then(response => response.json())
        .then(data => {
            // Renderizar los datos en la tabla
            const songsTableBody = document.getElementById("songsTableBody");
            data.forEach(song => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${song.id}</td>
                    <td>${song.title}</td>
                    <td>${song.category}</td>
                    <td>${song.duration}</td>
                    <td>${song.artist.name}</td>
                `;
                songsTableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error al obtener las canciones:", error));
});
