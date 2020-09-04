var str = window.location.search.substr(1)
var parametros = str.split("&")
var salaObj = {
    id: parametros[1].split("=")[1],
    nombre: parametros[2].split("=")[1],
    categoriaNombre: parametros[0].split("=")[1],
    mensajes: [],
    participantes: []
}
var timestamp1 = new Date().getTime()
var usuario = window.localStorage.getItem("usuario")
var jwt = window.localStorage.getItem("jwt")


function manejarProhibido(){
    window.localStorage.setItem("usuario", null)
    window.localStorage.setItem("jwt", null)
    window.location.href = "/login.html"
}

function enviarMensaje(){
    let texto = $("textarea").val()
    let url = "/categorias/"+salaObj.categoriaNombre+"/salas/"+salaObj.id+"/mensajes"
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
            getSala().then(actualizarVista)

        }
    })
}

async function entrarSala(){
    let url = "/categorias/"+salaObj.categoriaNombre+"/salas/"+salaObj.id+"/participantes"
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
    let url = "/categorias/"+salaObj.categoriaNombre+"/salas/"+salaObj.id
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
                resolve(data)
            }
        })
        
    })
    
}

function actualizarVista(data){
    if(data.mensajes.length != salaObj.mensajes.length){
        $("#mensajesLista").empty()
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
    }
    if(data.participantes.length != salaObj.participantes.length){
        $("#participantesLista").empty()
        for(let p of data.participantes){
        $("#participantesLista").append(
            "<li>\
                <div class=\"avatar-marco\">\
                        <img class=\"avatar-img\" src=\"logo.png\"/>\
                </div>\
                <span>"+p.nombre+"</span>\
            </li>")
        }
    }
    salaObj = data
}

function salir(){
    let timestamp2 = new Date().getTime()
    let ttotal = timestamp2 - timestamp1
    let tsegundos = ttotal/1000
    let segundos = Math.floor((tsegundos%60))
    let tminutos = segundos/60
    let minutos = Math.floor((tminutos%60))
    let horas = Math.floor((tminutos/60))
    //enviar update user
    usuario.tiempoTrabajado +=ttotal
    let url = "/usuarios/"+usuario.id
    $.ajax({
        type: "PUT",
        url: url,
        data: usuario,
        contentType: "application/json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", "Bearer "+jwt)
        },
        error: function(xhr, status, error){
            if(xhr.status == 403)
                manejarProhibido()
        }
    })
    //enviar delete participante
    url = "/categorias/"+salaObj.categoriaNombre+"/salas/"+salaObj.id+"/participantes/"+usuario.id
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
    $("h1").append(salaObj.categoriaNombre)
    $("h2").append(salaObj.nombre)
    $("#enviarMensaje").click(enviarMensaje)
    $("textarea").keypress(function(){
        if(event.keyCode === 13)
            enviarMensaje()
    })
    $("#salir-boton").click(salir)
    window.onbeforeunload = salir
    entrarSala().then(() => {
        getSala().then(actualizarVista)
    })
    
    setInterval(()=>{
        getSala().then(actualizarVista)
    }, 5000)
})