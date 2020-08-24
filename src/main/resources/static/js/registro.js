function registrar(){
    var nombre = $("#nombre").val()
    var email = $("#email").val()
    var password = $("#contraseña").val()
    $.ajax({
        type: "POST",
        url: "/usuarios",
        data: {
            nombre: nombre,
            email: email,
            password: password,
        },
        success: function (response){
            if (response.status = "400"){
                $("p.text-danger").html(response.body)
            }
            else if (response.status = "200"){

            }
        },
        dataType: "json",
    })
}


var emailreg = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
var passwreg = /^.*[0-9]+.*$/

function validar(){
    if($("#nombre").val() == ""){
        $("#nombre").tooltip("show")
        $("#nombre").css("border-color","red")
        $("#nombre").focus()
        return false
    }
    $("#nombre").css("border-color","")
    if($("#email").val() == ""
    || !emailreg.test($("#email").val())){
        $("#email").tooltip("show")
        $("#email").css("border-color","red")
        $("#email").focus()
       return false
    }
    $("#email").css("border-color","")
    if($("#contraseña").val().length < 6
    || !passwreg.test($("#contraseña").val())){
        $("#contraseña").tooltip("show")
        $("#contraseña").css("border-color","red")
        $("#contraseña").focus()
        return false
    }
    $("#contraseña").css("border-color","")
    if($("#confirmarContraseña").val() != $("#contraseña").val()){
        $("#confirmarContraseña").tooltip("show")
        $("#confirmarContraseña").css("border-color","red")
        $("#confirmarContraseña").focus()
        return false
    }
    return true
      
}

$(document).ready(function() {
    $("#boton-registrar").click(function(){
        if(validar()){
            registrar()
        }
    })
    $("#confirmarContraseña").keypress(function(){
        if(event.keyCode === 13){
            if (validar()){
                registrar()
            }
        }
    })
})