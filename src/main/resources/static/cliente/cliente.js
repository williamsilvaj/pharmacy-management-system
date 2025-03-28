const API_URL = 'http://localhost:3306/clientes'; // URL da API, ajuste conforme necessário

document.getElementById('btnCadastrarCliente').addEventListener('click', function() {
  mostrarFormularioCadastro();
});

document.getElementById('btnVoltar').addEventListener('click', function() {
  alert('Voltando para a tela principal');
});

document.getElementById('btnVoltarListarClientes').addEventListener('click', function() {
  voltarTelaInicial();
});

document.getElementById('btnSalvarCliente').addEventListener('click', function() {
  salvarCliente();
});

document.getElementById('btnCancelarCadastro').addEventListener('click', function() {
  voltarTelaInicial();
});

document.getElementById('btnConsultarCliente').addEventListener('click', function() {
  mostrarFormularioConsultar();
});

document.getElementById('btnBuscarCliente').addEventListener('click', function() {
  consultarClientePorCpf();
});

document.getElementById('btnCancelarConsulta').addEventListener('click', function() {
  voltarTelaInicial();
});

document.getElementById('btnAlterarCliente').addEventListener('click', function() {
  alert('Alterar dados do cliente');
});

document.getElementById('btnRemoverCliente').addEventListener('click', function() {
  alert('Remover cliente');
});

// Funções CRUD

function mostrarFormularioCadastro() {
  document.getElementById('formularioCadastro').style.display = 'block';
  document.getElementById('listarClientes').style.display = 'none';
  document.getElementById('formularioConsultar').style.display = 'none';
}

function mostrarFormularioConsultar() {
  document.getElementById('formularioConsultar').style.display = 'block';
  document.getElementById('formularioCadastro').style.display = 'none';
  document.getElementById('listarClientes').style.display = 'none';
}

function salvarCliente() {
  const nome = document.getElementById('nomeCliente').value;
  const cpf = document.getElementById('cpfCliente').value;
  const email = document.getElementById('emailCliente').value;

  if (nome && cpf && email) {
    const cliente = { nome, cpf, email };

    // Enviar POST para a API para criar o cliente
    fetch(API_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(cliente),
    })
      .then(response => response.json())
      .then(data => {
        alert('Cliente cadastrado com sucesso!');
        voltarTelaInicial();
      })
      .catch(error => alert('Erro ao cadastrar cliente: ' + error));
  } else {
    alert('Por favor, preencha todos os campos!');
  }
}

function consultarClientePorCpf() {
  const cpf = document.getElementById('cpfConsulta').value;

  // Enviar GET para a API para buscar o cliente pelo CPF
  fetch(`${API_URL}/cpf/${cpf}`)
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Cliente não encontrado');
      }
    })
    .then(cliente => {
      alert(`Cliente encontrado: ${cliente.nome}`);
    })
    .catch(error => alert(error.message));
}

function listarClientes() {
  // Enviar GET para a API para listar todos os clientes
  fetch(API_URL)
    .then(response => response.json())
    .then(clientes => {
      const clientesList = document.getElementById('clientesList');
      clientesList.innerHTML = '';
      clientes.forEach(cliente => {
        const li = document.createElement('li');
        li.textContent = `Nome: ${cliente.nome}, CPF: ${cliente.cpf}, Email: ${cliente.email}`;
        clientesList.appendChild(li);
      });

      document.getElementById('listarClientes').style.display = 'block';
      document.getElementById('formularioCadastro').style.display = 'none';
      document.getElementById('formularioConsultar').style.display = 'none';
    })
    .catch(error => alert('Erro ao listar clientes: ' + error));
}

function voltarTelaInicial() {
  document.getElementById('formularioCadastro').style.display = 'none';
  document.getElementById('listarClientes').style.display = 'none';
  document.getElementById('formularioConsultar').style.display = 'none';
  document.querySelector('.botoes-panel').style.display = 'flex';
}
