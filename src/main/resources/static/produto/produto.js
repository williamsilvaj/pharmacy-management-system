document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("productModal");
  const openModal = document.getElementById("addProductBtn");
  const closeModal = document.querySelector(".close");
  const form = document.getElementById("productForm");
  const tableBody = document.getElementById("productTableBody");
  const searchInput = document.getElementById("searchInput");

  let allProducts = [];

  async function loadProducts() {
    const response = await fetch("/produtos");
    const produtos = await response.json();
    allProducts = produtos;
    displayProducts(produtos);
  }

  function displayProducts(products) {
    tableBody.innerHTML = "";
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

  // Abrir modal para adicionar um novo produto
  openModal.addEventListener("click", () => {
    modal.style.display = "flex";
    form.reset();
    document.getElementById("productId").value = ""; // Garantindo que o ID está vazio
  });

  closeModal.addEventListener("click", () => {
    modal.style.display = "none";
    form.reset();
    document.getElementById("productId").value = "";
  });

  // Submissão do formulário (distinguir POST e PUT corretamente)
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const id = document.getElementById("productId").value.trim();
    const produto = {
      nome: document.getElementById("productName").value,
      codigo: document.getElementById("productCode").value,
      fabricante: document.getElementById("productManufacturer").value,
      concentracao: document.getElementById("productConcentration").value
    };

    if (id) {
      // Se ID existir, é uma edição (PUT)
      await fetch(`/produtos/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(produto)
      });
    } else {
      // Se ID não existir, é um novo cadastro (POST)
      await fetch("/produtos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(produto)
      });
    }

    modal.style.display = "none";
    form.reset();
    document.getElementById("productId").value = ""; // Resetando ID
    loadProducts();
  });

  window.deleteProduct = async (id) => {
    if (confirm("Tem certeza que deseja excluir este produto?")) {
      await fetch(`/produtos/${id}`, { method: "DELETE" });
      loadProducts();
    }
  };

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

  loadProducts();
});
