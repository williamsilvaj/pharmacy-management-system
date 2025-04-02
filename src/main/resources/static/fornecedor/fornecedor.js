document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("fornecedorModal");
  const openModal = document.getElementById("addFornecedorBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("fornecedorForm");
  const tableBody = document.getElementById("fornecedorTableBody");

  async function carregarFornecedores() {
    const response = await fetch("/fornecedores");

    // Verifique se a resposta foi bem-sucedida (status 200)
    if (!response.ok) {
      console.error("Erro ao carregar fornecedores: " + response.status);
      return; // Se falhar, retorna sem tentar processar o JSON
    }

    // Tente processar a resposta como JSON, e trate erros se houver
    try {
      const fornecedores = await response.json();
      console.log(fornecedores);

      tableBody.innerHTML = ""; // Limpar tabela antes de atualizar
      fornecedores.forEach(fornecedor => {
        const row = document.createElement("tr");
        row.innerHTML = `
        <td>${fornecedor.nome}</td>
        <td>${fornecedor.cnpj}</td>
        <td>${fornecedor.email}</td>
        <td>${fornecedor.endereco.rua}, ${fornecedor.endereco.numero},
        ${fornecedor.endereco.bairro}, ${fornecedor.endereco.cidade}, ${fornecedor.endereco.estado}, ${fornecedor.endereco.cep}</td>
        <td>
          <button onclick="editarFornecedor(${fornecedor.idFornecedor})">Editar</button>
          <button onclick="deletarFornecedor(${fornecedor.idFornecedor})">Excluir</button>
        </td>
            `;
        tableBody.appendChild(row);
      });
    } catch (e) {
      console.error("Erro ao processar a resposta como JSON", e);
    }
  }

  // Exibir o modal
  openModal.addEventListener("click", () => {
    modal.style.display = "flex";
    resetForm(); // Limpar formulário quando o modal é aberto
  });

  // Fechar o modal
  closeModal.addEventListener("click", () => {
    modal.style.display = "none";
    document.getElementById("fornecedorId").value = ""; // Limpar o ID
  });

  // Lógica de envio do formulário
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    // Corrigir aqui: use fornecedorId ao invés de id
    const fornecedorId = document.getElementById("fornecedorId").value.trim(); // Corrigido o uso do trim()

    const fornecedor = {
      nome: document.getElementById("fornecedorNome").value,
      cnpj: document.getElementById("fornecedorCnpj").value,
      email: document.getElementById("fornecedorEmail").value,
      endereco: {
        rua: document.getElementById("rua").value,
        numero: document.getElementById("numero").value,
        bairro: document.getElementById("bairro").value,
        cidade: document.getElementById("cidade").value,
        estado: document.getElementById("estado").value,
        cep: document.getElementById("cep").value,
      }
    };

    let fornecedorFinal = fornecedor;
    let method = "POST";
    let url = "/fornecedores";  // A URL de criação de fornecedores

    if (fornecedorId) { // Se fornecedorId existir, usamos PUT para editar
      method = "PUT";
      url = `/fornecedores/${fornecedorId}`; // Certifique-se de que o ID está sendo passado corretamente
    }

    console.log(fornecedorFinal);

    const response = await fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(fornecedorFinal),
    });

    if (response.ok) {
      modal.style.display = "none";
      document.getElementById("fornecedorId").value = ""; // Limpar o campo fornecedorId
      carregarFornecedores(); // Atualiza a tabela sem precisar recarregar a página
    } else {
      alert("Erro ao cadastrar ou atualizar fornecedor");
    }
  });


  // Função para excluir fornecedor
  window.deletarFornecedor = async (id) => {
    console.log(id);
    const response = await fetch(`/fornecedores/${id}`, { method: "DELETE" });
    if (response.ok) {
      document.getElementById("fornecedorId").value = "";
      await carregarFornecedores();
    }
  };

  // Função para editar fornecedor
  // Função para editar fornecedor
  window.editarFornecedor = async (id) => {
    console.log("ID do fornecedor (editar):", id); // Adicionando o log para verificar o ID

    if (!id) {
      console.error("ID do fornecedor não encontrado!");
      return; // Impede o código de continuar se o ID não for válido
    }

    const response = await fetch(`/fornecedores/${id}`);
    const fornecedor = await response.json();

    console.log(fornecedor);

    document.getElementById("fornecedorNome").value = fornecedor.nome;
    document.getElementById("fornecedorCnpj").value = fornecedor.cnpj;
    document.getElementById("fornecedorEmail").value = fornecedor.email;
    document.getElementById("rua").value = fornecedor.endereco.rua;
    document.getElementById("numero").value = fornecedor.endereco.numero;
    document.getElementById("bairro").value = fornecedor.endereco.bairro;
    document.getElementById("cidade").value = fornecedor.endereco.cidade;
    document.getElementById("estado").value = fornecedor.endereco.estado;
    document.getElementById("cep").value = fornecedor.endereco.cep;

    // Preencher o campo fornecedorId com o ID correto
    document.getElementById("fornecedorId").value = fornecedor.idFornecedor;  // **Correção aqui**: Garantir que o campo ID seja preenchido

    // Exibe o modal
    modal.style.display = "flex";
  };


  // Função para resetar o formulário
  function resetForm() {
    form.reset();
  }

  carregarFornecedores(); // Carrega os fornecedores ao iniciar a página
});
