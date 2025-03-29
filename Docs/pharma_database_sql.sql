
-- Google Docs with the SQL code: https://docs.google.com/document/d/1OWprbhl9NUqkN31fnFTh3jOwb9HmJSusQdX7yXSN5s4/edit?tab=t.0

-- -----------------------------------------------------
-- Schema pharmabd
-- -----------------------------------------------------
CREATE SCHEMA pharmabd DEFAULT CHARACTER SET utf8;

-- -----------------------------------------------------
-- Table pharmabd.cargo
-- -----------------------------------------------------
CREATE TABLE pharmabd.cargo (
  idCargo INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(45) NOT NULL,
  dataContratacao DATE NOT NULL,
  salario DOUBLE NOT NULL,
  PRIMARY KEY (idCargo)
);

-- -----------------------------------------------------
-- Table pharmabd.endereco
-- -----------------------------------------------------
CREATE TABLE pharmabd.endereco (
  idEndereco INT NOT NULL AUTO_INCREMENT,
  rua VARCHAR(45) NOT NULL,
  numero VARCHAR(45) NOT NULL,
  bairro VARCHAR(45) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  estado VARCHAR(45) NOT NULL,
  cep VARCHAR(45) NOT NULL,
  PRIMARY KEY (idEndereco)
);

-- -----------------------------------------------------
-- Table pharmabd.funcionario
-- -----------------------------------------------------
CREATE TABLE pharmabd.funcionario (
  idFuncionario INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  telefone VARCHAR(45) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  idCargo INT NOT NULL,
  idEndereco INT NOT NULL,
  PRIMARY KEY (idFuncionario),
  UNIQUE (cpf),
  CONSTRAINT fk_Funcionario_Cargo
    FOREIGN KEY (idCargo)
    REFERENCES pharmabd.cargo (idCargo)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT fk_Funcionario_Endereco
    FOREIGN KEY (idEndereco)
    REFERENCES pharmabd.endereco (idEndereco)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table pharmabd.gerente
-- -----------------------------------------------------
CREATE TABLE pharmabd.gerente (
  idFuncionario INT PRIMARY KEY,
  funcionariosSupervisionados VARCHAR(255) NULL,
  FOREIGN KEY (idFuncionario) REFERENCES pharmabd.funcionario(idFuncionario) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Adiciona fk_Gerente em pharmabd.funcionario
-- -----------------------------------------------------
ALTER TABLE pharmabd.funcionario
ADD COLUMN idGerente INT NULL,
ADD CONSTRAINT fk_Funcionario_Gerente
    FOREIGN KEY (idGerente)
    REFERENCES pharmabd.gerente (idFuncionario)
    ON DELETE NO ACTION
    ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table pharmabd.farmaceutico
-- -----------------------------------------------------
CREATE TABLE pharmabd.farmaceutico (
  idFuncionario INT PRIMARY KEY,
  turno VARCHAR(45) NOT NULL,
  crf VARCHAR(45) NOT NULL,
  cargaHoraria FLOAT NOT NULL,
  FOREIGN KEY (idFuncionario) REFERENCES pharmabd.funcionario(idFuncionario) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table pharmabd.despesa
-- -----------------------------------------------------
CREATE TABLE pharmabd.despesa (
  idDespesa INT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(255) NOT NULL,
  data DATE NOT NULL,
  valor DOUBLE NOT NULL,
  idGerente INT NOT NULL,
  PRIMARY KEY (idDespesa),
  CONSTRAINT fk_Despesa_Gerente
    FOREIGN KEY (idGerente)
    REFERENCES pharmabd.gerente (idFuncionario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table pharmabd.cliente
-- -----------------------------------------------------
CREATE TABLE pharmabd.cliente (
  idCliente INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  email VARCHAR(45) NOT NULL,
  telefone VARCHAR(45) NOT NULL,
  idEndereco INT NOT NULL,
  PRIMARY KEY (idCliente),
  UNIQUE (cpf),
  UNIQUE (email),
  UNIQUE (telefone),
  CONSTRAINT fk_Cliente_Endereco
    FOREIGN KEY (idEndereco)
    REFERENCES pharmabd.endereco (idEndereco)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table pharmabd.venda
-- -----------------------------------------------------
CREATE TABLE pharmabd.venda (
  idVenda INT NOT NULL AUTO_INCREMENT,
  quantidade INT NOT NULL,
  valor DOUBLE NOT NULL,
  data DATE NOT NULL,
  idFarmaceutico INT NOT NULL,
  idCliente INT NOT NULL,
  PRIMARY KEY (idVenda),
  CONSTRAINT fk_Venda_Farmaceutico
    FOREIGN KEY (idFarmaceutico)
    REFERENCES pharmabd.farmaceutico (idFuncionario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Venda_Cliente
    FOREIGN KEY (idCliente)
    REFERENCES pharmabd.cliente (idCliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table pharmabd.produto
-- -----------------------------------------------------
CREATE TABLE pharmabd.produto (
  idProduto INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  codigo INT NOT NULL,
  fabricante VARCHAR(45) NOT NULL,
  concentracao FLOAT NOT NULL,
  PRIMARY KEY (idProduto),
  UNIQUE (codigo)
);

-- -----------------------------------------------------
-- Table pharmabd.estoque
-- -----------------------------------------------------
CREATE TABLE pharmabd.estoque (
  idEstoque INT NOT NULL AUTO_INCREMENT,
  quantidade INT NOT NULL,
  dataEntrada DATE NOT NULL,
  PRIMARY KEY (idEstoque)
);

-- -----------------------------------------------------
-- Table pharmabd.item
-- -----------------------------------------------------
CREATE TABLE pharmabd.item (
  idItem INT NOT NULL AUTO_INCREMENT,
  valor DOUBLE NOT NULL,
  dataVencimento DATE NOT NULL,
  idProduto INT NOT NULL,
  Venda_idVenda INT NULL,
  idEstoque INT NULL,
  PRIMARY KEY (idItem),
  CONSTRAINT fk_Item_Produto
    FOREIGN KEY (idProduto)
    REFERENCES pharmabd.produto (idProduto)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Item_Venda
    FOREIGN KEY (Venda_idVenda)
    REFERENCES pharmabd.venda (idVenda)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Item_Estoque
    FOREIGN KEY (idEstoque)
    REFERENCES pharmabd.estoque (idEstoque)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table pharmabd.fornecedor
-- -----------------------------------------------------
CREATE TABLE pharmabd.fornecedor (
  idFornecedor INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  cnpj VARCHAR(14) NOT NULL,
  email VARCHAR(45) NOT NULL,
  status TINYINT NOT NULL,
  idEndereco INT NOT NULL,
  PRIMARY KEY (idFornecedor),
  CONSTRAINT fk_Fornecedor_Endereco
    FOREIGN KEY (idEndereco)
    REFERENCES pharmabd.endereco (idEndereco)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table pharmabd.fornecedor_item
-- -----------------------------------------------------
CREATE TABLE pharmabd.fornecedor_item (
  idFornecedor INT NOT NULL,
  idItem INT NOT NULL,
  PRIMARY KEY (idFornecedor, idItem),
  CONSTRAINT fk_FornecedorItem_Fornecedor
    FOREIGN KEY (idFornecedor)
    REFERENCES pharmabd.fornecedor (idFornecedor)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_FornecedorItem_Item
    FOREIGN KEY (idItem)
    REFERENCES pharmabd.item (idItem)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
