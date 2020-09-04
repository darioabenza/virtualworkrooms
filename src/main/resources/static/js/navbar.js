$(document).ready(function(){
    var usuario = JSON.parse(localStorage.getItem("usuario"))
    var autenticado = usuario !=null

    if(autenticado){
        var loginTimestamp = new Date(JSON.parse(window.localStorage.getItem("loginTimestamp")))
        if( (Date.now() - loginTimestamp) < 1000*60*60*10)
        {
            $("#login").remove()
            $("#registro").remove()
            let horasTrabajadas = Math.floor(usuario.tiempoTrabajado/60/60/1000)
            $("#usuario-nav")
                .append("<img class=\"border rounded-circle img-profile\"src=\""+usuario.avatar+"\"></img>\
                <strong>"+usuario.nombre+"</strong> "+horasTrabajadas+"h\
                <button id=\"logout\" class=\"btn action-button\" role=\"button\"\">Cerrar sesión</button>")
        } else{
            window.localStorage.setItem("usuario",null)
            window.localStorage.setItem("jwt", null)
        }
    }
    $("#logout").click(function(){
        window.localStorage.setItem("usuario",null)
        window.localStorage.setItem("jwt", null)
        window.location.href="index.html"
    })
})