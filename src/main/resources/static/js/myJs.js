// function validarFecha() {
//     var fechaNacimiento = new Date(document.getElementById("fechaNacimiento").value);
//     var fechaActual = new Date();
//
//     if (fechaNacimiento > fechaActual) {
//         document.getElementById("errorFecha").innerText = "La fecha no puede ser posterior a la fecha actual.";
//         document.getElementById("fechaNacimiento").value = "";
//     } else {
//         document.getElementById("errorFecha").innerText = "";
//     }
// }

function validarFecha() {
    var inputFecha = document.getElementById('fechaNacimiento');
    var fechaSeleccionada = new Date(inputFecha.value);
    var fechaActual = new Date();

    // Obtener la fecha actual sin la hora (para comparación de día)
    var hoy = new Date();
    hoy.setHours(0, 0, 0, 0);

    if (fechaSeleccionada > hoy) {
        document.getElementById('errorFecha').textContent = 'La fecha no puede ser posterior a la fecha actual';
        inputFecha.value = ''; // Limpiar el campo
    } else {
        document.getElementById('errorFecha').textContent = '';
    }
}