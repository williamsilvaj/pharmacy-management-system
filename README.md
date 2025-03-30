
# Guia de Configuração

## Iniciar o projeto:

0. **Ferramentas**:
   - Instalar JDK 21 e Maven 3.9.9
   - Configurar variaveis de ambiente no PC


1. **Baixar dependências e instalar**:
   ```bash
   mvn install -DskipTests
   ```
2. **Compilar o projeto**:
   ```bash
   mvn clean compile
   ```

---

## Formatar código automaticamente:

Para garantir que o código siga o padrão de formatação, use o Spotless:

```bash
mvn spotless:apply
```

---

## Verificar formatação do código:

Para garantir que o código está conforme as regras de estilo (como Google Java Style Guide), use o Checkstyle:

```bash
mvn checkstyle:check
```

---

## Manter padrão em várias IDEs:

### IntelliJ IDEA:
1. Vá em **File > Settings > Editor > Code Style**.
2. Ative a opção **Enable EditorConfig support**.

### VS Code:
1. Instale a extensão **[EditorConfig for VS Code](https://marketplace.visualstudio.com/items?itemName=EditorConfig.EditorConfig)**.

### Eclipse:
1. Instale o plugin **EditorConfig Eclipse Plugin**.

---
