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
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    // Obtendo os valores dos campos do formulário
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

    // Enviando os dados para o backend
    const response = await fetch("/clientes", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(cliente),
    });

    if (response.ok) {
      modal.style.display = "none";
      carregarClientes(); // Atualiza a tabela sem precisar recarregar a página
    } else {
      alert("Erro ao cadastrar cliente");
    }
  });

  // Função para carregar clientes na tabela
  async function carregarClientes() {
    const response = await fetch("/clientes");
    const clientes = await response.json();

    tableBody.innerHTML = ""; // Limpar tabela antes de atualizar
    clientes.forEach(cliente => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${cliente.nome}</td>
        <td>${cliente.telefone}</td>
        <td>${cliente.email}</td>
        <td>${cliente.cpf}</td>
        <td>
          <button onclick="editarCliente(${cliente.id})">Editar</button>
          <button onclick="deletarCliente(${cliente.id})">Excluir</button>
        </td>
      `;
      tableBody.appendChild(row);
    });
  }

  // Função para excluir cliente
  window.deletarCliente = async (id) => {
    const response = await fetch(`/clientes/${id}`, { method: "DELETE" });
    if (response.ok) {
      carregarClientes();
    }
  };

  // Função para editar cliente
  window.editarCliente = async (id) => {
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

    // Exibe o modal
    modal.style.display = "flex";

    form.onsubmit = async (e) => {
      e.preventDefault();

      // Atualiza os dados do cliente conforme os novos valores do formulário
      const updatedCliente = {
        nome: document.getElementById("clienteNome").value,
        telefone: document.getElementById("clienteTelefone").value,
        cpf: document.getElementById("clienteCpf").value,
        endereco: {
          rua: document.getElementById("rua").value,
          numero: document.getElementById("numero").value,
          bairro: document.getElementById("bairro").value,
          cidade: document.getElementById("cidade").value,
          estado: document.getElementById("estado").value,
          cep: document.getElementById("cep").value
        }
      };

      await fetch(`/clientes/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedCliente),
      });

      modal.style.display = "none";
      carregarClientes(); // Atualiza a tabela sem precisar recarregar a página
    };
  };

  // Função para resetar o formulário
  function resetForm() {
    form.reset();
  }

  carregarClientes(); // Carrega os clientes ao iniciar a página
});

