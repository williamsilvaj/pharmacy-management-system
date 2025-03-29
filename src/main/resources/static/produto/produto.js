document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("productModal");
    const openModal = document.getElementById("addProductBtn");
    const closeModal = document.querySelector(".close");
    const form = document.getElementById("productForm");
    const tableBody = document.getElementById("productTableBody");

    // Função para exibir produtos na tabela
    async function carregarProdutos() {
        const response = await fetch("/produtos");
        const produtos = await response.json();

        tableBody.innerHTML = ""; // Limpar tabela antes de atualizar
        produtos.forEach(produto => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${produto.nome}</td>
                <td>${produto.codigo}</td>
                <td>${produto.fabricante}</td>
                <td>${produto.concentracao}</td>
                <td>
                    <button onclick="editarProduto(${produto.idProduto})">Editar</button>
                    <button onclick="deletarProduto(${produto.idProduto})">Excluir</button>
                </td>
            `;
            tableBody.appendChild(row);
        });
    }

    openModal.addEventListener("click", () => {
        modal.style.display = "flex";
    });

    closeModal.addEventListener("click", () => {
        modal.style.display = "none";
    });

    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        const produto = {
            nome: document.getElementById("productName").value,
            codigo: document.getElementById("productCode").value,
            fabricante: document.getElementById("productManufacturer").value,
            concentracao: document.getElementById("productConcentration").value,
        };

        await fetch("/produtos", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(produto),
        });

        modal.style.display = "none";
        carregarProdutos(); // Atualiza a tabela sem precisar recarregar a página
    });

    window.deletarProduto = async (id) => {
        await fetch(`/produtos/${id}`, { method: "DELETE" });
        carregarProdutos();
    };

    window.editarProduto = async (id) => {
        const response = await fetch(`/produtos/${id}`);
        const produto = await response.json();

        document.getElementById("productName").value = produto.nome;
        document.getElementById("productCode").value = produto.codigo;
        document.getElementById("productManufacturer").value = produto.fabricante;
        document.getElementById("productConcentration").value = produto.concentracao;
        document.getElementById("productId").value = produto.idProduto;

        modal.style.display = "flex";

        form.onsubmit = async (e) => {
            e.preventDefault();
            const produtoAtualizado = {
                nome: document.getElementById("productName").value,
                codigo: document.getElementById("productCode").value,
                fabricante: document.getElementById("productManufacturer").value,
                concentracao: document.getElementById("productConcentration").value,
            };

            await fetch(`/produtos/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(produtoAtualizado),
            });

            modal.style.display = "none";
            carregarProdutos();
        };
    };

    carregarProdutos(); // Carrega os produtos ao iniciar a página
});
