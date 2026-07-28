//FUNÇÃO DE CADASTRO---------------------------
function botaoCadastra() {

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

            const mensagem = await response.text();
            throw new Error(mensagem);

        }

        return response.json();

    })
    .then(cliente => {

        alert("Usuário cadastrado com sucesso!");
        window.location.href = "login.html";

    })
    .catch(error => {

        alert(error.message);

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