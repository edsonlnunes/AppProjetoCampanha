# AppProjetoCampanha

Édson Lucas Martins Nunes

O aplicativo possui 6 telas ao total. Sendo elas:

Tela Inicial: 
Está a primeira tela que o APP abre (A NÃO SER SE VOCÊ JÁ ESTIVER LOGADO E NÃO FIZER LOGOUT), nela temos 2 botões, onde um serve para cadastrar uma conta e outra para fazer o login.

Tela Criar Conta: 
Nesta tela é feito o cadastro do usuário, nela pede os seguintes dados: Nome, CPF, E-mail e a senha. 
Existe algumas validações nos campos, sendo elas:
Nome: É obrigado pelo menos ter 1 digito.
CPF: É obrigado ser um CPF válido.
E-mail: É obrigado ser um E-mail válido (Não é mandado nenhum e-mail para confirmação) mas é obrigado ter o domínio e o “.com”.
Senha: É obrigatório ter 6 digitos.
Confirma Senha: É preciso ser a mesma digitada no campo Senha.
OBS: Se já possui um usuário cadastrado com o e-mail informado é preciso usar outro e-mail.
OBS: Não é permitido nenhum campo ficar vazio.

Tela Login: 
Está é a tela de login, nela pede os seguintes dados: E-mail cadastrado no App e uma senha válida. Existe algumas validações nos campos, sendo elas:
E-mail: Só permite um e-mail válido contendo o domínio e o “.com”. E-mail precisa estar cadastrado no App.
Senha: É obrigado digitar uma senha de pelo menos 6 digitos.
OBS: Se usuário não estiver cadastrado irá mostrar a mensagem “Usuário não existe”.
OBS: Se dados estiverem incorretos irá mostrar a mensagem: “E-mail ou senha incorreto”.
OBS: Não é possível tentar entrar com os campos vazios.
Nelas também temos 2 botões, um para efetuar o login e outro para entrar com o facebook. 
OBS: O login com o Facebook ainda não está implementado, será lançado na próxima versão.

Tela incial dentro do App 
Esta é a primeira tela do App depois que efetuar o Login, nela temos um ListView onde neste momento não está mostrando nada pois está esperando vir as campanhas do servidor, onde vai ser lançado na próxima versão. 
Nela temos um Action Bar no canto superior direito da tela onde serve para fazer o LOGOUT.
No canto esquerdo do App temos um Drawer onde puxando para o lado é possível ver 2 opçõe.
As duas opções são: Alterar os dados do usuário e escolher as afinidades que vão ser mostrado na primeira tela do app.

Tela Alterar Dados 
Esta é a tela para fazer a alteração de dados, nela possui os seguintes campos:  Nome, Email, CPF e Senha. O componente E-mail desta tela não é editável.
Os campos Nome, Email e CPF já vem preenchido, portanto para alterar é só clicar em cima do campo e fazer a alteração desejada. Como na tela de Criar Conta esta tela possui algumas validações, sendo elas:
Nome: É obrigado pelo menos ter 1 digito.
CPF: É obrigado ser um CPF válido.
E-mail: Não é alterável.
Senha: É obrigatório ter 6 digitos.
Confirma Senha: É preciso ser a mesma digitada no campo Senha.
OBS: Se já possui um usuário cadastrado com o e-mail informado é preciso usar outro e-mail.
OBS: Não é permitido nenhum campo ficar vazio.
OBS: Os campos Senha e Confirma Senha são opcional, se não for preenchido a senha não é alterada.

Tela Afinidades 
Esta é a tela de Escolhas de afinidades, nela temos um campo de opção para escolher as afinidades, quando clicado nele é aberto uma lista com as afinidades disponíveis e logo abaixo do botão temos uma lista para apresentar as afinidades escolhidas.
Para excluir a afinidade é preciso segurar em cima da afinidade e clicar em Remover.
Nesta tela temos a seguinte validação:
Se a opção já estiver na lista é porque ela já foi escolhida e não é possível escolher ela de novo, se for tentado escolher ela irá mostrar a mensagem “Afinidade já cadastrada”.
