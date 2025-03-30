document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("funcionarioModal");
  const openModal = document.getElementById("addFuncionarioBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("funcionarioForm");
  const tableBody = document.getElementById("funcionarioTableBody");

  // Ajusta os campos específicos de acordo com o tipo de funcionário
  function toggleGerenteFields() {
    const tipoFuncionario = document.querySelector('input[name="tipo"]:checked').value;
    const cargoField = document.getElementById("cargo");
    const farmaceuticoFields = document.getElementById("farmaceuticoFields");
    const gerenteFields = document.getElementById("gerenteFields");

    if (tipoFuncionario === "Gerente") {
      farmaceuticoFields.style.display = "none";
      gerenteFields.style.display = "block";
      cargoField.value = 1; // Exemplo: 1 para Gerente
    } else {
      farmaceuticoFields.style.display = "block";
      gerenteFields.style.display = "none";
      cargoField.value = 2; // Exemplo: 2 para Farmacêutico
    }
  }

  // Carregar funcionários e exibi-los na tabela
  async function carregarFuncionarios() {
    const response = await fetch("/funcionarios");
    const funcionarios = await response.json();

    tableBody.innerHTML = "";
    funcionarios.forEach(funcionario => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${funcionario.nome}</td>
        <td>${funcionario.telefone}</td>
        <td>${funcionario.cpf}</td>
        <td>${funcionario.cargo}</td>
        <td>
          <button onclick="editarFuncionario(${funcionario.idFuncionario})">Editar</button>
          <button onclick="deletarFuncionario(${funcionario.idFuncionario})">Excluir</button>
        </td>
      `;
      tableBody.appendChild(row);
    });

    document.querySelectorAll('input[name="tipo"]').forEach(input => {
      input.addEventListener("change", toggleGerenteFields);
    });
  }

  // Exibir o modal e limpar o formulário
  openModal.addEventListener("click", () => {
    modal.style.display = "flex";
    resetForm();
  });

  // Fechar o modal
  closeModal.addEventListener("click", () => {
    modal.style.display = "none";
  });

  // Envio do formulário para criar ou atualizar funcionário
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    // Define o tipo selecionado e monta o objeto de endereço
    const tipo = document.querySelector('input[name="tipo"]:checked').value;
    const endereco = {
      rua: document.getElementById("rua").value,
      numero: document.getElementById("numero").value,
      bairro: document.getElementById("bairro").value,
      cidade: document.getElementById("cidade").value,
      estado: document.getElementById("estado").value,
      cep: document.getElementById("cep").value
    };

    // Monta o payload base
    const payload = {
      nome: document.getElementById("funcionarioNome").value,
      telefone: document.getElementById("funcionarioTelefone").value,
      cpf: document.getElementById("funcionarioCpf").value,
      endereco: endereco,
      cargo: Number(document.getElementById("cargo").value)
    };

    // Inclui os campos específicos conforme o tipo selecionado
    if (tipo === "Farmaceutico") {
      payload.turno = document.getElementById("turno").value;
      payload.crf = document.getElementById("crf").value;
      payload.cargaHoraria = parseFloat(document.getElementById("cargaHoraria").value);
      // Não inclui campo 'funcionariosSupervisionados' para farmacêutico
    } else {
      payload.funcionariosSupervisionados = document.getElementById("funcionariosSupervisionados").value;
      // Não envia campos específicos de farmacêutico
    }

    // Determina endpoint e método conforme se for criação ou atualização
    const id = document.getElementById("funcionarioId").value;
    let method = "POST";
    let endpoint = "/funcionarios"; // Endpoint padrão

    if (id) {
      method = "PUT";
      endpoint = `/funcionarios/${id}`;
    } else {
      // Escolhe endpoint baseado no tipo para criação
      endpoint = tipo === "Farmaceutico" ? "/farmaceuticos" : "/gerentes";
    }

    const response = await fetch(endpoint, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });

    if (response.ok) {
      modal.style.display = "none";
      carregarFuncionarios();
    } else {
      alert("Erro ao cadastrar funcionário");
      console.error("Erro no cadastro:", await response.text());
    }
  });

  // Função para excluir funcionário
  window.deletarFuncionario = async (id) => {
    const response = await fetch(`/funcionarios/${id}`, { method: "DELETE" });
    if (response.ok) {
      carregarFuncionarios();
    } else {
      alert("Erro ao excluir funcionário");
    }
  };

  // Função para editar funcionário (preenche os dados no formulário)
  window.editarFuncionario = async (id) => {
    const response = await fetch(`/funcionarios/${id}`);
    const funcionario = await response.json();

    document.getElementById("funcionarioId").value = funcionario.idFuncionario;
    document.getElementById("funcionarioNome").value = funcionario.nome;
    document.getElementById("funcionarioTelefone").value = funcionario.telefone;
    document.getElementById("funcionarioCpf").value = funcionario.cpf;
    document.getElementById("rua").value = funcionario.endereco.rua;
    document.getElementById("numero").value = funcionario.endereco.numero;
    document.getElementById("bairro").value = funcionario.endereco.bairro;
    document.getElementById("cidade").value = funcionario.endereco.cidade;
    document.getElementById("estado").value = funcionario.endereco.estado;
    document.getElementById("cep").value = funcionario.endereco.cep;

    // Define o tipo e ajusta os campos
    document.querySelector(`input[name="tipo"][value="${funcionario.tipo}"]`).checked = true;
    toggleGerenteFields();

    modal.style.display = "flex";
  };

  // Função para resetar o formulário
  function resetForm() {
    form.reset();
    setTimeout(toggleGerenteFields, 300);
  }

  // Inicializa a página
  carregarFuncionarios();
  toggleGerenteFields();
});
