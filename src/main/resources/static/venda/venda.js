document.addEventListener("DOMContentLoaded", () => {
    // Elementos do DOM
    const newSaleBtn = document.getElementById("newSaleBtn");
    const saleForm = document.getElementById("saleForm");
    const cancelSaleBtn = document.getElementById("cancelSaleBtn");
    const confirmSaleBtn = document.getElementById("confirmSaleBtn");
    const salesHistoryBody = document.getElementById("salesHistoryBody");
    
    // Modais
    const clientModal = document.getElementById("clientModal");
    const pharmacistModal = document.getElementById("pharmacistModal");
    const productModal = document.getElementById("productModal");
    
    // Variáveis de estado
    let selectedClient = null;
    let selectedPharmacist = null;
    let saleItems = [];
    
    // Inicialização
    loadSalesHistory();
    
    // Event Listeners
    newSaleBtn.addEventListener("click", () => {
        saleForm.style.display = "block";
        resetSaleForm();
    });
    
    cancelSaleBtn.addEventListener("click", () => {
        saleForm.style.display = "none";
    });
    
    confirmSaleBtn.addEventListener("click", confirmSale);
    
    // Busca de clientes
    document.getElementById("searchClientBtn").addEventListener("click", () => {
        clientModal.style.display = "flex";
        searchClients();
    });
    
    document.getElementById("clientModalSearch").addEventListener("input", searchClients);
    
    // Busca de farmacêuticos
    document.getElementById("searchPharmacistBtn").addEventListener("click", () => {
        pharmacistModal.style.display = "flex";
        searchPharmacists();
    });
    
    document.getElementById("pharmacistModalSearch").addEventListener("input", searchPharmacists);
    
    // Busca de produtos
    document.getElementById("searchProductBtn").addEventListener("click", () => {
        productModal.style.display = "flex";
        searchProducts();
    });
    
    document.getElementById("productModalSearch").addEventListener("input", searchProducts);
    
    // Fechar modais
    document.querySelectorAll(".close").forEach(closeBtn => {
        closeBtn.addEventListener("click", function() {
            this.closest(".modal").style.display = "none";
        });
    });
    
    // Funções
    function resetSaleForm() {
        selectedClient = null;
        selectedPharmacist = null;
        saleItems = [];
        document.getElementById("selectedClient").innerHTML = "";
        document.getElementById("selectedPharmacist").innerHTML = "";
        document.getElementById("saleItemsBody").innerHTML = "";
        updateTotals();
    }
    
    async function searchClients() {
        const searchTerm = document.getElementById("clientModalSearch").value;
        const response = await fetch(`/clientes/nome/${encodeURIComponent(searchTerm)}`);
        const clients = await response.json();
        
        const resultsContainer = document.getElementById("clientModalResults");
        resultsContainer.innerHTML = "";
        
        clients.forEach(client => {
            const clientElement = document.createElement("div");
            clientElement.textContent = `${client.nome} (${client.cpf})`;
            clientElement.addEventListener("click", () => {
                selectedClient = client;
                document.getElementById("selectedClient").innerHTML = `
                    <strong>${client.nome}</strong><br>
                    CPF: ${client.cpf}<br>
                    Email: ${client.email}
                `;
                clientModal.style.display = "none";
            });
            resultsContainer.appendChild(clientElement);
        });
    }
    
    async function searchPharmacists() {
        const searchTerm = document.getElementById("pharmacistModalSearch").value;
        const response = await fetch(`/farmaceuticos/busca/${encodeURIComponent(searchTerm)}`);
        const pharmacists = await response.json();
        
        const resultsContainer = document.getElementById("pharmacistModalResults");
        resultsContainer.innerHTML = "";
        
        pharmacists.forEach(pharmacist => {
            const pharmacistElement = document.createElement("div");
            pharmacistElement.textContent = `${pharmacist.nome} (CRF: ${pharmacist.crf})`;
            pharmacistElement.addEventListener("click", () => {
                selectedPharmacist = pharmacist;
                document.getElementById("selectedPharmacist").innerHTML = `
                    <strong>${pharmacist.nome}</strong><br>
                    CRF: ${pharmacist.crf}<br>
                    Turno: ${pharmacist.turno}
                `;
                pharmacistModal.style.display = "none";
            });
            resultsContainer.appendChild(pharmacistElement);
        });
    }
    
    async function searchProducts() {
        const searchTerm = document.getElementById("productModalSearch").value;
        const response = await fetch(`/produtos/busca/${encodeURIComponent(searchTerm)}`);
        const products = await response.json();
        
        const resultsContainer = document.getElementById("productModalResults");
        resultsContainer.innerHTML = "";
        
        products.forEach(product => {
            const productElement = document.createElement("div");
            productElement.innerHTML = `
                <strong>${product.nome}</strong><br>
                Código: ${product.codigo} | Fabricante: ${product.fabricante}<br>
                Concentração: ${product.concentracao}
            `;
            productElement.addEventListener("click", async () => {
                // Buscar itens disponíveis para este produto
                const itemsResponse = await fetch(`/itens?produtoId=${product.idProduto}&disponivel=true`);
                const availableItems = await itemsResponse.json();
                
                if (availableItems.length === 0) {
                    alert("Não há itens disponíveis deste produto no estoque");
                    return;
                }
                
                // Adicionar item à venda (usando o primeiro item disponível como exemplo)
                const item = availableItems[0];
                addItemToSale(product, item);
                productModal.style.display = "none";
            });
            resultsContainer.appendChild(productElement);
        });
    }
    
    function addItemToSale(product, item) {
        const existingItem = saleItems.find(i => i.idItem === item.idItem);
        
        if (existingItem) {
            existingItem.quantity += 1;
        } else {
            saleItems.push({
                idItem: item.idItem,
                product: product,
                item: item,
                quantity: 1
            });
        }
        
        updateSaleItemsTable();
        updateTotals();
    }
    
    function updateSaleItemsTable() {
        const tableBody = document.getElementById("saleItemsBody");
        tableBody.innerHTML = "";
        
        saleItems.forEach((saleItem, index) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${saleItem.product.nome}</td>
                <td>
                    <input type="number" min="1" value="${saleItem.quantity}" 
                           data-index="${index}" class="quantity-input">
                </td>
                <td>R$ ${saleItem.item.valor.toFixed(2)}</td>
                <td>R$ ${(saleItem.item.valor * saleItem.quantity).toFixed(2)}</td>
                <td><button data-index="${index}" class="remove-item-btn">Remover</button></td>
            `;
            tableBody.appendChild(row);
        });
        
        // Adicionar event listeners para os inputs de quantidade
        document.querySelectorAll(".quantity-input").forEach(input => {
            input.addEventListener("change", (e) => {
                const index = parseInt(e.target.dataset.index);
                saleItems[index].quantity = parseInt(e.target.value);
                updateTotals();
            });
        });
        
        // Adicionar event listeners para os botões de remover
        document.querySelectorAll(".remove-item-btn").forEach(button => {
            button.addEventListener("click", (e) => {
                const index = parseInt(e.target.dataset.index);
                saleItems.splice(index, 1);
                updateSaleItemsTable();
                updateTotals();
            });
        });
    }
    
    function updateTotals() {
        const totalQuantity = saleItems.reduce((sum, item) => sum + item.quantity, 0);
        const totalValue = saleItems.reduce((sum, item) => sum + (item.item.valor * item.quantity), 0);
        
        document.getElementById("totalQuantity").textContent = totalQuantity;
        document.getElementById("totalValue").textContent = totalValue.toFixed(2);
    }
    
    async function confirmSale() {
        if (!selectedClient || !selectedPharmacist || saleItems.length === 0) {
            alert("Preencha todos os campos da venda");
            return;
        }
        
        const saleData = {
            idCliente: selectedClient.idCliente,
            idFarmaceutico: selectedPharmacist.idFuncionario,
            itens: saleItems.map(item => ({
                idItem: item.idItem,
                quantidade: item.quantity
            }))
        };
        
        try {
            const response = await fetch("/vendas", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(saleData)
            });
            
            if (response.ok) {
                alert("Venda registrada com sucesso!");
                saleForm.style.display = "none";
                loadSalesHistory();
            } else {
                throw new Error("Erro ao registrar venda");
            }
        } catch (error) {
            console.error("Erro:", error);
            alert("Erro ao registrar venda");
        }
    }
    
    async function loadSalesHistory() {
        const response = await fetch("/vendas");
        const sales = await response.json();
        
        salesHistoryBody.innerHTML = "";
        
        sales.forEach(sale => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${new Date(sale.data).toLocaleDateString()}</td>
                <td>${sale.cliente.nome}</td>
                <td>${sale.farmaceutico.nome}</td>
                <td>${sale.quantidade}</td>
                <td>R$ ${sale.valor.toFixed(2)}</td>
                <td>
                    <button onclick="viewSaleDetails(${sale.idVenda})">Detalhes</button>
                </td>
            `;
            salesHistoryBody.appendChild(row);
        });
    }
    
    window.viewSaleDetails = async (saleId) => {
        const response = await fetch(`/vendas/${saleId}`);
        const sale = await response.json();
        
        alert(`Detalhes da Venda #${sale.idVenda}\n
Data: ${new Date(sale.data).toLocaleDateString()}\n
Cliente: ${sale.cliente.nome}\n
Farmacêutico: ${sale.farmaceutico.nome}\n
Quantidade: ${sale.quantidade}\n
Valor Total: R$ ${sale.valor.toFixed(2)}\n
Itens:\n${sale.itens.map(item => `- ${item.produto.nome} (${item.quantidade}x R$ ${item.valor.toFixed(2)})`).join("\n")}`);
    };
});