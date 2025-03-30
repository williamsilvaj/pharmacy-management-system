document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("enderecoForm");
  const messageDiv = document.getElementById("message");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const endereco = {
      rua: document.getElementById("rua").value,
      numero: document.getElementById("numero").value,
      bairro: document.getElementById("bairro").value,
      cidade: document.getElementById("cidade").value,
      estado: document.getElementById("estado").value,
      cep: document.getElementById("cep").value
    };

    try {
      const response = await fetch("/enderecos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(endereco)
      });
      if (response.status === 201) {
        messageDiv.textContent = "Endereço cadastrado com sucesso!";
        messageDiv.style.color = "green";
        form.reset();
      } else {
        messageDiv.textContent = "Erro ao cadastrar endereço!";
        messageDiv.style.color = "red";
      }
    } catch (error) {
      console.error("Erro:", error);
      messageDiv.textContent = "Erro de conexão com o servidor.";
      messageDiv.style.color = "red";
    }
  });
});
