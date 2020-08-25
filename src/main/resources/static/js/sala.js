var str = window.location.search.substr(1).split("=")
var nombreSala = str[1]

$(document).ready(function(){
    $("h1").append(nombreSala)
    $("#salir-boton").click(function(){
        //enviar salir sala
        window.location.href="index.html"
    })
})