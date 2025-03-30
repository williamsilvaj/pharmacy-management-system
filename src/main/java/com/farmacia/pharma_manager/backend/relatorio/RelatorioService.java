package com.farmacia.pharma_manager.backend.relatorio;

import com.farmacia.pharma_manager.backend.despesa.Despesa;
import com.farmacia.pharma_manager.backend.venda.Venda;

import java.time.LocalDate;
import java.util.List;

public class RelatorioService {

    // Ajusta a data de início para a data mínima entre vendas e despesas, se necessário
    private LocalDate ajustarDataInicio(List<Venda> vendas, List<Despesa> despesas, LocalDate dataInicio) {
        if (!vendas.isEmpty()) {
            LocalDate minVenda = vendas.stream().map(Venda::getData).min(LocalDate::compareTo).orElse(dataInicio);
            dataInicio = minVenda.isBefore(dataInicio) ? minVenda : dataInicio;
        }
        if (!despesas.isEmpty()) {
            LocalDate minDespesa = despesas.stream().map(Despesa::getData).min(LocalDate::compareTo).orElse(dataInicio);
            dataInicio = minDespesa.isBefore(dataInicio) ? minDespesa : dataInicio;
        }
        return dataInicio;
    }

    // Ajusta a data de fim para a data máxima entre vendas e despesas, se necessário
    private LocalDate ajustarDataFim(List<Venda> vendas, List<Despesa> despesas, LocalDate dataFim) {
        if (!vendas.isEmpty()) {
            LocalDate maxVenda = vendas.stream().map(Venda::getData).max(LocalDate::compareTo).orElse(dataFim);
            dataFim = maxVenda.isAfter(dataFim) ? maxVenda : dataFim;
        }
        if (!despesas.isEmpty()) {
            LocalDate maxDespesa = despesas.stream().map(Despesa::getData).max(LocalDate::compareTo).orElse(dataFim);
            dataFim = maxDespesa.isAfter(dataFim) ? maxDespesa : dataFim;
        }
        return dataFim;
    }
}
