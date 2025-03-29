document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("funcionarioModal");
  const openModal = document.getElementById("addFuncionarioBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("funcionarioForm");
  const tableBody = document.getElementById("funcionarioTableBody");


  // Função para mostrar ou ocultar campos específicos com base no tipo
  function toggleGerenteFields() {
    const tipoFuncionario = document.querySelector('input[name="tipo"]:checked').value;
    const idCargo = document.getElementById("cargo");

    const farmaceuticoFields = document.getElementById("farmaceuticoFields");
    const gerenteFields = document.getElementById("gerenteFields");

    if (tipoFuncionario === "Gerente") {
      farmaceuticoFields.style.display = "none";
      gerenteFields.style.display = "block";
      idCargo.value = 1;
    } else {
      farmaceuticoFields.style.display = "block";
      gerenteFields.style.display = "none";
      idCargo.value = 2;
    }
  }

  // Função para exibir funcionários na tabela
  async function carregarFuncionarios() {
    const response = await fetch("/funcionarios");
    const funcionarios = await response.json();

    tableBody.innerHTML = ""; // Limpar tabela antes de atualizar
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

    // Adiciona um listener para garantir que os campos mudem quando o tipo for alterado
    const tipoInputs = document.querySelectorAll('input[name="tipo"]');
    tipoInputs.forEach(input => {
      input.addEventListener("change", toggleGerenteFields);
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


  // Chama a função para definir a visibilidade dos campos ao carregar a página
  toggleGerenteFields();

  // Lógica de envio do formulário
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    // Obtendo os valores dos campos do formulário
    const funcionario = {
      nome: document.getElementById("funcionarioNome").value,
      telefone: document.getElementById("funcionarioTelefone").value,
      cpf: document.getElementById("funcionarioCpf").value,
      endereco: {
        rua: document.getElementById("rua").value,
        numero: document.getElementById("numero").value,
        bairro: document.getElementById("bairro").value,
        cidade: document.getElementById("cidade").value,
        estado: document.getElementById("estado").value,
        cep: document.getElementById("cep").value,
      },
      cargo: Number(document.getElementById("cargo").value), // Farmacêutico ou Gerente
    };


    // Definindo o endpoint dependendo do tipo de funcionário
    const endpoint = funcionario.tipo === "Farmaceutico" ? "/farmaceuticos" : "/gerentes";

    console.log('endpoint: ', endpoint);
    console.log(JSON.stringify(funcionario));
    // Enviando os dados para o backend
    const response = await fetch(endpoint, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(funcionario),
    });

    if (response.ok) {
      modal.style.display = "none";
      carregarFuncionarios(); // Atualiza a tabela sem precisar recarregar a página
    } else {
      alert("Erro ao cadastrar funcionário");
    }
  });

  // Função para excluir funcionário
  window.deletarFuncionario = async (id) => {
    const response = await fetch(`/funcionarios/${id}`, { method: "DELETE" });
    if (response.ok) {
      carregarFuncionarios();
    }
  };

  // Função para editar funcionário
  window.editarFuncionario = async (id) => {
    const response = await fetch(`/funcionarios/${id}`);
    const funcionario = await response.json();

    document.getElementById("funcionarioNome").value = funcionario.nome;
    document.getElementById("funcionarioTelefone").value = funcionario.telefone;
    document.getElementById("funcionarioCpf").value = funcionario.cpf;
    document.getElementById("rua").value = funcionario.endereco.rua;
    document.getElementById("numero").value = funcionario.endereco.numero;
    document.getElementById("bairro").value = funcionario.endereco.bairro;
    document.getElementById("cidade").value = funcionario.endereco.cidade;
    document.getElementById("estado").value = funcionario.endereco.estado;
    document.getElementById("cep").value = funcionario.endereco.cep;

    // Preenche os campos de tipo
    document.querySelector(`input[name="tipo"][value="${funcionario.tipo}"]`).checked = true;

    // Exibe o modal
    modal.style.display = "flex";

    // Chama a função para ajustar os campos dependendo do tipo selecionado
    toggleGerenteFields();

    form.onsubmit = async (e) => {
      e.preventDefault();

      // Atualiza os dados do funcionário conforme os novos valores do formulário
      const updatedFuncionario = {
        nome: document.getElementById("funcionarioNome").value,
        telefone: document.getElementById("funcionarioTelefone").value,
        cpf: document.getElementById("funcionarioCpf").value,
        endereco: {
          rua: document.getElementById("rua").value,
          numero: document.getElementById("numero").value,
          bairro: document.getElementById("bairro").value,
          cidade: document.getElementById("cidade").value,
          estado: document.getElementById("estado").value,
          cep: document.getElementById("cep").value
        },
        tipo: document.querySelector('input[name="tipo"]:checked').value
      };

      // Endpoint e método de envio
      const endpoint = updatedFuncionario.tipo === "Farmaceutico" ? `/farmaceuticos/${id}` : `/gerentes/${id}`;

      await fetch(endpoint, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedFuncionario),
      });

      modal.style.display = "none";
      carregarFuncionarios(); // Atualiza a tabela sem precisar recarregar a página
    };
  };

  // Função para resetar o formulário
  function resetForm() {
    form.reset();
    // Força a chamada da função toggle para ajustar os campos ao estado inicial
    setTimeout(toggleGerenteFields, 900);
  }

  carregarFuncionarios(); // Carrega os funcionários ao iniciar a página

  // Adiciona um listener para garantir que os campos mudem quando o tipo for alterado
  // const tipoInputs = document.querySelectorAll('input[name="tipo"]');
  // tipoInputs.forEach(input => {
  //   input.addEventListener("change", toggleGerenteFields);
  // });
});
