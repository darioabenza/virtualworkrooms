function log() {
    var nombre = $("#nombre").val();
    var password = $("#password").val();
    $.ajax({
        type: "POST",
        url: "/login",
        data: JSON.stringify({
            nombre: nombre,
            password: password,
        }),
        success: 
            function(data, status, xhr){
                if(status=="success"){
                    window.localStorage.setItem("jwt", data.jwt)
                    window.localStorage.setItem("usuario", JSON.stringify(data.usuario))
                    window.location.href="/index.html"
                }
                
        },
        error:
            function(xhr, status, error){
                console.log(xhr.responseText)
                $("p.text-danger").empty()
                $("p.text-danger").append(xhr.responseText)
            },
        contentType: "application/json",
    })
    console.log("enviando")
}

var passwreg = /^.*[0-9]+.*$/

function validar(){
    if($("#nombre").val() == ""){
        $("#nombre").tooltip("show")
        $("#nombre").css("border-color","red")
        $("#nombre").focus()
        return false
    }
    $("#nombre").css("border-color","")
    if($("#password").val().length < 6 || !passwreg.test($("#password").val())){
        $("#password").tooltip("show")
        $("#password").css("border-color","red")
        console.log("contraseña no válida")
        return false
    }
    $("#password").css('border-color','')
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