package com.farmacia.pharma_manager.backend.relatorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
public class RelatorioController {

    @Autowired
    private RelatorioReceitaDespesaService relatorioReceitaDespesaService;

    // Endpoint para gerar o relatório em PDF
    @GetMapping("/relatorio")
    public ResponseEntity<byte[]> gerarRelatorio(
            @RequestParam("dataInicio") String dataInicio,
            @RequestParam("dataFim") String dataFim) throws IOException {

        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);

        // Gera o relatório em PDF
        byte[] pdf = relatorioReceitaDespesaService.gerarRelatorioPDF(inicio, fim);

        // Retorna o PDF com os cabeçalhos apropriados para renderização
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "inline; filename=relatorio.pdf")
                .body(pdf);
    }
}
