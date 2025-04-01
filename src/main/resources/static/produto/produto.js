document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("productModal");
  const openModal = document.getElementById("addProductBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("productForm");
  const tableBody = document.getElementById("productTableBody");
  const searchInput = document.getElementById("searchInput");

  let allProducts = []; // Armazenar todos os produtos

  // Função para carregar e exibir os produtos na tabela
  async function loadProducts() {
    const response = await fetch("/produtos");
    const produtos = await response.json();
    allProducts = produtos; // Armazenar todos os produtos
    displayProducts(produtos); // Exibir todos os produtos
  }

  // Função para exibir produtos na tabela
  function displayProducts(products) {
    tableBody.innerHTML = ""; // Limpa a tabela antes de atualizar
    products.forEach(produto => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${produto.nome}</td>
        <td>${produto.codigo}</td>
        <td>${produto.fabricante}</td>
        <td>${produto.concentracao}</td>
        <td>
          <button onclick="editProduct(${produto.idProduto})">Editar</button>
          <button onclick="deleteProduct(${produto.idProduto})">Excluir</button>
        </td>
      `;
      tableBody.appendChild(row);
    });
  }

  // Exibir modal para adicionar produto
  openModal.addEventListener("click", () => {
    modal.style.display = "flex";
    form.reset(); // Limpa o formulário ao abrir o modal
    document.getElementById("productId").value = ""; // Remove o ID do produto ao abrir o modal
  });

  // Fechar o modal
  closeModal.addEventListener("click", () => {
    modal.style.display = "none";
    form.reset();
    document.getElementById("productId").value = ""; // Remove o ID ao fechar o modal
  });

  // Submissão do formulário (adicionar ou editar produto)
  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("productId").value.trim(); // Verificação para garantir que id está vazio ao adicionar
    const produto = {
      nome: document.getElementById("productName").value,
      codigo: document.getElementById("productCode").value,
      fabricante: document.getElementById("productManufacturer").value,
      concentracao: document.getElementById("productConcentration").value
    };

    let method = "POST";
    let url = "/produtos";
    if (id) { // Se id existir, usamos PUT para editar
      method = "PUT";
      url = `/produtos/${id}`;
    }

    await fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(produto)
    });

    modal.style.display = "none";
    form.reset();
    loadProducts();
  });

  // Função para excluir produto
  window.deleteProduct = async (id) => {
    if (confirm("Tem certeza que deseja excluir este produto?")) {
      await fetch(`/produtos/${id}`, { method: "DELETE" });
      loadProducts();
    }
  };

  // Função para editar produto (carregar dados no formulário)
  window.editProduct = async (id) => {
    const response = await fetch(`/produtos/${id}`);
    const produto = await response.json();

    document.getElementById("productId").value = produto.idProduto;
    document.getElementById("productName").value = produto.nome;
    document.getElementById("productCode").value = produto.codigo;
    document.getElementById("productManufacturer").value = produto.fabricante;
    document.getElementById("productConcentration").value = produto.concentracao;

    modal.style.display = "flex";
  };

  // Função para pesquisar produtos
  searchInput.addEventListener("input", () => {
    const query = searchInput.value.toLowerCase();
    const filteredProducts = allProducts.filter(produto => {
      return (
        produto.nome.toLowerCase().includes(query) ||
        produto.codigo.toString().includes(query)
      );
    });
    displayProducts(filteredProducts);
  });

  // Carrega os produtos ao iniciar a página
  loadProducts();
});
