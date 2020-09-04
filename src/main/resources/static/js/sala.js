var str = window.location.search.substr(1)
var parametros = str.split("&")
var categoria = parametros[0].split("=")[1]
var id = parametros[1].split("=")[1]
var nombreSala = parametros[2].split("=")[1]
var timer = new Date()
var timestamp1 = timer.getTime()
var usuario = window.localStorage.getItem("usuario")
var jwt = window.localStorage.getItem("jwt")
var salaObj

function manejarProhibido(){
    window.localStorage.setItem("usuario", null)
    window.localStorage.setItem("jwt", null)
    window.location.href = "/login.html"
}

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
        beforeSend: function(request){
            request.setRequestHeader("Authorization", "Bearer "+jwt)
        },
        error: function(xhr, status, error){
            if(xhr.status == 403)
                manejarProhibido()
        },
        success: function(data, status, xhr){
            $("textarea").val("")
            getSala()
        }
    })
}

async function entrarSala(){
    let url = "/categorias/"+categoria+"/salas/"+id+"/participantes"
    return new Promise(function(resolve, reject){
        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(usuario),
            contentType: "application/json",
            beforeSend: function(request){
                request.setRequestHeader("Authorization", "Bearer "+jwt)
            },
            error: function(xhr, status, error){
                if(xhr.status == 403)
                    manejarProhibido()
                reject(error)
            },
            success: function(){
                resolve(salaObj)
            }
        })
    })
}

async function getSala(){
    let url = "/categorias/"+categoria+"/salas/"+id
    return new Promise(function(resolve, reject){
        $.ajax({
            type: "GET",
            url: url,
            beforeSend: function(request){
                request.setRequestHeader("Authorization", "Bearer "+jwt)
            },
            error: function(xhr, status, error){
                if(xhr.status == 403)
                    manejarProhibido()
                reject(error)
            },
            success: function(data, status, xhr){
                salaObj = data
                resolve(salaObj)
            }
        })
        
    })
    
}

function actualizarVista(){
    if(salaObj.mensajes != null){
        for(let msj of salaObj.mensajes){
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
    }
    if(salaObj.participantes != null){
        for(let p of salaObj.participantes){
        $("#participantesLista").append(
            "<li>\
                <div class=\"avatar-marco\">\
                        <img class=\"avatar-img\" src=\"logo.png\"/>\
                </div>\
                <span>"+p.nombre+"</span>\
            </li>")
        }
    }
    
}

function salir(){
    timer = new Date()
    let timestamp2 = timer.getTime()
    let ttotal = timestamp2 - timestamp1
    let tsegundos = ttotal/1000
    let segundos = Math.floor((tsegundos%60))
    let tminutos = segundos/60
    let minutos = Math.floor((tminutos%60))
    let horas = Math.floor((tminutos/60))
    //TODO
    //enviar update user
    //enviar delete participante
    let url = "/categorias/"+categoria+"/salas/"+id+"/participantes/"+usuario.id
    $.ajax({
        type: "DELETE",
        url: url,
        beforeSend: function(request){
            request.setRequestHeader("Authorization", "Bearer "+jwt)
        },
        error: function(xhr, status, error){
            if(xhr.status == 403)
                manejarProhibido()
        },
        success: function(){
            alert("Has salido de la sala. Has trabajado un tiempo total de: "+horas+" horas, "+minutos+" minutos, "+segundos+" segundos.")
            window.location.href="index.html"
        }
    })
    
}

$(document).ready(function(){
    $("h1").append(categoria)
    $("h2").append(nombreSala)
    $("#enviarMensaje").click(enviarMensaje)
    $("textarea").keypress(function(){
        if(event.keyCode === 13)
            enviarMensaje()
    })
    $("#salir-boton").click(salir)
    window.onbeforeunload = salir
    entrarSala().then(() => {
        getSala().then(() => {
            actualizarVista()
        })
    })
    
})