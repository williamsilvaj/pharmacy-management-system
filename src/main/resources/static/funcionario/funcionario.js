document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("funcionarioModal");
  const openModal = document.getElementById("addFuncionarioBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("funcionarioForm");
  const tableBody = document.getElementById("funcionarioTableBody");

  // Fechar o modal
  closeModal.addEventListener("click", () => {
    modal.style.display = "none";
    // Resetando o campo do ID do funcionário ao fechar o modal
    document.getElementById("funcionarioId").value = ""; // Limpar o ID
  });

  // Função para mostrar ou ocultar campos específicos com base no tipo
  async function toggleGerenteFields() {
    const tipoFuncionario = document.querySelector('input[name="tipo"]:checked').value;
    const cargo = document.getElementById("cargo");

    const farmaceuticoFields = document.getElementById("farmaceuticoFields");
    const gerenteFields = document.getElementById("gerenteFields");

    if (tipoFuncionario === "Gerente") {
      farmaceuticoFields.style.display = "none";
      gerenteFields.style.display = "block";
      cargo.value = "Gerente";
    } else {
      farmaceuticoFields.style.display = "block";
      gerenteFields.style.display = "none";
      cargo.value = "Farmacêutico";
      await carregarSupervisores();
    }
  }

  // Supervisor
  async function carregarSupervisores() {
    const response = await fetch("/gerentes");
    const gerentes = await response.json();
    console.log(gerentes);

    const selectSupervisor = document.getElementById("supervisor");
    selectSupervisor.innerHTML = '<option value="">Nenhum</option>'; // Opção padrão

    gerentes.forEach(gerente => {
      const option = document.createElement("option");
      option.value = gerente.id;
      option.textContent = `${gerente.nome} (ID: ${gerente.id})`;
      selectSupervisor.appendChild(option);
    });
  }


  // Função para exibir funcionários na tabela
    async function carregarFuncionarios() {
      const response = await fetch("/funcionarios");
      const funcionarios = await response.json();
      console.log(funcionarios);

      tableBody.innerHTML = ""; // Limpar tabela antes de atualizar
      funcionarios.forEach(funcionario => {
        const row = document.createElement("tr");
        row.innerHTML = `
        <td>${funcionario.nome}</td>
        <td>${funcionario.telefone}</td>
        <td>${funcionario.cpf}</td>
        <td>${funcionario.cargo.titulo}</td>
        <td>${funcionario.turno ?? "-"}</td>
        <td>${funcionario.crf ?? "-"}</td>
        <td>${funcionario.cargaHoraria ?? "-"}</td>
        <td>${funcionario.idSupervisor ? `ID: ${funcionario.idSupervisor}` : "-"}</td>
        <td>
          <button onclick="editarFuncionario(${funcionario.id}, '${funcionario.cargo.titulo}', ${funcionario.idSupervisor})">Editar</button>
          <button onclick="deletarFuncionario(${funcionario.id})">Excluir</button>
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

    const funcionarioId = document.getElementById("funcionarioId").value;
    const idSupervisor = parseInt(document.getElementById("supervisor").value, 10) || null;


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
      cargo: {
        titulo: document.getElementById("cargo").value,
        dataContratacao: document.getElementById("dataContratacao").value,
        salario: document.getElementById("salario").value,
      },
      idSupervisor: idSupervisor === -1 ? null : idSupervisor,
    };


    let funcionarioFinal = funcionario;

    // Definindo o endpoint dependendo do tipo de funcionário
    const endpoint = funcionario.cargo.titulo === "Farmacêutico" ? "/farmaceuticos" : "/gerentes";

    if (funcionario.cargo.titulo === "Farmacêutico") {
      funcionarioFinal = {
        ...funcionario,
        tipo: "farmaceutico",
        turno: document.getElementById("turno").value,
        crf: document.getElementById("crf").value,
        cargaHoraria: Number(document.getElementById("cargaHoraria").value),
      };
    } else {
      funcionarioFinal = {
        ...funcionario,
        tipo: "gerente",
      };
    }

    console.log(funcionarioFinal);

    if (funcionarioId) {
      // Se houver ID, significa que estamos atualizando um funcionário, então usamos o método PUT
      const response = await fetch(`/funcionarios/${funcionarioId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(funcionarioFinal),
      });

      if (response.ok) {
        modal.style.display = "none";
        document.getElementById("funcionarioId").value = "";
        carregarFuncionarios();
      } else {
        alert("Erro ao atualizar funcionário");
      }
    } else {
      // Caso contrário, estamos criando um novo funcionário com o método POST
      const response = await fetch(endpoint, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(funcionarioFinal),
      });

      if (response.ok) {
        modal.style.display = "none";
        document.getElementById("funcionarioId").value = "";
        carregarFuncionarios();
      } else {
        alert("Erro ao cadastrar funcionário");
      }
    }
  });

  // Função para excluir funcionário
  window.deletarFuncionario = async (id) => {
    const response = await fetch(`/funcionarios/${id}`, { method: "DELETE" });
    if (response.ok) {
      document.getElementById("funcionarioId").value = "";
      await carregarFuncionarios();
    }
  };

  // Função para editar funcionário
  window.editarFuncionario = async (id, cargo) => {
    const response = await fetch(`/funcionarios/${id}`);
    const funcionario = await response.json();

    // Preencher os campos do formulário com os dados do funcionário
    document.getElementById("funcionarioNome").value = funcionario.nome;
    document.getElementById("funcionarioTelefone").value = funcionario.telefone;
    document.getElementById("funcionarioCpf").value = funcionario.cpf;
    document.getElementById("rua").value = funcionario.endereco.rua;
    document.getElementById("numero").value = funcionario.endereco.numero;
    document.getElementById("bairro").value = funcionario.endereco.bairro;
    document.getElementById("cidade").value = funcionario.endereco.cidade;
    document.getElementById("estado").value = funcionario.endereco.estado;
    document.getElementById("cep").value = funcionario.endereco.cep;
    document.getElementById("cargo").value = funcionario.cargo.titulo;
    document.getElementById("dataContratacao").value = funcionario.cargo.dataContratacao;
    document.getElementById("salario").value = funcionario.cargo.salario;
    document.getElementById("supervisor").value = funcionario.idSupervisor || "";

    // Preencher o campo oculto com o ID
    document.getElementById("funcionarioId").value = funcionario.id;

    // Seleciona e marca o input correto com base no cargo
    if (cargo === "Gerente") {
      document.querySelector('input[name="tipo"][value="Gerente"]').checked = true;
    } else {
      document.getElementById("turno").value = funcionario.turno;
      document.getElementById("crf").value = funcionario.crf;
      document.getElementById("cargaHoraria").value = funcionario.cargaHoraria;
      document.querySelector('input[name="tipo"][value="Farmacêutico"]').checked = true;
    }

    // Exibe o modal
    modal.style.display = "flex";

    // Chama a função para ajustar os campos dependendo do tipo selecionado
    toggleGerenteFields();
  };

  // Função para resetar o formulário
  function resetForm() {
    form.reset();
    // Força a chamada da função toggle para ajustar os campos ao estado inicial
    setTimeout(toggleGerenteFields, 900);
  }

  carregarFuncionarios(); // Carrega os funcionários ao iniciar a página
});
