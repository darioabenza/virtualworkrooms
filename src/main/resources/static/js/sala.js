var str = window.location.search.substr(1)
var parametros = str.split("&")
var categoria = parametros[0].split("=")[1]
var id = parametros[1].split("=")[1]
var nombreSala = parametros[2].split("=")[1]
var timer = new Date()
var timestamp1 = timer.getTime()

function enviarMensaje(){
    let texto = $("textarea").val()
    let url = "/categorias/"+categoria+"/salas/"+id+"/mensajes"
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify({
            texto: texto
        }),
        contentType: "application/json",
        success: function(data, status, xhr){
            $("textarea").val("")
            getSala()
        }
    })
}

function getSala(){
    let url = "/categorias/"+categoria+"/salas/"+id
    $.getJSON(url, function(data){
        console.log(data)
        for(let msj of data.mensajes){
            $("#mensajesLista").append(
                "<li>\
                    <div class=\"header\">\
                        <div class=\"avatar-marco\">\
                            <img class=\"avatar-img\" src=\"logo.png\"/>\
                        </div>\
                        <span><strong>"+"reemplazar"+"</strong></span>\
                    </div>\
                    <p>"+msj.texto+"</p>\
                </li>")
        }
        for(let p of data.participantes){
            $("#participantesLista").append(
                "<li>\
                    <div class=\"avatar-marco\">\
                            <img class=\"avatar-img\" src=\"logo.png\"/>\
                    </div>\
                    <span>"+p.nombre+"</span>\
                </li>"
            )
        }
    })
}

function salir(){
    timer = new Date()
    let timestamp2 = timer.getTime()
    let ttotal = timestamp2 - timestamp1
    //TODO
    //enviar update user

    let tsegundos = ttotal/1000
    let segundos = Math.floor((tsegundos%60))
    let tminutos = segundos/60
    let minutos = Math.floor((tminutos%60))
    let horas = Math.floor((tminutos/60))
    alert("Vas a salir de la sala. Has trabajado un tiempo total de: "+horas+" horas, "+minutos+" minutos, "+segundos+" segundos.")
}

$(document).ready(function(){
    $("h1").append(categoria)
    $("h2").append(nombreSala)
    getSala()
    $("#enviarMensaje").click(enviarMensaje)
    $("textarea").keypress(function(){
        if(event.keyCode === 13)
            enviarMensaje()
    })
    $("#salir-boton").click(function(){
        salir()
        window.location.href="index.html"
    })
    window.onbeforeunload = salir
})