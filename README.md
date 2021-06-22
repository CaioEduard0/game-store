# Game store

Loja online de venda de jogos, desenvolvida como desafio técnico.


## Endpoints


|                URL                | Método | Permissão |                Função                  |  
|:---------------------------------:|:------:|:---------:|:--------------------------------------:|
|  /login							              |  get	 |    all    | Página de login de usuário             |
|  /sign-up 						            |  get   |    all    | Página	de cadastro de usuário          |
|  /sign-up							            |  post  |    all    | Cadastra um novo usuário		            |
|  /								                |  get   |    user   | Página de entrada do sistema		        |
|  /products/order-by-price			    |  get   |    user   | Lista os produtos ordenados por preço	|	
|  /products/order-by-score			    |  get   |    user   | Lista os produtos ordenados por pontos |
|  /products/order-by-name			    |  get   |    user   | Lista os produtos ordenados por nome	  |
|  /admin/products/register-product	|  get   |    admin  | Página de cadastro de produto 	        |
|  /admin/products/register-product	|  post  |    admin  | Cadastra um novo produto		            |
|  /new-order						            |  post  |    user   | Encaminha o usuário para o seu pedido  |
|  /new-order						            |  get   |    user   | Página de pedido		                    |
|  /register-order					        |  post  |    user   | Cadastra um novo pedido		            |

Quando o sistema é iniciado, é inserido no banco em memória uma lista com 9 produtos. Para acessar o sistema é necessário cadastrar uma conta,
ou pode ser usada a única conta com permissão de administrador que já é carregada em memória, e que possui os seguintes dados: e-mail: *admin@gmail.com*,
password: *admin*. A conta de administrador é usada apenas para cadastrar produtos, pois ela não possui às informações necessárias para fazer um pedido.
O projeto possui tratamento de erros para todos os campos de entrada de dados, e também não permite que o cliente cadastre um pedido sem nenhum produto.

## Comandos (compilar / empacotar / executar)


_É necessário ter o java 11 instalado!_


Executar apenas os testes do projeto:

```console
mvn test
```
Com um único comando, na pasta do projeto, o Maven irá executar os testes, validar o projeto, e gerar o pacote com o código compilado.

```console
mvn clean install
```
Como o projeto executa a interface *CommandLineRunner* na inicialização (usada para carregar os produtos no banco de dados), não é possível
rodar o projeto pelo terminal sem receber um erro, sendo assim, é recomendado que o mesmo seja executado pela classe principal, usando alguma IDE.

Para executar o projeto diretamente pelo terminal devemos comentar à classe *Saving Products*, entrar na pasta *target* que é gerada após
o comando *mvn clean install*, e executar o seguinte comando:

```console
java -jar game-store-0.0.1-SNAPSHOT.jar
```

## Tecnologias

- Java
- Spring
- Maven
- JUnit
- H2 Database
- HTML
- Bootstrap
- Thymeleaf

## Considerações finais

Escolhi o framework Spring pois tenho mais familiaridade com ele. É um framework que está sempre atualizado, e oferece várias features que nos 
ajudam no dia a dia com o desenvolvimento.

O projeto não tem um grande foco na parte de front-end, foi usado HTML, Bootstrap, Thymeleaf, e um pouco de CSS e JS para criar às funcionalidades
solicitadas, sem nada extravagante, apenas uma organização mínima para que os dados sejam mostrados de forma clara.

Atendi a todos os requisitos solicitados, mas tenho consciencia que faltaram várias coisas para que o projeto ficasse completo, sendo algumas delas:

- Página de entrada da loja (index);
- Página que lista os pedidos feitos por cada cliente;
- Classe _Client_, que teria um relacionamento com a classe _User_, para que o cliente precisasse preencher todos os seus dados apenas quando fosse fazer um pedido;
- Relacionamento _ManyToMany_ entre às classes _Order_ e _Product_ (esse realmente foi um erro meu, pois usei os atributos da classe deixada como modelo no desafio
e esqueci de colocar o relacionamento. Como não posso ultrapassar o prazo definido, fiz o commit do projeto assim mesmo);
- O Csrf foi desabilitado para que os testes sejam executados;
- etc.

