var autenticado = true

var horas = 450
var avatar = "media/avatar/piratecat.png"
var nombre = "dario"

$(document).ready(function(){
    if(autenticado){
        $("#login").remove()
        $("#registro").remove()
        $("#usuario-nav")
            .append("<img class=\"border rounded-circle img-profile\"src=\""+avatar+"\"></img>\
            <strong>"+nombre+"</strong> "+horas+"h")
    }
})