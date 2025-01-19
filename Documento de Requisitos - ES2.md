UNIVERSIDADE FEDERAL DE SERGIPE  
ENGENHARIA DE SOFTWARE II

# **DOCUMENTO DE REQUISITOS**

MANOEL VICTOR LIMA MONTEIRO  
RAFAEL LAUTON SANTOS DE OLIVEIRA  
THIAGO MENEZES DE OLIVEIRA   
WILLIAM SANTOS SILVA

## **1\. Introdução**

**1.1 Objetivo**  
O propósito deste documento é fornecer uma especificação detalhada dos requisitos para o desenvolvimento de um software de gerenciamento de farmácia, conforme a norma IEEE 830/1998. Este documento servirá como base para instruir clientes, desenvolvedores e analistas sobre o desenvolvimento, verificação e validação do sistema, a fim de assegurar que todas as funcionalidades necessárias sejam implementadas.

**1.2 Escopo**  
O Sistema Pharma Manager será uma solução de software que auxiliará nas operações diárias de uma farmácia. As principais funcionalidades serão: registro de produto, controle de estoque, processamento de vendas, registro e manutenção de clientes, fornecedores e funcionários e controle das prescrições médicas. O Sistema visa automatizar processos, diminuir falhas e aumentar o desempenho financeiro da organização.

**1.3 Definições, acrônimos e abreviações**  
Sistema: Sistema Pharma Manager.  
Produto: Qualquer item registrado para venda.  
Estoque: Quantidade de produtos disponíveis na farmácia.  
Cliente: Indivíduo ou organização que realiza compras na farmácia.  
Fornecedor: Companhia ou pessoa que fornece produtos para a farmácia.  
ERS: Especificação de Requisitos de Software.  
SW: Software.  
RF: Requisito funcional.  
RNF: Requisito não funcional.

**1.4 Referências**

* IEEE Std 830-1998, "IEEE Recommended Practice for Software Requirements Specifications".  
  


**1.5 Visão geral**  
Este documento de ESR foi estruturado da seguinte forma:

1. Introdução: Descreve o objetivo, escopo, definições e referências.  
2. Descrição Geral: Apresenta uma visão geral do Sistema, descrevendo as principais funcionalidades.  
3. Especificação de Requisitos: Detalha os requisitos funcionais e não-funcionais do Sistema.  
4. Apêndice: Apresenta informações adicionais sobre as funcionalidades do sistema com o Documento de Casos de Uso.  
   

## **2\. Descrição geral**

**2.1 Perspectiva do produto**  
O Sistema Pharma Manager é um aplicativo independente desenvolvido para automatizar e otimizar as operações diárias de uma farmácia. Ele fornecerá uma interface intuitiva para os usuários, melhor controle de estoque e finanças e garantirá a conformidade com as regulamentações legais.

**2.2 Funções do produto**

* **Manutenção do Cliente:** armazenar informações do cliente e histórico de compras.  
* **Registro de produtos:** cadastrar os produtos fornecendo seu código, nome e fornecedor.  
* **Manutenção de produtos:** adicionar, editar e excluir informações do produto, incluindo nome, preço, fornecedor e data de entrada.  
* **Controle de estoque:** monitorar os níveis de estoque gerando relatórios a qualquer momento que for solicitado.

**2.3 Características do usuário**

1) **Gerente da Farmácia**  
   1) **Experiência Técnica**: Conhecimento em gestão de farmácias e informática básica.  
   2) **Responsabilidades**: Supervisão de vendas, controle financeiro, análise de relatórios, e   tomada de decisão estratégica.  
2) **Farmacêutico**  
   1) **Experiência Técnica**: Conhecimento básico em informática e sistemas de gestão.  
   2) **Responsabilidades**: Gestão de estoque de medicamentos, atendimento ao cliente, controle de receitas médicas, geração de relatórios de vendas.  
      

**2.4 Restrições gerais**  
O sistema deve cumprir os regulamentos locais e nacionais sobre a venda de medicamentos. A segurança e a privacidade dos dados devem ser garantidas, protegendo as informações dos clientes e da farmácia contra acessos não autorizados. O sistema deve prover disponibilidade dos dados dos medicamentos e clientes.

**2.5 Suposições e dependências**

1) **Conectividade com a Internet:**  Assume-se que a farmácia terá uma conexão à Internet estável e sistemas de pagamento.  
2) **Treinamento de usuário:** Assume-se que os usuários receberão treinamento adequado sobre como utilizar o sistema de forma eficaz, garantindo que podem aproveitar as funcionalidades do sistema com baixa curva de aprendizagem.  
3) **Disponibilidade de hardware:** Assume-se que a farmácia terá a infraestrutura de hardware necessária, incluindo computadores, leitores de código de barras e impressoras de recibos.

## **3\. Requisitos específicos**

**3.1 Requisitos funcionais**

* **RF1.0 Manutenção de clientes**  
  * **RF1.1** O sistema deve permitir que o farmacêutico cadastre clientes.  
  * **RF1.2** O sistema deve armazenar o histórico de compras de todos os clientes.  
  * **RF1.3** O sistema deve permitir que o farmacêutico consulte o histórico de compras do cliente.  
  * **RF1.4** O sistema deve armazenar as prescrições médicas do cliente.

* **RF2.0 Manutenção de funcionários**  
  * **RF2.1** O sistema deve permitir que o gerente cadastre funcionários.  
  * **RF2.2** O sistema deve permitir que o gerente exclua funcionários.

* **RF3.0 Manutenção de produtos**  
  * **RF3.1** O sistema deve permitir ao gerente cadastrar os produtos.  
  * **RF3.2** O sistema deve permitir aos funcionários emitir um relatório do estoque sempre que lhe for solicitado.  
  * **RF3.3** O sistema deve permitir ao gerente registrar a quantidade de entrada de um mesmo produto no estoque.  
  * **RF3.4** O sistema deve permitir ao gerente registrar a data de entrada de produtos no estoque.  
  * **RF3.5** Quando ocorre a venda de um produto, o sistema deve subtrair a quantidade vendida do estoque do produto.  
  * **RF3.6** Quando ocorre a venda de um produto, o sistema deve registrar a data da venda.  
  * **RF3.7** O sistema deve permitir ao gerente alterar o preço dos produtos.   
  * **RF3.8** O sistema deve permitir aos funcionários buscar os produtos.  
  * **RF3.9** O sistema deve permitir aos funcionários excluir os produtos do estoque.  
  * **RF3.10** O sistema deve permitir aos funcionários alterar os dados do cadastro do produto.

* **RF4.0 Manutenção de fornecedores**  
  * **RF4.1** O sistema deve permitir que o gerente cadastre fornecedores.  
  * **RF4.2** O sistema deve permitir que o gerente atualize as informações dos fornecedores.  
  * **RF4.2** O sistema deve permitir que o gerente exclua fornecedores.

* **RF5.0 Relatórios de Vendas**  
  * **RF5.1** O sistema deve permitir que o gerente emita relatórios de vendas diárias.  
  * **RF5.2** O sistema deve permitir que o gerente emita relatórios de vendas semanais.  
  * **RF5.3** O sistema deve permitir que o gerente emita relatórios de vendas mensais.  
  * **RF5.4** O sistema deve permitir que o gerente emita relatórios de vendas anuais.  
  * **RF5.5** O sistema deve informar ao funcionário a quantidade de produtos vendidos.   
  * **RF5.6** O sistema deve informar ao gerente o valor total das vendas.   
  * **RF5.7** O sistema deve informar ao funcionário os descontos aplicados.  
  * **RF5.8** O sistema deve informar ao funcionário os meios de pagamento utilizados.  
  * **RF5.9** O sistema deve permitir ao gerente a filtragem de relatórios de vendas por produto.  
  * **RF5.10** O sistema deve permitir ao gerente a filtragem de relatórios de vendas por categoria.  
  * **RF5.11** O sistema deve permitir ao gerente a filtragem de relatórios de vendas por período específico.  
  * **RF5.12** O sistema deve permitir ao gerente a exportação dos relatórios de vendas em formato PDF.  
  * **RF5.13** O sistema deve permitir ao gerente a exportação dos relatórios de vendas em formato Excel.  
  * **RF5.14** O sistema deve permitir ao gerente emitir um relatório comparativo entre receitas e despesas.  
  * **RF5.15** O sistema deve destacar o lucro no relatório comparativo entre receitas e despesas.  
  * **RF5.16** O sistema deve destacar o prejuízo no relatório comparativo entre receitas e despesas  
      
* **RF6.0 Relatórios de Despesas**  
  * **RF6.1** O sistema deve permitir que o gerente registre as despesas das aquisições de produtos.  
  * **RF6.2** O sistema deve permitir que o gerente registre as despesas dos salários dos funcionários.  
  * **RF6.3** O sistema deve permitir que o gerente registre as despesas administrativas.  
  * **RF6.4** O sistema deve permitir que o gerente emita o relatório de despesa mensal.  
  * **RF6.5** O sistema deve permitir que o gerente emita o relatório de despesa anual.  
  * **RF6.6** O sistema deve permitir que o gerente emita o relatório das despesas por categoria.  
  * **RF6.7** O sistema deve permitir que o gerente compare as despesas com o orçamento planejado.  
  * **RF6.8** O sistema deve permitir que o gerente exporte os relatórios de despesas em formatos PDF.  
  * **RF6.9** O sistema deve permitir que o gerente exporte os relatórios de despesas em formatos Excel.

    

**3.2 Requisitos não funcionais**

* **RNF1.0 Usabilidade**  
  * **RNF1.1** Os funcionários devem ser capazes de utilizar o sistema cometendo no máximo 2 erros por hora após 4 horas de treinamento.  
  * **RNF1.2** O sistema deve possuir apenas uma tela para cada tipo de usuário.  
  * **RNF1.3** O sistema deve organizar as funcionalidades disponíveis para o usuário em menus.  
* **RNF2.0 Performance**  
  * **RNF2.1** O sistema não pode ficar indisponível por mais de 5 minutos por semana.  
  * **RNF2.2** O sistema deve ser compatível com sistemas operacionais Windows 10, a partir da versão 22H2, e Ubuntu, a partir da versão 22.04LTS.  
  * **RNF2.3** O sistema deve suportar a geração simultânea de relatórios para até cinco usuários.  
  * **RNF2.4** O sistema deve ser capaz de gerar relatórios para um período de até um ano em no máximo 10 segundos.  
* **RNF3.0 Segurança**  
  * **RNF3.1** O sistema deve ser capaz de recuperar os dados de uma operação em 90% das falhas.  
  * **RNF3.2** O sistema deve autenticar apenas usuários autorizados.  
  * **RNF3.3** O acesso à funcionalidade de relatórios de vendas e despesas deve ser restrito a usuários com permissões administrativas, assegurando que informações financeiras estejam protegidas.  
  * **RNF3.4** O sistema deve permitir que funcionários acessem apenas as informações relacionadas ao seu cargo.

	

## **4\. Apêndice**

**4.1 Documento de Casos de Uso**

* **Generalização:** Funcionário é uma generalização de Farmacêutico e Gerente, apontando que tanto Farmacêutico como Gerente são tipos específicos de Funcionários no sistema.

![][image1]

* **Caso de Uso Geral:** Vender produto


| Ator Primário | Farmacêutico |  |
| ----- | ----- | ----- |
| **Descrição** | O farmacêutico deseja vender um produto para um cliente. |  |
| **Ator Secundário** | Cliente |  |
| **Fluxo Principal** |  |  |
| **Farmacêutico** | **Cliente** | **Sistema** |
| 1\. O farmacêutico acessa o sistema. |  |  |
|  |  | 2\. O sistema autoriza sua entrada. |
| 3\. O farmacêutico navega até a seção de Vendas. |  |  |
|  | 4\. O cliente informa seu CPF. |  |
| 5\. O farmacêutico insere o CPF do cliente. |  |  |
|  |  | 6\. O sistema valida o cliente. |
| 7\. O farmacêutico registra os produtos que serão comprados. |  |  |
|  | 8\. O cliente paga. |  |
|  |  | 9\. O sistema registra a compra. |
| **Fluxo Alternativo** |  |  |
|  |  | 6.1 O sistema informa que o cliente não possui cadastro. |
|  |  | 6.2 O sistema vai para a seção de Cadastro de cliente. |
| 6.3 O farmacêutico cadastra o cliente.  |  |  |


* **Caso de Uso 1:** Gerar relatórios de venda


| Ator Primário | Gerente |
| ----- | ----- |
| **Descrição** | O gerente da farmácia deseja gerar um relatório de vendas para um período específico. |
| **Ator Secundário** | \- |
| **Fluxo Principal** |  |
| **Ação do Ator** | **Resposta do Sistema** |
| 1\. O gerente da farmácia acessa o sistema Pharma Manager e faz login com suas credenciais. |  |
|  | 2\. O sistema autoriza sua entrada |
| 3\. O gerente navega até a seção de Relatórios Financeiros. |  |
|  | 4\. O sistema exibe uma opção para gerar relatório de vendas. |
| 5\. O gerente seleciona os filtros desejados (período, produto, cliente). |  |
| 6\. O gerente clica em “Gerar relatório". |  |
|  | 7\. O sistema processa e exibe o relatório. |
| 8\. O gerente escolhe a opção de exportar o relatório em formato PDF ou Excel |  |
|  | 9\. O sistema exporta o relatório |
| **Fluxo Alternativo** |  |
|   | 5.1 O sistema exibe uma mensagem de erro informando que não há registros disponíveis para o período selecionado. |
|  | 7.1 O sistema detecta que não há dados suficientes para gerar o relatório para o período selecionado. |





* **Caso de Uso 2:** Registrar Despesas


| Ator Primário | Gerente |
| ----- | ----- |
| **Descrição** | O gerente da farmácia deseja registrar uma nova despesa operacional no sistema. |
| **Ator Secundário** | \- |
| **Fluxo Principal** |  |
| **Ação do Ator** | **Resposta do Sistema** |
| 1\. O gerente da farmácia acessa o sistema Pharma Manager e faz login com suas credenciais. |  |
|  | 2\. O sistema autoriza sua entrada |
| 3\. O gerente navega até a seção de Despesas. |  |
|  | 4\. O sistema exibe um formulário para registro de despesas. |
| 5\. O gerente preenche o formulário com os detalhes da despesa, incluindo categoria, valor, e data. |  |
|  | 6\. O sistema salva as informações e atualiza os relatórios de despesas. |
| **Fluxo Alternativo** |  |
| 5.1 Se o gerente tentar registrar uma despesa com informações incompletas. |  |
|  | 5.2 O sistema emite um alerta e impede o registro até que todas as informações sejam fornecidas. |


  

			

* **Caso de Uso 3:** Comparar Receitas e Despesas


| Ator Primário | Gerente |
| ----- | ----- |
| **Descrição** | O gerente da farmácia deseja comparar receitas e despesas para determinar o lucro ou prejuízo em um período específico. |
| **Ator Secundário** | \- |
| **Fluxo Principal** |  |
| **Ação do Ator** | **Resposta do Sistema** |
| 1\. O gerente da farmácia acessa o sistema Pharma Manager e faz login com suas credenciais. |  |
|  | 2\. O sistema autoriza sua entrada |
| 3\. O gerente navega até a seção de Relatórios Financeiros. |  |
|  | 4\. O sistema exibe uma opção para comparar receitas e despesas. |
| 5\. O gerente seleciona o período de tempo desejado para a comparação. |  |
|  | 6\. O sistema processa as informações e gera um relatório comparativo. |
|  | 7\. O sistema exibe o relatório na tela com gráficos que destacam o lucro ou prejuízo. |
| 8\. O gerente escolhe a opção de exportar o relatório em formato PDF ou Excel |  |
|  | 9\. O sistema exporta o relatório |
| **Fluxo Alternativo** |  |
| 5.1 O gerente escolhe um período sem dados suficientes para gerar um relatório  |  |
|  | 5.2 O sistema exibe uma mensagem de erro informando que não há registros disponíveis para o período selecionado. |


  

*    **Caso de Uso 4:** Cadastrar cliente

| Ator Primário | Farmacêutico |  |
| ----- | ----- | ----- |
| **Descrição** | O farmacêutico deseja realizar o cadastro de um cliente. |  |
| **Ator Secundário** | Cliente |  |
| **Fluxo Principal** |  |  |
| **Farmacêutico** | **Cliente** | **Sistema** |
| 1\. O farmacêutico acessa o sistema. |  |  |
|  |  | 2\. Valida suas informações de login. |
| 3\. Acessa a seção de cadastro. |  |  |
|  | 4\. Informa seu CPF. |  |
|  |  | 5\. Valida a existência do CPF do cliente. |
|  | 6\. Informa outros dados solicitados. |  |
|  |  | 7\. Armazena as informações do cliente. |
|  |  | 8\. Finaliza o cadastro. |
| **Fluxo Alternativo** |  |  |
| **Farmacêutico** | **Cliente** | **Sistema** |
|  |  | 5.1 Retorna que o cliente já possui um cadastro. |
| 9\. Navega até a seção de Excluir cliente. |  |  |
| 9.1 Insere CPF do cliente. |  |  |
|  |  | 9.2 Valida CPF do cliente. |
|  |  | 9.3 Excluir o cliente. |

* **Caso de Uso 5:** Cadastrar produto


| Ator Primário | Gerente |
| ----- | ----- |
| **Descrição** | O gerente da farmácia deseja fazer o cadastro de um produto. |
| **Ator Secundário** | \- |
| **Fluxo Principal** |  |
| **Ação do Ator** | **Resposta do Sistema** |
| 1\. O gerente da farmácia acessa o sistema Pharma Manager e faz login com suas credenciais. |  |
|  | 2\. O sistema autoriza sua entrada |
| 3\. O gerente navega até a seção de Cadastro de Produto. |  |
|  | 4\. O sistema exibe uma tela para preencher o cadastro. |
| 5\. O gerente preenche os dados do cadastro. |  |
|  | 6\. O sistema salva os dados. |
| 7\. O gerente clica em adicionar os produtos. |  |
|  | 8\. O sistema adiciona os dados ao estoque. |
| **Fluxo Alternativo** |  |
| 1.O gerente preenche os dados do cadastro. |  |
|  | 2.Se o produto já está cadastrado o sistema exibe um aviso.  |


  


* **Caso de Uso 6:** Gerar relatório de estoque


| Ator Primário | Funcionário |
| ----- | ----- |
| **Descrição** | O funcionário deseja requisitar o relatório do estoque. |
| **Ator Secundário** | \- |
| **Fluxo Principal** |  |
| **Ação do Ator** | **Resposta do Sistema** |
| 1\. O funcionário acessa o sistema Pharma Manager e faz login com suas credenciais. |  |
|  | 2\. O sistema autoriza sua entrada |
| 3\. O funcionário requisita a geração de um relatório ao navegar até a seção de Controle de estoque. |  |
|  | 4\. O sistema mostra na tela um relatório com todos os produtos do estoque separados pelo nome e organizados em ordem alfabética. |
| 5\. O funcionário pode filtrar os dados do relatório. |  |
|  | 6\. O sistema mostra na tela um relatório com os produtos do estoque que possuem o atributo requisitado no filtro, separados pelo nome e organizados em ordem alfabética. |
| **Fluxo Alternativo** |  |
| 1\. O funcionário pode filtrar os dados do relatório. |  |
|  | 2\. O sistema reconhece que não há nenhum produto com os filtros solicitados e exibe um aviso na tela. |


* **Caso de Uso 7:** Gerenciar produtos.


| Ator Primário | Gerente |
| ----- | ----- |
| **Descrição** | O gerente da farmácia deseja alterar o cadastro de um produto ou excluir um produto do estoque. |
| **Ator Secundário** | \- |
| **Fluxo Principal** |  |
| **Ação do Ator** | **Resposta do Sistema** |
| 1\. O gerente acessa o sistema Pharma Manager e faz login com suas credenciais. |  |
|  | 2\. O sistema autoriza sua entrada |
| 3\. O gerente pode buscar um produto específico pelo seu nome ou código. |  |
|  | 4.O sistema mostra na tela o produto buscado. |
| 5\. O gerente pode selecionar o produto e alterar os dados do cadastro, a data de entrada e o preço. |  |
|  | 6\. O estoque é atualizado e o sistema salva as informações. |
| 7.O gerente pode excluir o produto buscado. |  |
|  | 8\. O sistema apaga aquele produto do estoque |
| **Fluxo Alternativo** |  |
| 1\. O gerente pode buscar um produto específico pelo seu nome ou código. |  |
|  | 2\. Se o produto não estiver cadastrado, o sistema gera um aviso. |


  

* **Caso de Uso 8:** Cadastrar fornecedor


| Ator Primário | Gerente |  |
| ----- | ----- | ----- |
| **Descrição** | O gerente deseja cadastrar um fornecedor. |  |
| **Ator Secundário** | Fornecedor |  |
| **Fluxo Principal** |  |  |
| **Gerente** | **Fornecedor** | **Sistema** |
| 1\. O gerente acessa o sistema. |  |  |
|  |  | 2\. O sistema autoriza sua entrada. |
| 3\. O gerente navega até a seção de Cadastro de Fornecedores. |  |  |
|  | 4\. O fornecedor informa seus dados. |  |
| 5\. O gerente preenche os dados do cadastro. |  |  |
| 6\. O gerente clica no botão “Cadastrar Fornecedor”. |  |  |
|  |  | 7\. O sistema salva as informações e finaliza o cadastro do fornecedor. |
| **Fluxo Alternativo** |  |  |
|  |  | 7.1 Se o fornecedor já estiver cadastrado, o sistema deve retornar essa informação. |
| 8\. O gerente deseja excluir um fornecedor. |  |  |
| 8.1 O gerente navega até a seção Excluir Fornecedor.  |  |  |
| 8.2 O gerente indica o fornecedor a ser excluído. |  |  |
| 8.3 O gerente clica no botão “Excluir Fornecedor”. |  |  |
|  |  | 8.4 O sistema verifica se há alguma pendência daquele fornecedor. |
|  |  | 8.4.1 Se houver pendência, o sistema deve retornar essa informação e abortar a operação. |
|  |  | 8.4.2 Se não houver pendência, o sistema exclui o fornecedor. |





  

* **Caso de Uso 9:** Cadastrar funcionário

| Ator Primário | Gerente |  |
| ----- | ----- | ----- |
| **Descrição** | O gerente deseja cadastrar um funcionário. |  |
| **Ator Secundário** | Funcionário |  |
| **Fluxo Principal** |  |  |
| **Gerente** | **Funcionário** | **Sistema** |
| 1\. O gerente acessa o sistema. |  |  |
|  |  | 2\. O sistema autoriza sua entrada. |
| 3\. O gerente navega até a seção de Cadastro de Funcionários. |  |  |
|  | 4\. O funcionário informa seus dados. |  |
| 5\. O gerente preenche os dados do cadastro. |  |  |
| 6\. O gerente seleciona o tipo de funcionário. |  |  |
|  7\. O gerente clica no botão “Cadastrar Funcionário”. |  |  |
|  |  | 8\. O sistema salva as informações e finaliza o cadastro do funcionário. |
| **Fluxo Alternativo** |  |  |
|  |  | 8.1 Se o funcionário já estiver cadastrado, o sistema deve retornar essa informação. |
| 9\. O gerente deseja excluir um funcionário. |  |  |
| 9.1 O gerente navega até a seção de Excluir Funcionário.  |  |  |
| 9.2 O gerente indica o funcionário a ser excluído. |  |  |
| 9.3 O gerente clica no botão “Excluir Funcionário”. |  |  |
|  |  | 9.4 O sistema revoga as permissões de acesso do funcionário. |
|  |  | 9.5 O sistema exclui o funcionário. |





  


  


  


  


  


  


  


  


  


  


  
![][image2]

[image1]: <data:image/png;base64,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>

[image2]: <data:image/png;base64,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2X53uFuo44gUatqjLzVs10V0HfHdcTwGj7/y99zeun+82O/66cvFGyp3HUDSgTfKakzX//3f/4kJNU+cOEH79u2jLVu20KRJk2jQoEHUrl07qlGjBrVp04YGDBhAEydOpCVLltDevXvp+PHjYuLOYN8tF65R1QvUvCofo+qL68+UVrr4zYorS1wRGL5yq316hIRU+wzojukRuDolZkDn6RFy7wrYDOgAAFBaykq6ipNPP/2ULly4QMeOHaP77ruPFi5cSOPHj6e+fftS5cqVqU6dOtShQwcaOnQoZWVl0bZt22j//v303HPP0ddff02//fab9ZRIKaOqF6h5VT5G1RfXnylKunjsE4+B4kkQeayDmB4hKV2Ml+LJD8X0CC3a2WdAn5UtxlbxGCueAR3TIwAAdCeQ0lVUfvrpJ/rggw/o5Zdfpp07d9L69etp7ty5NHr0aGrSpAldd9111LBhQ+fYs9WrV9P27dtFt+if/vQn+uGHH6ynRIqIql6g5lX5GFVfXH+GpavvjCxx154xnoXv5ms7cBRVq9NA3OXHd/s17d5XdN/wXYB8NyDfGciTH1r/QAEAQCihknQVJyxWxtizO+64g9asWSMkrFu3bkLIWMx69uwpRI2FLTc3l44cOSJE7v3337eeLuyjqheoeVU+RtUXt6j885//pA8//JDOnTsn7qpZsWIFpaWlibECXJKuV6+euPOGB33ecMMNdP/994txAlyOLqrSBQAA4Yxu0lXa8Nixzz//XCyLw+8TeXl5tHTpUjHOjMebVa9eXYw/43FoPCZt2bJlYp8HHnhAjFn74osvxPi1UImqXqDkVW3atMmxlpp/CFT4F/izzz6j1157jR5//HHKyckRpeNhw4aJ/n3+DxAdHU1xcXHirhq+a+bo0aP06quvFjn4EtIFAADeCTfpKk74g/7HH39MTz75JO3evZtuuukmysjIoOuvv55atGhBlSpVorp164r3pKlTp4pCwMMPP0ynTp2it99+m7755hvrKQMWf3sBnz8QCZyB+DH8ggUrXHXiTwl/+MMfxADKefPmUUJCgigJ8y9wq1ataODAgTR58mS67bbbROn47Nmzon+/NIF0AQCAdyBdJQtPk8G9LzxtBhcCeCqNxMREMa1GVFSUmGqje/fuNHLkSDH9BneFPvroo/Tiiy/Sn//8Z+vpgpZgekFhUfOq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>