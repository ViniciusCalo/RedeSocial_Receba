const urlCadastro = 'http://localhost:8088/cadastro'
const urlLogin = 'http://localhost:8088/login'
const urlPost = 'http://localhost:8088/post'
const urlReaction = 'http://localhost:8088/reaction'
const img = "imagens/imgPerfil.jpg"
const email = sessionStorage.getItem('email')



let listaUsers;

document.onreadystatechange = () => {
    if (document.readyState == 'complete') {
        ListaPost();
    }
}

function att(id) {
    $("#lbllike" + id).html(Listalike(id));
    $("#lbldeslike" + id).html(ListaDeslike(id));
}


function Listalike(id) {
    var like = 0;
    $.ajax({
        type: "GET",
        url: urlReaction + "/" + id,
        async: false,
        success: function (res) {
            res.forEach(e => {
                if (e.reacao == 1) {
                    like = like + 1;
                }
            });
        },
        contentType: "application/json",
        dataType: "json",
    });
    return like;
}

function ListaDeslike(id) {
    var deslike = 0;
    $.ajax({
        type: "GET",
        url: urlReaction + "/" + id,
        async: false,
        success: function (res) {

            res.forEach(e => {

                if (e.reacao == 2) {
                    deslike = deslike + 1;
                }
            });

        },
        contentType: "application/json",
        dataType: "json",
    });
    return deslike;
}





function ListaPost() {
    $("#post-list").empty();

    $.getJSON(urlPost, function (data) {

        const divPost = $(`<div class="div-post"> </div>`)
        data.forEach(e => {
            const post = $(`<div class="post"><br> </div>`)
            const nick = $(`<label class="lbl">${e.user.nickName}</label><br>`);
            const status = $(`<label class="lbl">${e.user.status}</label><br><br>`);
            const imagem = $(`<img src="${e.user.picture}" alt="imagem" class="imgperfil"> <br>`);
            const posttxt = $(`<label>${e.post}</label> <br>`);
            const btnGostei = $(`<button onclick="gostei(${e.idPost})"  class="buttonG">gostei</button>`);
            const qtnLike = $(`<label class="lbllike" id="lbllike${e.idPost}">${Listalike(e.idPost)}</label>`);
            const btnNgostei = $(`<button onclick="Naogostei(${e.idPost})"  class="buttonNg">nao gostei</button>`);
            const qtnDeslike = $(`<label class="lbldeslike" id="lbldeslike${e.idPost}">${ListaDeslike(e.idPost)}</label> <br> <hr class="hr">`);
            divPost.append(post.append(imagem, nick, status, posttxt, btnGostei, qtnLike, btnNgostei, qtnDeslike))
        });
        $("#post-list").append(divPost);
        console.log(data)
    });
}


function cadastro() {
    const nick = $("#cadastro-nickname").val();
    const email = $("#cadastro-email").val();
    const senha = $("#cadastro-senha").val();
    const status = $("#cadastro-status").val();

    if (nick != "" && email != "" && senha != "") {
        const body = `{
        "nickName": "${nick}", 
        "email": "${email}", 
        "senha": "${senha}",
        "picture": "${img}",
        "status":"${status}"
                  }`;

        $.ajax({
            type: "POST",
            url: urlCadastro,
            data: body,
            success: (res) => {
                console.log('post done.');
                window.location.replace("http://127.0.0.1:5500/Receba_Front/Receba_Front/Login.html")

            },
            contentType: "application/json",
            dataType: "json",
            error: (res) => {
                alert("Este email ja existe")
            }
        });

    } else {
        alert("Preencha os campos")
    }
}

function login() {
    const email = $("#email-log").val();
    const senha = $("#senha-log").val();

    const body = `{ 
            "senha": "${senha}",
            "email": "${email}"
                      }`;

    if (email != "" && senha != "") {
        $.ajax({
            type: "POST",
            url: urlLogin,
            data: body,
            success: (res) => {

                sessionStorage.setItem('email', email);
                window.location.replace("http://127.0.0.1:5500/Receba_Front/Receba_Front/Post.html")

            },
            error: (res) => {
                alert("senha ou email incorreto!")
                console.log("nao funcionou")
                console.log(email)

            },
            contentType: "application/json",
            dataType: "json"
        });
    } else {
        console.log('nao achei')
    }
}


function post() {
    const post = $("#input-post").val();
    const body = `{      
                "email": "${email}",  
                "post": "${post}"
                          }`;

    if (post != "") {
        $.ajax({
            type: "POST",
            url: urlPost,
            data: body,
            success: (res) => {
                console.log("post done")
                ListaPost()
            },
            error: (res) => {
                console.log("nao funcionou")

            },
            contentType: "application/json",
            dataType: "json"
        });
    } else {
        alert("Faça algum post")
    }
}

function gostei(idPost) {

    const body = `{        
            "post": "${idPost}",
            "email": "${email}",
            "reacao": "${1}"
                      }`;
    $.ajax({
        type: "POST",
        url: urlReaction,
        data: body,
        success: (res) => {
            att(idPost),
                console.log("post done")
        },
        error: (res) => {
            console.log("nao funcionou")

        },
        contentType: "application/json",
        dataType: "json"

    });


}


function Naogostei(idPost) {

    const body = `{        
                "post": "${idPost}",
                "email": "${email}",
                "reacao": "${2}"
                          }`;
    $.ajax({
        type: "POST",
        url: urlReaction,
        data: body,
        success: (res) => {
            att(idPost),
                console.log("post done")
        },
        error: (res) => {
            console.log("nao funcionou")

        },
        contentType: "application/json",
        dataType: "json"

    });


}

function contarReacoes() {
    const body = `{      
                "email": "${email}",  
                "post": "${post}"
                          }`;
    $.ajax({
        type: "POST",
        url: urlPost,
        data: body,
        success: (res) => {
            console.log("post done")
            ListaPost()
        },
        error: (res) => {
            console.log("nao funcionou")

        },
        contentType: "application/json",
        dataType: "json"

    });

}