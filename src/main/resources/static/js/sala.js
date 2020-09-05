var str = window.location.search.substr(1)
var parametros = str.split("&")
var urlEncoded = {
    id : parametros[1].split("=")[1],
    nombre : parametros[2].split("=")[1],
    categoriaNombre : parametros[0].split("=")[1]
}
var salaObj = {
    id: decodeURIComponent(urlEncoded.id),
    nombre: decodeURIComponent(urlEncoded.nombre),
    categoriaNombre: decodeURIComponent(urlEncoded.categoriaNombre),
    mensajes: [],
    participantes: []
}
var timestamp1 = new Date().getTime()
var usuario = JSON.parse(window.localStorage.getItem("usuario"))
var jwt = window.localStorage.getItem("jwt")


function manejarProhibido(){
    window.localStorage.setItem("usuario", null)
    window.localStorage.setItem("jwt", null)
    window.location.href = "/login.html"
}

function enviarMensaje(){
    let mensaje = {
        texto: $("textarea").val(),
        autor: usuario
    }
    let url = "/categorias/"+urlEncoded.categoriaNombre+"/salas/"+urlEncoded.id+"/mensajes"
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(mensaje),
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
    let url = "/categorias/"+urlEncoded.categoriaNombre+"/salas/"+urlEncoded.id+"/participantes"
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
    let url = "/categorias/"+urlEncoded.categoriaNombre+"/salas/"+urlEncoded.id
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
    console.log(data.mensajes!=null)
    if(data.mensajes!=null && data.mensajes.length != salaObj.mensajes.length){
        console.log("ayy")
        $("#mensajesLista").empty()
        for(let msj of data.mensajes){
        $("#mensajesLista").append(
            "<li>\
                <div class=\"header\">\
                    <div class=\"avatar-marco\">\
                        <img class=\"avatar-img\" src="+msj.autor.avatar+"/>\
                    </div>\
                    <span><strong>"+msj.autor.nombre+"</strong></span>\
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
                        <img class=\"avatar-img\" src="+p.avatar+"/>\
                </div>\
                <span>"+p.nombre+"</span>\
            </li>")
        }
    }
    salaObj = data
}

function updateUsuario(){
    let timestamp2 = new Date().getTime()
    let ttotal = timestamp2 - timestamp1

    usuario.tiempoTrabajado +=ttotal
    let url = "/usuarios/"+usuario.id
    $.ajax({
        type: "PUT",
        url: url,
        data: JSON.stringify(usuario),
        contentType: "application/json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", "Bearer "+jwt)
        },
        error: function(xhr, status, error){
            if(xhr.status == 403)
                manejarProhibido()
        }
    })
}

function mostrarModal(){
    updateUsuario()
    let timestamp2 = new Date().getTime()
    let ttotal = timestamp2 - timestamp1
    let tsegundos = ttotal/1000
    let segundos = Math.floor((tsegundos%60))
    let tminutos = segundos/60
    let minutos = Math.floor((tminutos%60))
    let horas = Math.floor((tminutos/60))
    $(".modal-body").empty()
    $(".modal-body")
        .append("¿Seguro que quieres salir de la sala? Has trabajado un tiempo total de: "+horas+" horas, "+minutos+" minutos, "+segundos+" segundos.")
    $("#modalSalir").modal("show")
}

function salir(){
    //enviar delete participante
    let url = "/categorias/"+urlEncoded.categoriaNombre+"/salas/"+urlEncoded.id+"/participantes/"+usuario.id
    console.log(url)
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
        success: function(xhr, status){
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
    $("#salir-boton").click(mostrarModal)
    $("#confirmarSalir").click(salir)
    entrarSala().then(() => {
        getSala().then(actualizarVista)
    })
    
    setInterval(()=>{
        updateUsuario()
        getSala().then(actualizarVista)
    }, 5000)
})