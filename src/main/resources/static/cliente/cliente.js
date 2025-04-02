document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("clienteModal");
  const openModal = document.getElementById("addClienteBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("clienteForm");
  const tableBody = document.getElementById("clienteTableBody");

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
  // Lógica de envio do formulário
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    // Obtendo os valores dos campos do formulário
    const clienteId = document.getElementById("clienteId").value.trim();
    const cliente = {
      nome: document.getElementById("clienteNome").value,
      telefone: document.getElementById("clienteTelefone").value,
      cpf: document.getElementById("clienteCpf").value,
      email: document.getElementById("clienteEmail").value,
      endereco: {
        rua: document.getElementById("rua").value,
        numero: document.getElementById("numero").value,
        bairro: document.getElementById("bairro").value,
        cidade: document.getElementById("cidade").value,
        estado: document.getElementById("estado").value,
        cep: document.getElementById("cep").value,
      }
    };

    // Verifique se campos obrigatórios estão preenchidos
    if (!cliente.endereco.bairro || !cliente.endereco.rua || !cliente.endereco.numero || !cliente.endereco.cidade || !cliente.endereco.estado || !cliente.endereco.cep) {
      alert("Preencha todos os campos obrigatórios, incluindo bairro, rua, número, cidade, estado e CEP.");
      return; // Impede o envio do formulário se campos obrigatórios estiverem vazios
    }

    let method = "POST";
    let url = "/clientes";  // A URL de criação de clientes

    if (clienteId) { // Se clienteId existir, usamos PUT para editar
      method = "PUT";
      url = `/clientes/${clienteId}`; // Certifique-se de que o ID está sendo passado corretamente
    }

    // Enviando os dados para o backend
    const response = await fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(cliente),
    });

    if (response.ok) {
      modal.style.display = "none";
      await carregarClientes(); // Atualiza a tabela sem precisar recarregar a página
    } else {
      alert("Erro ao cadastrar ou atualizar cliente");
    }
  });



  // Função para carregar clientes na tabela
  async function carregarClientes() {
    const response = await fetch("/clientes");
    const clientes = await response.json();
    console.log(clientes);

    tableBody.innerHTML = ""; // Limpar tabela antes de atualizar
    clientes.forEach(cliente => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${cliente.nome}</td>
        <td>${cliente.telefone}</td>
        <td>${cliente.email}</td>
        <td>${cliente.cpf}</td>
        <td>
          <button onclick="editarCliente(${cliente.idCliente})">Editar</button>
          <button onclick="deletarCliente(${cliente.idCliente})">Excluir</button>
        </td>
      `;
      tableBody.appendChild(row);
    });
  }

  // Função para excluir cliente
  window.deletarCliente = async (id) => {
    const response = await fetch(`/clientes/${id}`, { method: "DELETE" });
    if (response.ok) {
      await carregarClientes();
    }
  };

  // Função para editar cliente
  window.editarCliente = async (id) => {
    console.log(id);
    const response = await fetch(`/clientes/${id}`);
    const cliente = await response.json();

    document.getElementById("clienteNome").value = cliente.nome;
    document.getElementById("clienteTelefone").value = cliente.telefone;
    document.getElementById("clienteEmail").value = cliente.email;
    document.getElementById("clienteCpf").value = cliente.cpf;
    document.getElementById("rua").value = cliente.endereco.rua;
    document.getElementById("numero").value = cliente.endereco.numero;
    document.getElementById("bairro").value = cliente.endereco.bairro;
    document.getElementById("cidade").value = cliente.endereco.cidade;
    document.getElementById("estado").value = cliente.endereco.estado;
    document.getElementById("cep").value = cliente.endereco.cep;

    // Preencher o campo clienteId com o ID correto
    document.getElementById("clienteId").value = cliente.idCliente;  // Garantir que o campo ID seja preenchido

    // Exibe o modal
    modal.style.display = "flex";
  };

  // Função para resetar o formulário
  function resetForm() {
    form.reset();
  }

  carregarClientes(); // Carrega os clientes ao iniciar a página
});
