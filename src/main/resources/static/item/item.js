document.addEventListener("DOMContentLoaded", () => {
  const itemModal = document.getElementById("itemModal");
  const produtoModal = document.getElementById("produtoModal");
  const openItemModalBtn = document.getElementById("addItemBtn");
  const closeItemModal = document.getElementById("closeItemModal");
  const closeProdutoModal = document.getElementById("closeProdutoModal");
  const form = document.getElementById("itemForm");
  const tableBody = document.getElementById("itemTableBody");
  const searchInput = document.getElementById("searchInput");

  // Elementos para seleção de produto
  const openProdutoModalBtn = document.getElementById("openProdutoModalBtn");
  const produtoSearchInput = document.getElementById("produtoSearchInput");
  const produtoTableBody = document.getElementById("produtoTableBody");
  const produtoSelecionadoNome = document.getElementById("produtoSelecionadoNome");
  const itemProdutoId = document.getElementById("itemProdutoId");

  // Elementos para dados do estoque
  const estoqueQuantidadeInput = document.getElementById("estoqueQuantidade");
  const estoqueDataEntradaInput = document.getElementById("estoqueDataEntrada");

  let allItems = [];    // Armazena os itens carregados
  let allProdutos = []; // Armazena os produtos carregados

  // Carrega itens do endpoint /itens
  async function loadItems() {
    try {
      const response = await fetch("/itens");
      const items = await response.json();
      allItems = items;
      displayItems(items);
    } catch (error) {
      console.error("Erro ao carregar itens:", error);
    }
  }

  // Exibe itens na tabela de itens
  function displayItems(items) {
    tableBody.innerHTML = "";
    items.forEach(item => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${item.idItem}</td>
        <td>${item.valor.toFixed(2)}</td>
        <td>${new Date(item.dataVencimento).toLocaleDateString()}</td>
        <td>${item.produto ? item.produto.nome : "N/A"}</td>
        <td>${item.estoque ? item.estoque.quantidade + " / " + new Date(item.estoque.dataEntrada).toLocaleDateString() : "N/A"}</td>
        <td>
          <button onclick="editItem(${item.idItem})">Editar</button>
          <button onclick="deleteItem(${item.idItem})">Excluir</button>
        </td>
      `;
      tableBody.appendChild(row);
    });
  }

  // Abre o modal para adicionar item
  openItemModalBtn.addEventListener("click", () => {
    itemModal.style.display = "flex";
    form.reset();
    document.getElementById("itemId").value = "";
    // Limpa seleção de produto
    produtoSelecionadoNome.value = "";
    itemProdutoId.value = "";
  });

  // Fecha o modal de item
  closeItemModal.addEventListener("click", () => {
    itemModal.style.display = "none";
    form.reset();
  });

  // Abre o modal de seleção de produto
  openProdutoModalBtn.addEventListener("click", () => {
    produtoModal.style.display = "flex";
    loadProdutos(); // Carrega produtos ao abrir o modal
    produtoSearchInput.value = "";
  });

  // Fecha o modal de produto
  closeProdutoModal.addEventListener("click", () => {
    produtoModal.style.display = "none";
  });

  // Carrega produtos do endpoint /produtos
  async function loadProdutos() {
    try {
      const response = await fetch("/produtos");
      const produtos = await response.json();
      allProdutos = produtos;
      displayProdutos(produtos);
    } catch (error) {
      console.error("Erro ao carregar produtos:", error);
    }
  }

  // Exibe produtos na tabela do modal de seleção
  function displayProdutos(produtos) {
    produtoTableBody.innerHTML = "";
    produtos.forEach(produto => {
      const row = document.createElement("tr");
      row.style.cursor = "pointer";
      row.innerHTML = `
        <td>${produto.idProduto}</td>
        <td>${produto.nome}</td>
        <td>${produto.codigo}</td>
        <td>${produto.fabricante}</td>
      `;
      // Ao clicar, seleciona o produto e fecha o modal
      row.addEventListener("click", () => {
        produtoSelecionadoNome.value = produto.nome;
        itemProdutoId.value = produto.idProduto;
        produtoModal.style.display = "none";
      });
      produtoTableBody.appendChild(row);
    });
  }

  // Filtra produtos no modal de seleção conforme o usuário digita
  produtoSearchInput.addEventListener("input", () => {
    const termo = produtoSearchInput.value.toLowerCase();
    const filteredProdutos = allProdutos.filter(produto => {
      return produto.nome.toLowerCase().includes(termo) ||
             produto.codigo.toString().includes(termo);
    });
    displayProdutos(filteredProdutos);
  });

  // Submete o formulário de item (para criar ou atualizar)
  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("itemId").value.trim();

    // Monta o objeto do item com os dados do formulário (sem quantidade de item)
    const itemData = {
      valor: parseFloat(document.getElementById("itemValor").value),
      dataVencimento: document.getElementById("itemDataVencimento").value,
      produto: { idProduto: parseInt(itemProdutoId.value) }
    };

    // Para criação, primeiro cria o estoque
    if (!id) {
      const estoqueData = {
        quantidade: parseInt(estoqueQuantidadeInput.value),
        dataEntrada: estoqueDataEntradaInput.value
      };

      try {
        // Cria o estoque e obtém o id retornado
        const estoqueResponse = await fetch("/estoques", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(estoqueData)
        });
        if (!estoqueResponse.ok) {
          throw new Error("Erro ao criar estoque");
        }
        const novoEstoque = await estoqueResponse.json();
        // Associa o estoque criado ao item
        itemData.estoque = { idEstoque: novoEstoque.idEstoque };
      } catch (error) {
        console.error("Erro na criação do estoque:", error);
        return;
      }

      // Agora cria o item
      try {
        await fetch("/itens", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(itemData)
        });
        itemModal.style.display = "none";
        form.reset();
        loadItems();
      } catch (error) {
        console.error("Erro ao salvar item:", error);
      }
    } else {
      // Se for atualização, mantém a lógica anterior
      try {
        await fetch(`/itens/${id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(itemData)
        });
        itemModal.style.display = "none";
        form.reset();
        loadItems();
      } catch (error) {
        console.error("Erro ao atualizar item:", error);
      }
    }
  });

  // Função global para excluir item
  window.deleteItem = async (id) => {
    if (confirm("Tem certeza que deseja excluir este item?")) {
      try {
        await fetch(`/itens/${id}`, { method: "DELETE" });
        loadItems();
      } catch (error) {
        console.error("Erro ao excluir item:", error);
      }
    }
  };

  // Função global para editar item (carregar dados no formulário)
  window.editItem = async (id) => {
    try {
      const response = await fetch(`/itens/${id}`);
      const item = await response.json();

      document.getElementById("itemId").value = item.idItem;
      document.getElementById("itemValor").value = item.valor;
      document.getElementById("itemDataVencimento").value = item.dataVencimento;
      if (item.produto) {
        produtoSelecionadoNome.value = item.produto.nome;
        itemProdutoId.value = item.produto.idProduto;
      }
      // Para edição, se desejar permitir alteração do estoque, carrega os dados
      if (item.estoque) {
        estoqueQuantidadeInput.value = item.estoque.quantidade;
        estoqueDataEntradaInput.value = item.estoque.dataEntrada;
      }

      itemModal.style.display = "flex";
    } catch (error) {
      console.error("Erro ao buscar item:", error);
    }
  };

  // Filtra itens na tabela principal
  searchInput.addEventListener("input", () => {
    const query = searchInput.value.toLowerCase();
    const filteredItems = allItems.filter(item => {
      const produtoNome = item.produto ? item.produto.nome.toLowerCase() : "";
      const estoqueQtd = item.estoque ? item.estoque.quantidade.toString() : "";
      return produtoNome.includes(query) || estoqueQtd.includes(query);
    });
    displayItems(filteredItems);
  });

  // Carrega os itens ao iniciar a página
  loadItems();
});
