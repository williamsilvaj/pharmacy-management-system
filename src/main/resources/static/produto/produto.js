document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("produtoForm");
    const mensagem = document.getElementById("mensagem");

    form.addEventListener("submit", async function (event) {
        event.preventDefault(); // Evita recarregar a página

        const produto = {
            nome: document.getElementById("nome").value,
            codigo: document.getElementById("codigo").value,
            fabricante: document.getElementById("fabricante").value,
            concentracao: document.getElementById("concentracao").value
        };

        try {
            const resposta = await fetch("/produtos", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(produto)
            });

            if (resposta.ok) {
                mensagem.textContent = "Produto criado com sucesso!";
                mensagem.style.color = "green";
                form.reset();
            } else {
                mensagem.textContent = "Erro ao criar produto!";
                mensagem.style.color = "red";
            }
        } catch (erro) {
            console.error("Erro:", erro);
            mensagem.textContent = "Erro ao conectar com o servidor.";
            mensagem.style.color = "red";
        }
    });
});
