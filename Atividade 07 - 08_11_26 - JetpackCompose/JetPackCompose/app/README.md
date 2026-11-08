No Readme responda:
1. O que é entrada em um sistema computacional? Dê dois exemplos.
- São o conjunto de ações, dispositivos ou processos que passam instruções do mundo real para o processamento da máquina
- Exemplos : Teclado e Mouse

2. Qual é a diferença entre processamento e saída?
- Processamento: Recebe a instrução do mundo real e processa ela
- Saída: uma resposta do processamento 

3. Por que o valor digitado em um TextField é inicialmente tratado como texto?
- Pois a interface e o teclado usam Strings universalmente, o teclado passa para o textfield o campo
- como texto por padrão

4. Qual é a finalidade de remember e mutableStateOf?
- remember serve para que quando aconteça um restart do sistema, os dados inseridos naqueles campos não sejam perdidos
- mutableStateOf serve para ter um campo que pode ter multiplos outros valores posteriormente

5. O que é um evento? Cite os eventos utilizados nesta aplicação.
-  Uma ação realizada que o sistema pode interpretar e responder, alguns eventos que foram utilizadas:
- Inserção de dados e Clique dos botões

6. O que significa recomposição no Jetpack Compose?
- A recomposição no Jetpack Compose é o processo de executar novamente suas funções combináveis (Composables)
- para atualizar a interface do usuário quando os dados ou o estado (State) do aplicativo mudam.

7. Qual é a responsabilidade da MainActivity neste projeto?
- Ser a tela principal que vai ser atualizada após a mudança dos dados do jetpack compose

8. Qual é a responsabilidade de CalculadoraDescontoScreen?
- Processar os dados e mudar na tela o que for necessário

9. Por que calcularPrecoComDesconto foi colocada em outro arquivo?
- Para separar a lógica do processo das mudanças

10. Qual é a vantagem de uma função de regra de negócio não depender do Compose?
- Caso seja necessário alguma alteração, dá para fazer a alteração sem ter que mexer em todo o compose