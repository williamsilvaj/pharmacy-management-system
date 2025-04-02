document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("fornecedorModal");
  const openModal = document.getElementById("addFornecedorBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("fornecedorForm");
  const tableBody = document.getElementById("fornecedorTableBody");

  // Função para exibir fornecedores na tabela
  async function carregarFornecedores() {
    const response = await fetch("/fornecedores");
    const fornecedores = await response.json();
    console.log(fornecedores)

    tableBody.innerHTML = ""; // Limpar tabela antes de atualizar
    fornecedores.forEach(fornecedor => {
      const row = document.createElement("tr");
      row.innerHTML = `
                <td>${fornecedor.nome}</td>
                <td>${fornecedor.cnpj}</td>
                <td>${fornecedor.email}</td>
                <td>${fornecedor.telefone}</td>
                <td>${fornecedor.endereco.rua}, ${fornecedor.endereco.numero},
${fornecedor.endereco.bairro}, ${fornecedor.endereco.cidade}, ${fornecedor.endereco.estado}, ${fornecedor.endereco.cep}</td>
                <td>
                    <button onclick="editarFornecedor(${fornecedor.idFornecedor})">Editar</button>
                    <button onclick="deletarFornecedor(${fornecedor.idFornecedor})">Excluir</button>
                </td>
            `;
      tableBody.appendChild(row);
    });

  }

  // Exibir o modal
  openModal.addEventListener("click", () => {
    modal.style.display = "flex";
    resetForm(); // Limpar formulário quando o modal é aberto
  });

  // Fechar o modal
  closeModal.addEventListener("click", () => {
    modal.style.display = "none";
  });

  // Lógica de envio do formulário
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    // Obtendo os valores dos campos do formulário
    const fornecedor = {
      nome: document.getElementById("fornecedorNome").value,
      cnpj: document.getElementById("fornecedorCnpj").value,
      email: document.getElementById("fornecedorEmail").value,
      telefone: document.getElementById("fornecedorTelefone").value,
      endereco: {
        rua: document.getElementById("rua").value,
        numero: document.getElementById("numero").value,
        bairro: document.getElementById("bairro").value,
        cidade: document.getElementById("cidade").value,
        estado: document.getElementById("estado").value,
        cep: document.getElementById("cep").value,
      },
    };

    let fornecedorFinal = fornecedor

    console.log(fornecedorFinal);

    if (fornecedorId) {
      const response = await fetch(`/fornecedores/${fornecedores}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(fornecedorFinal),
      });

      if (response.ok) {
        modal.style.display = "none";
        document.getElementById("fornecedorId").value = "";
        carregarFornecedores();
      } else {
        alert("Erro ao atualizar fornecedor");
      }
    } else {
      // Enviando os dados para o backend
      const response = await fetch("/fornecedores", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(fornecedorFinal),
      });

      if (response.ok) {
        modal.style.display = "none";
        document.getElementById("fornecedorId").value = "";
        carregarFornecedores(); // Atualiza a tabela sem precisar recarregar a página
      } else {
        alert("Erro ao cadastrar fornecedor");
      }
    }
  });

  // Função para excluir fornecedor
  window.deletarFornecedor = async (id) => {
    const response = await fetch(`/fornecedores/${id}`, {method: "DELETE"});
    if (response.ok) {
      document.getElementById("fornecedorId").value = "";
      await carregarFornecedores();
    }
  };

    // Função para editar fornecedor
  window.editarFornecedor = async (id) => {
    const response = await fetch(`/fornecedores/${id}`);
    const fornecedor = await response.json();

    document.getElementById("fornecedorNome").value = fornecedor.nome;
    document.getElementById("fornecedorCnpj").value = fornecedor.cnpj;
    document.getElementById("fornecedorEmail").value = fornecedor.email;
    document.getElementById("fornecedorTelefone").value = fornecedor.telefone;
    document.getElementById("rua").value = fornecedor.endereco.rua;
    document.getElementById("numero").value = fornecedor.endereco.numero;
    document.getElementById("bairro").value = fornecedor.endereco.bairro;
    document.getElementById("cidade").value = fornecedor.endereco.cidade;
    document.getElementById("estado").value = fornecedor.endereco.estado;
    document.getElementById("cep").value = fornecedor.endereco.cep;

    // Exibe o modal
    modal.style.display = "flex";

     form.onsubmit = async (e) => {
       e.preventDefault();

       // Atualiza os dados do fornecedor conforme os novos valores do formulário
       const updatedFornecedor = {
         nome: document.getElementById("fornecedorNome").value,
         cnpj: document.getElementById("fornecedorCnpj").value,
         email: document.getElementById("fornecedorEmail").value,
         telefone: document.getElementById("fornecedorTelefone").value,
         endereco: {
           rua: document.getElementById("rua").value,
           numero: document.getElementById("numero").value,
           bairro: document.getElementById("bairro").value,
           cidade: document.getElementById("cidade").value,
           estado: document.getElementById("estado").value,
           cep: document.getElementById("cep").value
         }
       };

       // Endpoint e método de envio
       await fetch(`/fornecedores/${id}`, {
         method: "PUT",
         headers: {"Content-Type": "application/json"},
         body: JSON.stringify(updatedFornecedor),
       });

    modal.style.display = "none";
    carregarFornecedores(); // Atualiza a tabela sem precisar recarregar a página
    };
  };

    // Função para resetar o formulário
    function resetForm() {
      form.reset();
    }

    carregarFornecedores(); // Carrega os fornecedores ao iniciar a página
  });
