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
                // Verificar quantidade disponível primeiro
                try {
                    const disponibilidadeResponse = await fetch(`/itens/disponiveis/quantidade/${product.idProduto}`);
                    if (!disponibilidadeResponse.ok) {
                        throw new Error("Erro ao verificar estoque");
                    }
                    
                    const { quantidadeDisponivel } = await disponibilidadeResponse.json();
                    
                    // Verificar itens já adicionados para este produto
                    const alreadyAdded = saleItems.filter(item => item.product.idProduto === product.idProduto)
                                                .reduce((sum, item) => sum + item.quantity, 0);
                    
                    const available = quantidadeDisponivel - alreadyAdded;
                    
                    if (available <= 0) {
                        alert(`Não há mais unidades disponíveis deste produto no estoque`);
                        return;
                    }
                    
                    // Pedir quantidade desejada com limite máximo
                    const quantity = prompt(`Quantidade desejada de ${product.nome} (Disponível: ${available}):`, "1");
                    if (!quantity || isNaN(quantity) || parseInt(quantity) <= 0) {
                        alert("Quantidade inválida");
                        return;
                    }
                    
                    const desiredQuantity = parseInt(quantity);
                    
                    if (desiredQuantity > available) {
                        alert(`Quantidade solicitada (${desiredQuantity}) excede o disponível (${available})`);
                        return;
                    }
                    
                    // Buscar os itens disponíveis
                    const itemsResponse = await fetch(`/itens/disponiveis/${product.idProduto}?limit=${desiredQuantity}`);
                    const availableItems = await itemsResponse.json();
                    
                    if (availableItems.length < desiredQuantity) {
                        alert(`Quantidade indisponível no momento. Disponível agora: ${availableItems.length}`);
                        return;
                    }
                    
                    // Adicionar itens à venda
                    for (const item of availableItems) {
                        addItemToSale(product, item);
                    }
                    
                    productModal.style.display = "none";
                } catch (error) {
                    console.error("Erro:", error);
                    alert("Erro ao verificar estoque");
                }
            });
            resultsContainer.appendChild(productElement);
        });
    }

    function addItemToSale(product, item) {
        // Verifica se já existe um item com o mesmo produto
        const existingItem = saleItems.find(i => i.product.idProduto === product.idProduto);
        
        if (existingItem) {
            // Se já existe, apenas incrementa a quantidade
            existingItem.quantity += 1;
            existingItem.idItem = item.idItem; // Atualiza com o último item adicionado
        } else {
            // Se não existe, cria um novo item
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
                <td>${saleItem.quantity}</td>
                <td>R$ ${saleItem.item.valor.toFixed(2)}</td>
                <td>R$ ${(saleItem.item.valor * saleItem.quantity).toFixed(2)}</td>
                <td><button data-index="${index}" class="remove-item-btn">Remover</button></td>
            `;
            tableBody.appendChild(row);
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
        alert("Selecione cliente, farmacêutico e pelo menos um produto");
        return;
    }

    const saleData = {
        idCliente: selectedClient.idCliente,
        idFarmaceutico: selectedPharmacist.id,
        itens: saleItems.map(item => ({
            idProduto: item.product.idProduto,
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

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || "Erro ao registrar venda");
        }

        const result = await response.json();
        alert(`Venda #${result.idVenda} registrada com sucesso!`);
		saleForm.style.display = "none";
        resetSaleForm();
        loadSalesHistory();
        
    } catch (error) {
        console.error("Erro na venda:", error);
        alert(`Erro: ${error.message}`);
    }
}
    
    // Adicione esta função para desfazer venda
	window.undoSale = async (saleId) => {
		if (confirm("Tem certeza que deseja desfazer esta venda?\nEsta ação não pode ser desfeita.")) {
			try {
				const response = await fetch(`/vendas/${saleId}/desfazer`, {
					method: "POST"
				});

				if (!response.ok) {
					throw new Error("Erro ao desfazer venda");
				}

				alert("Venda desfeita com sucesso!");
				loadSalesHistory(); // Atualiza a tabela
			} catch (error) {
				console.error("Erro:", error);
				alert(`Erro ao desfazer venda: ${error.message}`);
			}
		}
	};

	// Atualize a função loadSalesHistory para incluir o botão Desfazer
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
					<button onclick="undoSale(${sale.idVenda})">Desfazer</button>
				</td>
			`;
			salesHistoryBody.appendChild(row);
		});
	}
    
    window.viewSaleDetails = async (saleId) => {
		try {
			// Busca os dados da venda
			const vendaResponse = await fetch(`/vendas/${saleId}`);
			if (!vendaResponse.ok) throw new Error("Erro ao buscar dados da venda");
			const venda = await vendaResponse.json();

			// Busca os itens da venda
			const itensResponse = await fetch(`/itens/por-venda/${saleId}`);
			if (!itensResponse.ok) throw new Error("Erro ao buscar itens da venda");
			const itens = await itensResponse.json();

			// Formata os detalhes dos itens
			let itemsDetails = "Nenhum item encontrado";
			if (itens && itens.length > 0) {
				itemsDetails = itens.map(item => 
					`- ${item.produto.nome} (R$ ${item.valor.toFixed(2)})`
				).join("\n");
			}

			// Exibe os detalhes
			alert(`Detalhes da Venda #${venda.idVenda}	
Itens:\n${itemsDetails}`);

		} catch (error) {
			console.error("Erro:", error);
			alert(`Erro ao carregar detalhes: ${error.message}`);
		}
	};
});