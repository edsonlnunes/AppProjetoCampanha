# AppProjetoCampanha

Édson Lucas Martins Nunes

O aplicativo possui 10 telas ao total. Sendo elas:

Tela Inicial: 
Está a primeira tela que o APP abre (A NÃO SER SE VOCÊ JÁ ESTIVER LOGADO E NÃO FIZER LOGOUT), nela temos 2 botões, onde um serve para cadastrar uma conta e outra para fazer o login.

Tela Criar Conta: 
Nesta tela é feito o cadastro do usuário, nela pede os seguintes dados: Nome, CPF, E-mail, Telefone e a senha. 
Existe algumas validações nos campos, sendo elas:
Nome: É obrigado pelo menos ter 1 digito.
CPF: É obrigado ser um CPF válido.
E-mail: É obrigado ser um E-mail válido (Não é mandado nenhum e-mail para confirmação) mas é obrigado ter o domínio e o “.algumacoisa”.
Telefone: É obrigado preencher o telefone no formato (XX) XXXXX-XXXX, respeitando os parênteses e também os espaços. É preciso informar o telefone pois para fazer a recuperação de senha é feito por SMS, onde é enviado uma senha provisória. Se colocar no formato (XX) XXXX-XXXX é passível de falha, pois os telefones agora possuem o digito 9 na frente.
Senha: É obrigatório ter 6 dígitos.
Confirma Senha: É preciso ser a mesma digitada no campo Senha.
OBS: Se já possui um usuário cadastrado com o e-mail informado é preciso usar outro e-mail.
OBS: Não é permitido nenhum campo ficar vazio.

Tela Login: 
Está é a tela de login, nela pede os seguintes dados: E-mail cadastrado no App e uma senha válida. Também é possível fazer o login com o facebook se preferir. Quando digitado o e-mail e senha é feito as seguintes validações nos campos, sendo elas:
E-mail: Só permite um e-mail válido contendo o domínio e o “.algumacoisa”. E-mail precisa estar cadastrado no App.
Senha: É obrigado digitar uma senha de pelo menos 6 digitos.
OBS: Se usuário não estiver cadastrado irá mostrar a mensagem “Usuário não existe”.
OBS: Se dados estiverem incorretos irá mostrar a mensagem: “E-mail ou senha incorreto”.
OBS: Não é possível tentar entrar com os campos vazios.
Quando clicado na opção “Esqueci minha senha” é aberto um dialog:
Neste dialog solicita o e-mail do usuário. É preciso colocar o e-mail cadastrado no app e é preciso ter um telefone válido cadastrado. Se tiver tudo válido é enviado um SMS para o usuário informando uma senha temporária.
Nelas também temos 2 botões, um para efetuar o login e outro para entrar com o facebook. 
OBS: Para entrar usando a opção do facebook é preciso solicitar acesso para o desenvolvedor, como o app ainda não foi publicado na loja não é possível qualquer um entrar usando essa opção. 
OBS 2: O sms é enviado do celular do próprio usuário, como é um app acadêmico não foi integrado com nenhum serviço de SMS por questão financeira. Logo, se o usuário não tiver como enviar SMS não é possível recuperar a senha.

Tela incial dentro do App 
Esta é a primeira tela do App depois que efetuar o Login, nela temos um ListView onde está implementado alguns Card Views fictícios. Ainda não possuem ações reais pois está esperando o servidor ficar pronto para receber os dados.
Nos Cards temos 2 botões, um para participar da ação onde não está implementado nada, futuramente vai ser implementado toda a parte e funções do voluntário participar do evento.
Também temos um botão para fazer doação, onde clicando nele mostra a seguinte tela:

Tela de Doação
Nela podemos informar o que vamos doar e a quantidade.
Nesta tela inicial temos um Action Bar no canto superior direito da tela onde serve para fazer o LOGOUT, como mostrado na imagem a seguir, e também tem uma opção de ler um QR CODE.
Quando clicado no botão para ler um QR CODE irá abrir a câmera e ler algum qr code.  Depois de ler um qr code é aberto a seguinte tela com as informações obtidas.

Tela Alterar Dados 
Esta é a tela para fazer a alteração de dados, nela possui os seguintes campos:  Nome, Email, CPF, Telefone e Senha. O componente E-mail desta tela não é editável.
Os campos Nome, Email e CPF já vem preenchido, portanto para alterar é só clicar em cima do campo e fazer a alteração desejada.
Quando o login é feito informando os dados na tela Criar Conta os campos Nome, Email e CPF já vem preenchido, portanto para alterar é só clicar em cima do campo e fazer a alteração desejada. Quando é o login é feito usando o facebook, é feito um cadastro da conta, mas os dados CPF e Telefone não são preenchidos, portanto é obrigado informar os dados para ficar salvo no banco de dados e assim usar o máximo possível do app. Nos campos possuem as seguintes validações:
Nome: É obrigado pelo menos ter 1 digito.
CPF: É obrigado ser um CPF válido.
E-mail: Não é alterável.
Telefone: É obrigado preencher o telefone no formato (XX) XXXXX-XXXX, respeitando os parênteses e também os espaços. É preciso informar o telefone pois para fazer a recuperação de senha é feito por SMS, onde é enviado uma senha provisória. Se colocar no formato (XX) XXXX-XXXX é passível de falha, pois os telefones agora possuem o digito 9 na frente.
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

Qualquer dúvida ou informação, entrar em contato no seguinte e-mail:
edsonmartins3110@gmail.com

