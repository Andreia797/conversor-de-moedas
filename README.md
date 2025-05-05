# Conversor de Moedas - Portal de Câmbio Global

## Descrição do Projeto

Este é um aplicativo Java interativo que permite aos usuários converter valores entre diferentes moedas utilizando taxas de câmbio em tempo real obtidas através da API [ExchangeRate-API](https://www.exchangerate-api.com/). O conversor oferece um menu textual via console com diversas opções de conversão, incluindo Real Brasileiro (BRL), Dólar Americano (USD), Euro (EUR), Libra Esterlina (GBP) e Escudo Cabo-verdiano (CVE).

## Funcionalidades

* **Menu Interativo:** Uma interface de linha de comando amigável com opções numeradas para diferentes conversões de moedas.
* **Taxas de Câmbio em Tempo Real:** As taxas de conversão são dinamicamente obtidas de uma API externa, garantindo dados atualizados.
* **Suporte a Múltiplas Moedas:** Conversão entre BRL, USD, EUR, GBP e CVE.
* **Mensagens Informativas:** Feedback claro para o usuário sobre as opções selecionadas, valores convertidos e possíveis erros.

## Pré-requisitos

Antes de executar o Conversor de Moedas, você precisará ter o seguinte instalado em seu sistema:

* **Java Development Kit (JDK):** Versão 11 ou superior. Você pode baixar a versão mais recente em [Download the Latest Java LTS Free](https://www.oracle.com/java/technologies/downloads/#java11).
* **Biblioteca Gson:** Versão 2.10.1 ou superior. Esta biblioteca é utilizada para processar a resposta JSON da API. Se você estiver usando Maven, ela será baixada automaticamente. Caso contrário, você pode baixá-la do [Maven Central Repository Search](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1) e adicionar o arquivo JAR ao classpath do seu projeto.

## Como Executar

1.  **Clone ou Faça o Download do Projeto:** Se você tiver o código em um repositório (como o GitHub), clone-o para sua máquina. Caso contrário, baixe os arquivos diretamente.

2.  **Compile o Código (se necessário):** Se você não estiver usando uma IDE que compila automaticamente, abra um terminal ou prompt de comando, navegue até o diretório do projeto e compile o arquivo `ConversorMoedas.java`. Certifique-se de incluir o arquivo JAR do Gson no classpath:

    ```bash
    javac ConversorMoedas.java -cp .:gson-2.10.1.jar
    ```

    *(Observação: Use `;` em vez de `:` como separador de classpath em sistemas Windows)*

3.  **Execute o Aplicativo:** Após a compilação bem-sucedida, execute o programa Java:

    ```bash
    java -cp .:gson-2.10.1.jar ConversorMoedas
    ```

    *(Observação: Novamente, use `;` em vez de `:` como separador de classpath em sistemas Windows)*

4.  **Interaja com o Menu:** O aplicativo exibirá um menu com as opções de conversão disponíveis. Digite o número da opção desejada e siga as instruções.

## Configuração da Chave de API

Este projeto utiliza a ExchangeRate-API para obter as taxas de câmbio. A chave de API está diretamente no código-fonte (`ConversorMoedas.java`):

```java
private static final String API_KEY = "efeb6b3e4df207f4a5c5f71f";