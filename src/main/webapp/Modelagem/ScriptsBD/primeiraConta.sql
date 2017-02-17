insert into grupo (codigo, nome_grupo) values (1, 'ADMINISTRADOR');
insert into grupo (codigo, nome_grupo) values (2, 'ESTAGIARIO');
insert into delegacia (codigo, nome_delegacia) values (1, 'DEGEPOL');            
insert into pessoa(codigo, cpf, login, matricula, nome_pessoa, nome_mae, nome_pai, rg, 
            senha, sobre_nome, sobre_nome_mae, ultimo_nome, 
            ultimo_nome_mae, ultimo_nome_pai, codigo_delegacia)
    VALUES (1, '000.000.000-00', 'TESTE', '000.000-0', 'Teste', 'Teste', 'Teste', '000.000.000', 
            '202cb962ac59075b964b07152d234b70', 'Teste', 'Teste', 'Teste', 
            'Teste', 'Teste', 1);            
            
insert into usuario_grupo (codigo_pessoa, codigo_grupo) values (1, 1);


pegarDoisUltimosDigitosCPF   | Quais os 2 ultimos digitos do seu CPF ?
pegarTresPrimeirosDigitosCPF | Quais os 3 primeiros digitos do seu CPF ?
pegarTresPrimeirosDigitosRG  | Quais os 3 primeiros digitos do seu RG ?
pegarTresUltimosDigitosRG    | Quais os 3 ultimos digitos do seu RG ?
pegarPrimeiroNomePai         | Qual o primeiro nome do seu pai ?
pegarPrimeiroNomeMae         | Qual o primeiro nome da sua mae ?
pegarSobrenome               | Qual seu sobrenome ?
pegarSobrenomeMae            | Qual o sobrenome da sua mae ?
pegarSobrenomePai            | Qual o sobrenome do seu pai ?
pegarUltimonome              | Qual seu ultimonome ?
pegarUltimonomeMae           | Qual o ultimo nome da sua mae ?
pegarUltimonomePai           | Qual o ultimo nome do seu pai ?
pegarAnoNascimento           | Qual o ano do seu nascimento ?
pegarMesNascimento           | Qual o mês do seu nascimento ?
pegarDiaNascimento           | Qual o dia do seu nascimento ?
pegarMesInicioEstagio        | Qual o mes de inicio do seu estagio ?

