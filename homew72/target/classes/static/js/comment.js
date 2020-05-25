'use strict';

function addComment(id) {
    let form = document.getElementById("form-comment");
    let data = new FormData(form);
    let name = "_csrf"
    let value = document.getElementsByName("_csrf_token")[0].getAttribute("content")
    data.append(name, value);
    data.append("id",id);

    fetch("http://localhost:8000/theme/addComment", {
        method: 'POST',
        body: data
    }).then(r => r.json());
}

class Comment{
    constructor(userLogin, text, ldt, commentId) {
        this.userLogin = userLogin;
        this.text = text;
        this.ldt = ldt;
        this.commentId = commentId;
    }
}

function createComment(comment) {
    let elem = document.createElement(`div`);
    elem.innerHTML = `
    <input type="hidden" class="commentId" value="${comment.commentId}">
    <div class="col-2">
        <p><i class="fas fa-user-circle" style="font-size: 3em; color: black"></i></p>
        <p>${comment.userLogin}</p>
    </div>
    <div class="col-10">
        <p>${comment.ldt}</p>
        <p>${comment.text}</p>
    </div>`;
    let att = document.createAttribute("class");
    att.value = "row";
    elem.setAttributeNode(att);
    return elem;
}

function addComment1(elem) {
    document.getElementById("comments").insertBefore(elem,document.getElementById("comments").firstChild);
}

async function getComments(themeId) {
    let lastCommentId=0;
    let comms = document.getElementsByClassName("commentId");
    if(comms.length>1){
        lastCommentId = comms[1].value;
    }
        let response = await fetch('http://localhost:8000/chat/comment/'+themeId+"/"+lastCommentId).catch(function (error) {

        });
    if (response.ok) { // если HTTP-статус в диапазоне 200-299
        // получаем тело ответа (см. про этот метод ниже)
        let commentJson = await response.json();
        for (let i=0; i<commentJson.length; i++){
            let comm = new Comment(commentJson[i].user.name, commentJson[i].text, commentJson[i].ldt, commentJson[i].id);
            let elem = createComment(comm);
            addComment1(elem);
        }
        console.log("All Messages in this chat")
    } else {
        console.log("No Messages in this chat")
    }
}

let timerId = setInterval(getComments(1),1000);
