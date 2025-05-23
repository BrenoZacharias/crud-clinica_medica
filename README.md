O projeto em questão tem como objetivo principal marcar consultas entre médicos e pacientes. O/A atendente será responsável pela marcação das consultas.

Para o desenvolvimento do projeto os seguintes casos de uso foram identificados:

CRUD de Atendente: uma vez que a/o atendente é o responsável pela marcação de consultas então ela deve existir no sistema, se logar e seus dados devem poder ser manipulados. A criação de um atendente é realizada na tela de login, ao apertar o botão 'Cadastrar-se'. Um atendete só pode ser cadastrado mediante a inserção de um código - durante o cadastro - gerado pelo sistema, evitando assim cadastros não autorizados; 

Login de Atendente: como já citado o atendente realizará login para acesso ao sistema;

Geração de códigos para cadastro de atendente;

CRUD de Paciente: os pacientes necessitam de cadastro e manipulação de seus dados no sistema para que possam ser agendadas consultadas entre ele e o médico. Durante a criação ou edição de um paciente, o campo 'Sexo' deve receber 'm' para masculino e 'f' para feminino.

CRUD de Especialidade: representa a especialidade do médico.



Com um controle de acesso através de login e senha a aplicação oferece um nível de segurança.
Outra medida de segurança, já citada, presente na aplicação é o fato do atendente só pode ser cadastrado mediante a inserção do código gerado pelo sistema, o que evita cadastros não autorizados. 


Melhorias:
Arrumar Pesquisas de consulta, especialidade, medico e paciente;
POP-UP para tratar erros como de chave duplicada, por exemplo;
Validar se especialidade já existe antes de tentar adicionar;
Na consulta colocar um combobox com os médicos e pacientes que podem ser selecionados;
Tirar cadastro de especialidade, cadastra-lás direto no banco, para diminuir o escopo;
Ao tentar adicionar um médico já criado ele é duplicado na tableview, 
mesmo não sendo criado no bd;
Data da consulta + hora;
Não posso deixar mais de uma consulta com o mesmo médico e paciente no mesmo dia e horário,
arrumar relação;
Senha não deve ser mostrada

Instações necessárias:
MariaDB Server, que pode ser adquirido através do seguinte link de download: https://mariadb.org/download/?t=mariadb&p=mariadb&r=11.6.2&os=windows&cpu=x86_64&pkg=msi&mirror=fder

mariadb-java-client-3.3.2.jar, que pode ser adquirido através do seguinte link de download: https://mariadb.com/kb/en/mariadb-connector-j-releases/

JAVA 1.8

Passo-a-passo para execução do app:
1) Abrir a pasta sql.
2) Abrir o arquivo 'Query do bd.sql'.
3) Copiar todo o texto de comandos deste arquivo.
4) Logar no MariaDB Server.
5) Colar no prompt do MariaDB Server o texto de comandos copiado do arquivo 'Query do bd.sql'.
6) Apertar 'Enter' para que o MariaDB Server execute os comandos.
7) Inicializar a aplicação.
8) Se logar na aplicação a partir do login inserido no banco de dados.

crud de uma clinica médica

Integrantes do grupo:
BRENO MARCONDES ZACHARIAS,
GABRIEL AKIO, 
HIGOR HUNGRIA AMARAL,
STEPHANIE CAPELINI

LINK DO VÍDEO DE EXPLICAÇÃO DO PROJETO (POO exigiu - é um vídeo de um minuto e pouco explicando por cima como o sistema está funcionando):
https://www.youtube.com/watch?v=AxADliBqVWs
