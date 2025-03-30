package com.farmacia.pharma_manager.backend.venda;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.farmacia.pharma_manager.backend.venda.Venda;
import com.farmacia.pharma_manager.backend.item.Item;
import com.farmacia.pharma_manager.backend.item.ItemRepository;
import com.farmacia.pharma_manager.backend.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Service
public class RelatorioVendasService {

    @Autowired
    private VendaRepository vendaRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    public byte[] gerarRelatorioPDF(LocalDate dataInicio, LocalDate dataFim) throws IOException {
        List<Venda> vendas = vendaRepository.findByDataBetween(dataInicio, dataFim);
        
        if (vendas.isEmpty()) {
            throw new RuntimeException("Nenhuma venda encontrada no período selecionado");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document document = new Document(pdfDoc);

        // 1. Cabeçalho
        Paragraph cabecalho = new Paragraph("PharmaManager - Relatório de Vendas")
            .setTextAlignment(TextAlignment.CENTER)
            .setBold()
            .setFontSize(16);
        document.add(cabecalho);

        // 2. Período e data de emissão
        document.add(new Paragraph("Período: " + dataInicio + " a " + dataFim));
        document.add(new Paragraph("Data de emissão: " + LocalDate.now()));
        document.add(new Paragraph("\n")); // Espaçamento

        // 3. Dados consolidados
        int totalItens = vendas.stream().mapToInt(Venda::getQuantidade).sum();
        double totalVendas = vendas.stream().mapToDouble(Venda::getValor).sum();
        
        document.add(new Paragraph("Total de vendas: R$ " + String.format("%.2f", totalVendas)));
        document.add(new Paragraph("Quantidade total de itens vendidos: " + totalItens));
        document.add(new Paragraph("Número total de transações: " + vendas.size()));
        document.add(new Paragraph("\n"));
		
		document.add(new Paragraph("Detalhes das vendas: ").setBold());
        // 4. Tabela de vendas
        float[] columnWidths = {2, 2, 2, 2};
        Table tabelaVendas = new Table(columnWidths);
        
        // Cabeçalho da tabela
        tabelaVendas.addCell(new Paragraph("Data").setBold());
        tabelaVendas.addCell(new Paragraph("N° Venda").setBold());
        tabelaVendas.addCell(new Paragraph("Qtd. Itens").setBold());
        tabelaVendas.addCell(new Paragraph("Valor Total").setBold());
        
        // Dados das vendas
        vendas.forEach(venda -> {
            tabelaVendas.addCell(venda.getData().toString());
            tabelaVendas.addCell(venda.getIdVenda().toString());
            tabelaVendas.addCell(venda.getQuantidade().toString());
            tabelaVendas.addCell("R$ " + String.format("%.2f", venda.getValor()));
        });
        
        document.add(tabelaVendas);
        document.add(new Paragraph("\n"));

        // 5. Produtos mais vendidos
        Map<String, Long> produtosMaisVendidos = calcularProdutosMaisVendidos(vendas);
        
        Paragraph topProdutos = new Paragraph("Produtos mais vendidos:").setBold();
        document.add(topProdutos);
        
        Table tabelaProdutos = new Table(2);
        tabelaProdutos.addCell(new Paragraph("Produto").setBold());
        tabelaProdutos.addCell(new Paragraph("Quantidade").setBold());
        
        produtosMaisVendidos.forEach((produto, quantidade) -> {
            tabelaProdutos.addCell(produto);
            tabelaProdutos.addCell(quantidade.toString());
        });
        
        document.add(tabelaProdutos);
        document.close();
        
        return baos.toByteArray();
    }

    private Map<String, Long> calcularProdutosMaisVendidos(List<Venda> vendas) {
        return vendas.stream()
            .flatMap(v -> itemRepository.findByVendaIdVenda(v.getIdVenda()).stream())
            .collect(Collectors.groupingBy(
                item -> item.getProduto().getNome(),
                Collectors.counting()
            ))
            .entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Ordena decrescente
            .limit(3)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
    }
}