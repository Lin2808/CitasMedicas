// Seleccionar el modal por su ID y almacenarlo en una variable
var miModal = new bootstrap.Modal(document.getElementById('editarCamposModal'));
var miModal2 = new bootstrap.Modal(document.getElementById('modalExamen'));

// Función para cargar datos en el modal y mostrarlo
function cargarDatosModal(diagnostico, tratamiento, comentario, consultaIdEdit) {
    // Obtener referencias a los campos de entrada del modal
    var diagnosticoInput = document.getElementById('diagnostico');
    var tratamientoInput = document.getElementById('tratamiento');
    var comentarioInput = document.getElementById('comentarioedit');
    var consultaIdInput = document.getElementById('consultaIdEdit');

    // Asignar valores a los campos de entrada del modal
    diagnosticoInput.value = diagnostico || '';
    tratamientoInput.value = tratamiento || '';
    comentarioInput.value = comentario || '';
    consultaIdInput.value = consultaIdEdit || '';

    // Mostrar el modal
    miModal.show();
}

// Evento que se dispara al cerrar el modal
miModal.addEventListener('hide.bs.modal', function () {
    // Limpiar los campos de entrada al cerrar el modal
    var diagnosticoInput = document.getElementById('diagnostico');
    var tratamientoInput = document.getElementById('tratamiento');
    var comentarioInput = document.getElementById('comentarioedit');
    var consultaIdInput = document.getElementById('consultaIdEdit');

    diagnosticoInput.value = '';
    tratamientoInput.value = '';
    comentarioInput.value = '';
    consultaIdInput.value = '';
});

document.getElementById('guardarBtn').addEventListener('click', function () {
    // Obtener los valores de los campos de entrada del modal
    var consultaId = document.getElementById('consultaIdEdit').value;
    var diagnostico = document.getElementById('diagnostico').value;
    var tratamiento = document.getElementById('tratamiento').value;
    var comentario = document.getElementById('comentarioedit').value;

    // Crear un objeto FormData y agregar los datos
    var formData = new FormData();
    formData.append('consultaId', consultaId);
    formData.append('diagnostico', diagnostico);
    formData.append('tratamiento', tratamiento);
    formData.append('comentario', comentario);

    // Realizar una solicitud AJAX para enviar los datos al controlador Spring
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/historias_clinicas/actualizarConsulta', true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                // Recargar la página después de actualizar la consulta
                window.location.reload();
            } else {
                // Manejar el caso de error aquí
                console.error('Error en la solicitud AJAX:', xhr.statusText);
            }
        }
    };

    // Enviar la solicitud con el objeto FormData
    xhr.send(formData);
});

function cargarArchivoModal(consultaId) {
    var consultaIdInput = document.getElementById('consultaIdSubirExamen');
    consultaIdInput.value = consultaId || '';
    miModal2.show();
}

miModal2.addEventListener('hide.bs.modal', function () {
    var consultaIdInput = document.getElementById('consultaIdSubirExamen');
    consultaIdInput.value = '';
});

var miModal2 = new bootstrap.Modal(document.getElementById('modalExamen'));

document.getElementById('subirExamen').addEventListener('submit', function (event) {
    event.preventDefault();

    var consultaId = document.getElementById('consultaIdSubirExamen').value;
    var fileInput = document.getElementById('file');

    var formData = new FormData();
    formData.append('consultaId', consultaId);
    formData.append('file', fileInput.files[0]);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/historias_clinicas/subirExamen', true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.reload();
            } else {
                console.error('Error en la solicitud AJAX:', xhr.statusText);
            }
        }
    };
    xhr.send(formData);
});

