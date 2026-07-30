//LIMPA ERROP
function limparErros() {

    document.getElementById("erroNome").textContent = "";
    document.getElementById("erroCpf").textContent = "";
    document.getElementById("erroEmail").textContent = "";
    document.getElementById("erroSenha").textContent = "";

    document.getElementById("campoNome").classList.remove("erro-input");
    document.getElementById("campoCpf").classList.remove("erro-input");
    document.getElementById("campoEmail").classList.remove("erro-input");
    document.getElementById("campoSenha").classList.remove("erro-input");
}



//FUNÇÃO DE CADASTRO---------------------------
function botaoCadastra() {
    
    limparErros();

    const campoNome = document.getElementById("campoNome").value;
    const campoCpf = document.getElementById("campoCpf").value;
    const campoEmail = document.getElementById("campoEmail").value;
    const campoSenha = document.getElementById("campoSenha").value;

    if (
        campoNome === "" ||
        campoCpf === "" ||
        campoEmail === "" ||
        campoSenha === ""
    ) {
        alert("Prencha todos os campos!");
        return;
    }

    const packCadastroJson = {
        nomeCompleto: campoNome,
        cpf: campoCpf,
        email: campoEmail,
        senha: campoSenha
    };

    fetch("http://localhost:4040/apilogin/cadastrar", {

    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify(packCadastroJson)

})
        .then(async response => {

        if (!response.ok) {

            const erros = await response.json(); // ← Agora recebe um JSON
            throw erros;

        }

        return response.json();

    })
    .then(cliente => {

        alert("Usuário cadastrado com sucesso!");
        window.location.href = "login.html";

    })
    
    

   .catch(erros => {

                console.log(erros);
    limparErros();

    if (erros.nome) {
        document.getElementById("erroNome").textContent = erros.nome;
        document.getElementById("campoNome").classList.add("erro-input");
    }

    if (erros.cpf) {
        document.getElementById("erroCpf").textContent = erros.cpf;
        document.getElementById("campoCpf").classList.add("erro-input");
    }

    if (erros.email) {
        document.getElementById("erroEmail").textContent = erros.email;
        document.getElementById("campoEmail").classList.add("erro-input");
    }

    if (erros.senha) {
        document.getElementById("erroSenha").textContent = erros.senha;
        document.getElementById("campoSenha").classList.add("erro-input");
    }

});




}


//FUNÇÃO DE LOGIN ------------------------------------------
function botaoLogin() {

    const campoLogin = document.getElementById("campoLogin").value;
    const senha = document.getElementById("senhaLogin").value;

    const loginJson = {
        login: campoLogin,
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

document.getElementById("campoNome").addEventListener("input", function () {

    this.classList.remove("erro-input");
    document.getElementById("erroNome").textContent = "";

});

document.getElementById("campoCpf").addEventListener("input", function () {

    this.classList.remove("erro-input");
    document.getElementById("erroCpf").textContent = "";

});

document.getElementById("campoEmail").addEventListener("input", function () {

    this.classList.remove("erro-input");
    document.getElementById("erroEmail").textContent = "";

});

document.getElementById("campoSenha").addEventListener("input", function () {

    this.classList.remove("erro-input");
    document.getElementById("erroSenha").textContent = "";
});

["Nome", "Cpf", "Email", "Senha"].forEach(campo => {

    document.getElementById("campo" + campo).addEventListener("input", function () {

        this.classList.remove("erro-input");
        document.getElementById("erro" + campo).textContent = "";

    });

});

