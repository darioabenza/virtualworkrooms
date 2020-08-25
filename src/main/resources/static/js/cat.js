var str = window.location.search.substr(1).split("=")
var categoria = str[1]
console.log(categoria)

var salas7 = [categoria, "asdlkhasdlf", "ahAHkljhklajshdf", "ds", "A", "añlds923", "añsldjgh"]
var salas1= [categoria]
var salas20 = [categoria, "asdlkhasdlf", "ahAHkljhklajshdf", "ds", "A", "añlds923", "añsldjgh",
                "alñsdhfla", "asdlkf", "asdlfjasñls", "añlsdkhglsañ", "añlsdijfñl", "alñdafsd",
                "añlsdkfj", "ñalksdjf", "añlsdkj", "asdfads", "añdslk"]

$(document).ready(function(){
    $("h1").append(categoria)
    let lista = $("table.table")
    for(let sala of salas20){
        lista
            .append("<tr><td>\
                <a href=\"/sala.html?nombre=sala1\">\
                <div class=\"card\">\
                <p>"+sala+"</p>\
                </div></td></tr>")
    }
})