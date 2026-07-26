
function botaoCadastra() {


    const campoNome = document.getElementById("campoNome").value;
    const campoCpf = document.getElementById("campoCpf").value;
    const campoSenha = document.getElementById("campoSenha").value;

        if (campoNome === "" || campoCpf === "" || campoSenha === "" ) {
                alert(" Prencha todos os campos! ");
        return;  
        }
    
    const packCadastroJson = {
        nomeCompleto: campoNome,
        cpf: campoCpf,
        senha: campoSenha
    }

        fetch("http://localhost:4040/apilogin/cadastrar", {

            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },

                body: JSON.stringify(packCadastroJson)

    })

}


function botaoLogin() {



    const cpf = document.getElementById("cpfLogin").value;
    const senha = document.getElementById("senhaLogin").value;

    const loginJson = {
        cpf: cpf,
        senha: senha
    };

    fetch("http://localhost:4040/apilogin/login", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify(loginJson)
})

.then(response => {

    if (!response.ok) {
        throw new Error("CPF ou senha inválidos.");
    }

    return response.json();
})
.then(cliente => {

    alert("Login realizado com sucesso!");
    window.location.href = "painel.html";

})
.catch(error => {

    alert(error.message);

});

}
