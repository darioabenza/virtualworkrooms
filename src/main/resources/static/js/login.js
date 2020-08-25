function log() {
    var email = $("#email").val();
    var password = $("#password").val();
    $.ajax({type: "POST",
    url: "/login",
    data: {
        username: email,
        password: password,
    },
    success: function (response){
        if (response.status == "400"){
            $("p.text-danger").html(response.body)
        }
        else if (response.status == "200"){

        }
    },
    dataType: "json",
})
console.log("enviando")
}

var emailreg = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
var passwreg = /^.*[0-9]+.*$/

function validar(){
    if($("#email").val()=="" || !emailreg.test($("#email").val())){
        $("#email").tooltip("show")
        $("#email").css("border-color","red")
        console.log("email no válido")
        return false
    }
    $("#email").css('border-color','')
    if($("#password").val().length < 6 || !passwreg.test($("#password").val())){
        $("#password").tooltip("show")
        $("#password").css("border-color","red")
        console.log("contraseña no válida")
        return false
    }
    $("#password").css('border-color','')
    console.log("campos válidos")
    return true
}

$(document).ready(function(){
    $('#submit').click(function(){
        if(validar()){
            log()
        }
    })
    $('#password').keypress(function(){
        if(event.keyCode === 13){
            if(validar()){
                log()
            }
        }
    })
})