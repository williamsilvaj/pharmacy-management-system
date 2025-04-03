document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("clientModal");
  const openModal = document.getElementById("addClientBtn");
  const closeModal = document.getElementById("closeClientModal");
  const form = document.getElementById("clientForm");
  const tableBody = document.getElementById("clientTableBody");
  const searchInput = document.getElementById("searchInput");

  // Recupera o input oculto para o idEndereco
  const enderecoIdInput = document.getElementById("enderecoId");

  let allClients = [];

  async function loadClients() {
    try {
      const response = await fetch("/clientes");
      const clients = await response.json();
      allClients = clients;
      displayClients(clients);
    } catch (error) {
      console.error("Erro ao carregar clientes:", error);
    }
  }

  function displayClients(clients) {
    tableBody.innerHTML = "";
    clients.forEach(client => {
      const endereco = client.endereco;
      const enderecoStr = endereco ? `${endereco.rua}, ${endereco.numero} - ${endereco.bairro}, ${endereco.cidade}, ${endereco.estado} - CEP: ${endereco.cep}` : "";
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${client.nome}</td>
        <td>${client.cpf}</td>
        <td>${client.email}</td>
        <td>${client.telefone}</td>
        <td>${enderecoStr}</td>
        <td>
          <button onclick="editClient(${client.idCliente})">Editar</button>
          <button onclick="deleteClient(${client.idCliente})">Excluir</button>
        </td>
      `;
      tableBody.appendChild(row);
    });
  }

  openModal.addEventListener("click", () => {
    modal.style.display = "flex";
    form.reset();
    // Limpa os campos de identificação do cliente e do endereço
    document.getElementById("clientId").value = "";
    enderecoIdInput.value = "";
  });

  closeModal.addEventListener("click", () => {
    modal.style.display = "none";
    form.reset();
  });

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const id = document.getElementById("clientId").value.trim();
    const client = {
      nome: document.getElementById("clientName").value,
      cpf: document.getElementById("clientCpf").value,
      email: document.getElementById("clientEmail").value,
      telefone: document.getElementById("clientPhone").value,
      endereco: {
        rua: document.getElementById("enderecoRua").value,
        numero: document.getElementById("enderecoNumero").value,
        bairro: document.getElementById("enderecoBairro").value,
        cidade: document.getElementById("enderecoCidade").value,
        estado: document.getElementById("enderecoEstado").value,
        cep: document.getElementById("enderecoCep").value
      }
    };

    // Verifica se o campo de idEndereco possui um valor e o adiciona ao objeto
    const enderecoId = enderecoIdInput.value.trim();
    if (enderecoId) {
      client.endereco.idEndereco = parseInt(enderecoId);
    }

    if (id) {
      // Atualização
      try {
        await fetch(`/clientes/${id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(client)
        });
      } catch (error) {
        console.error("Erro ao atualizar cliente:", error);
      }
    } else {
      // Criação
      try {
        await fetch("/clientes", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(client)
        });
      } catch (error) {
        console.error("Erro ao criar cliente:", error);
      }
    }

    modal.style.display = "none";
    form.reset();
    loadClients();
  });

  window.deleteClient = async (id) => {
    if (confirm("Tem certeza que deseja excluir este cliente?")) {
      try {
        await fetch(`/clientes/${id}`, { method: "DELETE" });
        loadClients();
      } catch (error) {
        console.error("Erro ao excluir cliente:", error);
      }
    }
  };

  window.editClient = async (id) => {
    try {
      const response = await fetch(`/clientes/${id}`);
      const client = await response.json();
      document.getElementById("clientId").value = client.idCliente;
      document.getElementById("clientName").value = client.nome;
      document.getElementById("clientCpf").value = client.cpf;
      document.getElementById("clientEmail").value = client.email;
      document.getElementById("clientPhone").value = client.telefone;

      if (client.endereco) {
        // Preenche o campo oculto do idEndereco se existir
        enderecoIdInput.value = client.endereco.idEndereco ? client.endereco.idEndereco : "";
        document.getElementById("enderecoRua").value = client.endereco.rua;
        document.getElementById("enderecoNumero").value = client.endereco.numero;
        document.getElementById("enderecoBairro").value = client.endereco.bairro;
        document.getElementById("enderecoCidade").value = client.endereco.cidade;
        document.getElementById("enderecoEstado").value = client.endereco.estado;
        document.getElementById("enderecoCep").value = client.endereco.cep;
      }
      modal.style.display = "flex";
    } catch (error) {
      console.error("Erro ao buscar cliente:", error);
    }
  };

  searchInput.addEventListener("input", () => {
    const query = searchInput.value.toLowerCase();
    const filteredClients = allClients.filter(client => {
      return client.nome.toLowerCase().includes(query) ||
             client.cpf.includes(query) ||
             client.email.toLowerCase().includes(query);
    });
    displayClients(filteredClients);
  });

  loadClients();
});
