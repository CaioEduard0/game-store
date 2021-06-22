# Game store

Loja online de venda de jogos, desenvolvida como desafio técnico.


## Endpoints


|                URL                | Método | Permissão |                Função                 |  
|:---------------------------------:|:------:|:---------:|:-------------------------------------:|
|  /login							              |  get	 |    all    | Página de login de usuário            |
|  /sign-up 						            |  get   |    all    | Página	de cadastro de usuário         |
|  /sign-up							            |  post  |    all    | Cadastra um novo usuário		           |
|  /								                |  get   |    user   | Página de entrada do sistema		       |
|  /products/order-by-price			    |  get   |    user   | Lista os produtos ordenado por preço	 |	
|  /products/order-by-score			    |  get   |    user   | Lista os produtos ordenado por pontos |
|  /products/order-by-name			    |  get   |    user   | Lista os produtos ordenado por nome	 |
|  /admin/products/register-product	|  get   |    admin  | Página de cadastro de produto 	       |
|  /admin/products/register-product	|  post  |    admin  | Cadastra um novo produto		           |
|  /new-order						            |  post  |    user   | Encaminha o usuário para o seu pedido |
|  /new-order						            |  get   |    user   | Página de pedido		                   |
|  /register-order					        |  post  |    user   | Cadastra um novo pedido		           |

## Tecnologias

- Java
- Spring
- Maven
- JUnit
- H2 Database
- HTML
- Bootstrap
- Thymeleaf

(README ainda não finalizado!)
