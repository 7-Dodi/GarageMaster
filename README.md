# Projeto Web para Operações de Loja de Auto-Peças

Este projeto tem como objetivo desenvolver um sistema web para a gestão de operações em uma loja de auto-peças. Utilizando tecnologias como JSP (JavaServer Pages), HTML e JavaScript, o sistema permitirá o gerenciamento eficaz de clientes, funcionários, veículos, peças, serviços e vendas, além de controlar o acesso através de um sistema de login.

## Tecnologias Utilizadas

- **Front-end:** HTML, CSS, JavaScript, JSP
- **Back-end:** Servlets
- **Banco de Dados:** MySQL ou PostgreSQL
- **Servidor:** Apache Tomcat

## Entidades

### 1. Cliente

- **Atributos:** ID, Nome, CPF/CNPJ, Endereço, Telefone, Email
- **Operações:** Cadastrar, Atualizar, Listar, Remover

### 2. Funcionário

- **Atributos:** ID, Nome, CPF, Cargo, Salário, Data de Contratação
- **Operações:** Cadastrar, Atualizar, Listar, Remover

### 3. Veículo

- **Atributos:** ID, Marca, Modelo, Ano, Placa, Cliente (Proprietário)
- **Operações:** Cadastrar, Atualizar, Listar, Remover

### 4. Peça

- **Atributos:** ID, Nome, Descrição, Quantidade em Estoque, Preço
- **Operações:** Cadastrar, Atualizar, Listar, Remover

### 5. Serviço

- **Atributos:** ID, Descrição, Veículo, Data, Funcionário Responsável, Valor
- **Operações:** Cadastrar, Atualizar, Listar, Remover

### 6. Venda

- **Atributos:** ID, Peças Vendidas, Serviços Realizados, Cliente, Funcionário, Data, Valor Total
- **Operações:** Registrar, Listar

### 7. Usuário

- **Atributos:** ID, Login, Senha, Tipo (Admin, Funcionário)
- **Operações:** Cadastrar, Atualizar, Remover, Login

## Banco de Dados

### Scripts de Criação:

```bash
CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
    CPF VARCHAR(50) NOT NULL UNIQUE,
    endereco VARCHAR(50) NOT NULL,
    telefone VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Veiculo (
    id SERIAL PRIMARY KEY,
    modelo VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    placa VARCHAR(50) NOT NULL UNIQUE,
    idCliente INTEGER REFERENCES Cliente(id) ON DELETE CASCADE
);

CREATE TABLE Funcionario (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
    CPF VARCHAR(50) NOT NULL UNIQUE,
    endereco VARCHAR(50) NOT NULL,
	cargo VARCHAR(50) NOT NULL,
-- 	Login de funcionário
    senha VARCHAR(50) NOT NULL,
    matricula VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Servico (
	id SERIAL PRIMARY KEY,
	descricao VARCHAR(255) NOT NULL,
    valor FLOAT NOT NULL DEFAULT 0,
    data VARCHAR(50) NOT NULL,
	idVeiculo INTEGER REFERENCES Veiculo(id) ON DELETE CASCADE,
	idFuncionario INTEGER REFERENCES Funcionario(id) ON DELETE CASCADE
);

CREATE TABLE Peca (
	id SERIAL PRIMARY KEY,
    valor FLOAT NOT NULL,
	descricao VARCHAR(255) NOT NULL,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE ServicoPeca (
	idServico INTEGER,
    idPeca INTEGER,
    quantidade INTEGER,
    PRIMARY KEY (idServico, idPeca),
	FOREIGN KEY (idServico) REFERENCES Servico(id) ON DELETE CASCADE,
    FOREIGN KEY (idPeca) REFERENCES Peca(id) ON DELETE CASCADE
);

CREATE TABLE Venda (
	id SERIAL PRIMARY KEY,
    valor FLOAT NOT NULL DEFAULT 0,
    data VARCHAR(50) NOT NULL,
    finalizacao VARCHAR(50),
	idCliente INTEGER REFERENCES Cliente(id) ON DELETE CASCADE,
	idFuncionario INTEGER REFERENCES Funcionario(id) ON DELETE CASCADE
);

CREATE TABLE VendaPeca (
	idVenda INTEGER,
    idPeca INTEGER,
    quantidade INTEGER,
    PRIMARY KEY (idVenda, idPeca),
	FOREIGN KEY (idVenda) REFERENCES Venda(id) ON DELETE CASCADE,
    FOREIGN KEY (idPeca) REFERENCES Peca(id) ON DELETE CASCADE
);

-- Função de adição de dados e atualização de valor:

-- Calculo para atualizar valor de serviço
CREATE OR REPLACE FUNCTION atualizarValorServico()
RETURNS TRIGGER AS
$$
DECLARE
    valorPeca FLOAT;
BEGIN
    -- Busca o valor da peça na tabela Peca
    SELECT valor * NEW.quantidade INTO valorPeca
    FROM Peca
    WHERE id = NEW.idPeca;

    -- Soma o valor da peça ao valor do serviço na tabela Servico
    UPDATE Servico
    SET valor = valor + valorPeca
    WHERE id = NEW.idServico;

    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Criando gatilho
CREATE TRIGGER atualizarValorServico
AFTER INSERT ON ServicoPeca
FOR EACH ROW
EXECUTE FUNCTION atualizarValorServico();


-- Calculo para atualizar valor de Vendas
CREATE OR REPLACE FUNCTION atualizarValorVendas()
RETURNS TRIGGER AS
$$
DECLARE
    valorPeca FLOAT;
BEGIN
    -- Busca o valor da peça na tabela Peca
    SELECT valor * NEW.quantidade INTO valorPeca
    FROM Peca
    WHERE id = NEW.idPeca;

    -- Soma o valor da peça ao valor da venda na tabela Venda
    UPDATE Venda
    SET valor = valor + valorPeca
    WHERE id = NEW.idVenda;

    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Criando gatilho
CREATE TRIGGER atualizarValorVendas
AFTER INSERT ON VendaPeca
FOR EACH ROW
EXECUTE FUNCTION atualizarValorVendas();


--Função para atualizar o valor do serviço sempre a deletar uma peça do serviço
CREATE OR REPLACE FUNCTION diminirValorServico()
RETURNS TRIGGER AS
$$
DECLARE
    valorPeca FLOAT;
BEGIN
    -- Busca o valor da peça na tabela Peca
    SELECT valor * OLD.quantidade INTO valorPeca
    FROM Peca
    WHERE id = OLD.idPeca;

    -- Subtrai o valor da peça do valor do serviço na tabela Servico
    UPDATE Servico
    SET valor = valor - valorPeca
    WHERE id = OLD.idServico;

    RETURN OLD;
END;
$$
LANGUAGE plpgsql;

--Criando o gatilho
CREATE OR REPLACE TRIGGER triggerDiminirValorServico
AFTER DELETE ON ServicoPeca
FOR EACH ROW
EXECUTE FUNCTION diminirValorServico();


--Função para atualizar o valor da venda sempre a deletar uma peça da venda
CREATE OR REPLACE FUNCTION diminirValorVenda()
RETURNS TRIGGER AS
$$
DECLARE
    valorPeca FLOAT;
BEGIN
    -- Busca o valor da peça na tabela Peca
    SELECT valor * OLD.quantidade INTO valorPeca
    FROM Peca
    WHERE id = OLD.idPeca;

    -- Subtrai o valor da peça do valor do serviço na tabela Venda
    UPDATE Venda
    SET valor = valor - valorPeca
    WHERE id = OLD.idVenda;

    RETURN OLD;
END;
$$
LANGUAGE plpgsql;

--Criando o gatilho
CREATE OR REPLACE TRIGGER triggerDiminirValorVenda
AFTER DELETE ON VendaPeca
FOR EACH ROW
EXECUTE FUNCTION diminirValorVenda();

-- Adicionando uma funcionária ao sistema
-- Inserir dados na tabela Funcionario
INSERT INTO Funcionario (nome, CPF, endereco, cargo, senha, matricula) VALUES
('Cristiane', '000.000.000-01', 'Jardim Oasis, 456', 'Atendente', '123456', 'AAA-10');
```

## Funcionalidades

- **Autenticação de Usuários:** Acesso ao sistema através de login e senha.
- **Gerenciamento de Clientes:** Inclusão, alteração, listagem e exclusão de clientes.
- **Gerenciamento de Funcionários:** Inclusão, alteração, listagem e exclusão de funcionários.
- **Gerenciamento de Veículos:** Cadastro e associação de veículos aos clientes.
- **Controle de Estoque:** Gerenciamento das peças em estoque.
- **Registro de Serviços:** Cadastro e gerenciamento de serviços oferecidos.
- **Realização de Vendas:** Registro de vendas de peças e serviços.
- **Relatórios:** Geração de relatórios de vendas, serviços e estoque.

## Arquitetura do Sistema

O sistema será dividido em três camadas principais:

1. **Camada de Apresentação:** Desenvolvida com HTML, CSS e JavaScript para a criação de interfaces intuitivas e responsivas.
2. **Camada de Lógica de Negócio:** Utilização de JSP e Servlets para o processamento das regras de negócio.
3. **Camada de Acesso a Dados:** Interação com o banco de dados para persistência e recuperação de dados.

## Considerações Finais

Este projeto visa facilitar e otimizar a gestão de uma loja de auto-peças, melhorando o atendimento ao cliente e a eficiência operacional. A implementação deste sistema permitirá um controle mais rigoroso sobre os estoques, vendas e serviços prestados, além de proporcionar uma base sólida para a tomada de decisões estratégicas.
