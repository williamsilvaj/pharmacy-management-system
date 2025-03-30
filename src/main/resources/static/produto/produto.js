document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("productModal");
  const openModal = document.getElementById("addProductBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("productForm");
  const tableBody = document.getElementById("productTableBody");

  // Função para carregar e exibir os produtos na tabela
  async function loadProducts() {
    const response = await fetch("/produtos");
    const produtos = await response.json();
    tableBody.innerHTML = ""; // Limpa a tabela antes de atualizar

    produtos.forEach(produto => {
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
  });

  // Fechar o modal
  closeModal.addEventListener("click", () => {
    modal.style.display = "none";
    form.reset();
  });

  // Submissão do formulário (adicionar ou editar produto)
  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("productId").value;
    const produto = {
      nome: document.getElementById("productName").value,
      codigo: document.getElementById("productCode").value,
      fabricante: document.getElementById("productManufacturer").value,
      concentracao: document.getElementById("productConcentration").value
    };

    let method = "POST";
    let url = "/produtos";
    if (id) {
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

  // Carrega os produtos ao iniciar a página
  loadProducts();
});
