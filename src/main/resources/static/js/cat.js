var str = window.location.search.substr(1).split("=")
var categoria = str[1]

function getSalas(){
    let url = "/categorias/"+categoria+"/salas"
    $.getJSON(url, function(data){
        let lista = $("table.table")
        for(let sala of data){
            //la sala General debería estar siempre al principio
            if(sala.nombre == "General"){
                $("#salaGeneral").append("<a href=\"/sala.html?categoria="+categoria+"&id="+sala.id+"&nombre="+sala.nombre+"\">\
                <div class=\"card\">\
                <p>"+sala.nombre+"</p></div>")
                continue
            }
            lista.append("<tr><td>\
                <a href=\"/sala.html?categoria="+categoria+"&id="+sala.id+"&nombre="+sala.nombre+"\">\
                <div class=\"card\">\
                <p>"+sala.nombre+"</p>\
                </div></td></tr>")
        }
    })
}

//crea la sala y el usuario entra automáticamente
function crearSala(){
    let url="/categorias/"+categoria+"/salas"
    let nombre=$("#nombre").val()
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify({
            nombre: nombre
        }),
        beforeSend: function(request){
            request.setRequestHeader("Authorization", "Bearer "+window.localStorage.getItem("jwt"))
        },
        success: function(data, status, xhr){
            if(status=="success"){
                let location = xhr.getResponseHeader("Location")
                let id = location.split("/").pop()
                console.log(id)
                window.location.href="sala.html?categoria="+categoria+"&id="+id+"&nombre="+nombre
            }
            
        },
        contentType: "application/json",
    })
}

$(document).ready(function(){
    $("h1").append(decodeURIComponent(categoria))
    getSalas()
    $("#botonCrearSala").click(crearSala)
})