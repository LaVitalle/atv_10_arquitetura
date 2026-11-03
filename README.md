# Atividade 010 - Padrão Composite

Refatoração do sistema de arquivos e pastas utilizando o padrão **Composite**, incluindo o desafio adicional de exibir a hierarquia.

## Estrutura

| Classe | Papel |
|---|---|
| `FileSystemComponent` | Componente: abstração comum com `getName()`, `getSize()` e `display(String indent)` |
| `DocumentFile` | Folha: arquivo individual com nome e tamanho em KB |
| `Folder` | Composto: guarda uma lista de `FileSystemComponent`, com `add` e `remove` |
| `Main` | Monta a árvore e consulta arquivos e pastas pela abstração comum |

## Como executar

```bash
javac -d out src/*.java
java -cp out Main
```

## Questões para reflexão

**a) Qual problema existente no código inicial foi resolvido com o Composite?**

A pasta só aceitava arquivos, e o tamanho era consultado de formas diferentes em arquivos (`getSize`) e pastas (`getTotalSize`). Para ter subpastas, seria preciso manter listas separadas e tratar cada tipo de um jeito. Com o Composite, arquivos e pastas implementam a mesma interface, então uma pasta pode conter os dois e o código cliente trata todos igual.

**b) Quais elementos representam o componente, a folha e o composto na sua implementação?**

- Componente: `FileSystemComponent`
- Folha: `DocumentFile`
- Composto: `Folder`

**c) Como o polimorfismo permite tratar arquivos e pastas de maneira uniforme?**

O cliente e a própria pasta chamam `getSize()` e `display()` sobre um `FileSystemComponent`, sem saber se é arquivo ou pasta. Cada classe responde do seu jeito: o arquivo devolve o próprio tamanho e a pasta soma os filhos. Assim não é preciso `if` nem `instanceof` para decidir o que fazer.

**d) Qual é o papel da recursão no cálculo do tamanho das pastas?**

A pasta calcula seu tamanho chamando `getSize()` em cada filho. Se o filho é outra pasta, ela faz o mesmo com os filhos dela, e assim por diante até chegar nos arquivos. Isso permite calcular o tamanho de uma árvore com qualquer número de níveis. A pasta vazia não tem filhos, então retorna zero.

**e) Quais são as vantagens e limitações de manter as operações de adicionar e remover filhos apenas na classe `Folder`?**

Vantagem: é mais seguro, porque não faz sentido adicionar filhos num arquivo, e o compilador impede isso. A interface comum também fica menor e só com o que vale para todos. Limitação: o cliente perde um pouco da uniformidade. Para adicionar ou remover filhos, ele precisa ter uma referência do tipo `Folder` e não pode fazer isso usando apenas `FileSystemComponent`.
