var str = window.location.search.substr(1)
var parametros = str.split("&")
var categoria = parametros[0].split("=")[1]
var id = parametros[1].split("=")[1]
var nombreSala = parametros[2].split("=")[1]

$(document).ready(function(){
    $("h1").append(categoria)
    $("h2").append(nombreSala)
    $("#salir-boton").click(function(){
        //enviar salir sala
        window.location.href="index.html"
    })
})