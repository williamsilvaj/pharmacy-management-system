document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("expenseModal");
  const openModal = document.getElementById("addExpenseBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("expenseForm");
  const tableBody = document.getElementById("expenseTableBody");

  // Função para carregar os gerentes
  async function carregarGerentes() {
    try {
      const response = await fetch('/gerentes');
      const gerentes = await response.json();
      const selectGerente = document.getElementById("expenseManager");

      selectGerente.innerHTML = ''; // Limpar o select antes de adicionar as opções
      const optionDefault = document.createElement("option");
      optionDefault.value = '';
      optionDefault.textContent = 'Selecione um gerente';
      selectGerente.appendChild(optionDefault);

      gerentes.forEach(gerente => {
        const option = document.createElement("option");
        option.value = gerente.id;
        option.textContent = gerente.nome;
        selectGerente.appendChild(option);
      });
    } catch (error) {
      console.error("Erro ao carregar gerentes:", error);
    }
  }

  // Função para exibir despesas na tabela
  async function carregarDespesas() {
    try {
      const response = await fetch("/despesas");
      if (!response.ok) throw new Error(`Erro ao carregar despesas: ${response.status}`);

      const despesas = await response.json();
      tableBody.innerHTML = ""; // Limpar tabela antes de atualizar

      despesas.forEach(despesa => {
        const row = document.createElement("tr");
        row.innerHTML = `
                  <td>${despesa.descricao}</td>
                  <td>${despesa.data}</td>
                  <td>${despesa.valor}</td>
                  <td>${despesa.gerente.nome}</td>
                  <td>
                      <button onclick="editarDespesa(${despesa.idDespesa})">Editar</button>
                      <button onclick="deletarDespesa(${despesa.idDespesa})">Excluir</button>
                  </td>
              `;
        tableBody.appendChild(row);
      });
    } catch (error) {
      console.error(error.message);
    }
  }

  openModal.addEventListener("click", () => {
    modal.style.display = "flex";
    carregarGerentes();

    // Resetar o formulário para evitar dados de edições anteriores
    form.reset();
    document.getElementById("expenseId").value = "";

    // Remover event listener antigo e adicionar um novo para criar despesas
    form.onsubmit = async (e) => {
      e.preventDefault();
      const idGerente = document.getElementById("expenseManager").value;

      const novaDespesa = {
        descricao: document.getElementById("expenseDescription").value,
        valor: parseFloat(document.getElementById("expenseValue").value),
        data: document.getElementById("expenseDate").value,
        gerente: { id: Number(idGerente) },
      };

      await fetch("/despesas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(novaDespesa),
      });

      modal.style.display = "none";
      await carregarDespesas();
    };
  });

  closeModal.addEventListener("click", () => {
    modal.style.display = "none";
  });

  window.deletarDespesa = async (id) => {
    await fetch(`/despesas/${id}`, { method: "DELETE" });
    await carregarDespesas();
  };

  window.editarDespesa = async (id) => {
    const response = await fetch(`/despesas/${id}`);
    const despesa = await response.json();

    await carregarGerentes();

    document.getElementById("expenseDescription").value = despesa.descricao;
    document.getElementById("expenseValue").value = despesa.valor;
    document.getElementById("expenseDate").value = despesa.data;
    document.getElementById("expenseId").value = despesa.idDespesa;
    document.getElementById("expenseManager").value = despesa.gerente.id;
    console.log(despesa.data);

    modal.style.display = "flex";

    // Remover qualquer outro evento de submit antes de adicionar um novo
    form.onsubmit = null;

    form.onsubmit = async (e) => {
      e.preventDefault();
      const despesaAtualizada = {
        descricao: document.getElementById("expenseDescription").value,
        valor: parseFloat(document.getElementById("expenseValue").value),
        data: document.getElementById("expenseDate").value,
        gerente: { id: Number(document.getElementById("expenseManager").value) }
      };

      console.log(despesaAtualizada);

      await fetch(`/despesas/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(despesaAtualizada),
      });

      modal.style.display = "none";
      await carregarDespesas();
    };
  };

  carregarDespesas();
});
