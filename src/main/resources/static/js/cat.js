var str = window.location.search.substr(1).split("=")
var categoria = str[1]

function getSalas(){
    let url = "/categorias/"+categoria+"/salas"
    $.getJSON(url, function(data){
        let lista = $("table.table")
        for(let sala of data){
            lista.append("<tr><td>\
                    <a href=\"/sala.html?categoria="+categoria+"&id="+sala.id+"&nombre="+sala.nombre+"\">\
                    <div class=\"card\">\
                    <p>"+sala.nombre+"</p>\
                    </div></td></tr>")
    }
    })
}

$(document).ready(function(){
    $("h1").append(categoria)
    getSalas()
})