# daily-reward

daily-reward é um plugin de recompensas diárias e de fácil configuração com possibilidade de criar quantas recompensas
você desejar.

## :pushpin: Features

- Configuração flexível.
- Codigo limpo e leve.
- Permitido criar até 53 recompensas.
- Permitido enviar itens na coleta da recompensa.
- Existem 3 tipos diferentes de recompensa, que são eles: COMMAND, ITEM e ALL.
- Existem 2 tipos de armazenamento, MySQL e SQLite.

## :heavy_minus_sign: Comandos

<table border="1" style="border-collapse: collapse">
<tr>
<th>comando</th> <th>descrição</th> <th>permissão</th>
</tr>
<tr>
<td>/coletar</td> <td>abrir o menu</td> <td>dailyreward.command.use</td>
</tr>
</table>

## :telescope: Compatibility

- Minecraft version 1.8.0 - 1.12.2

## :link: Links

- [config.yml](https://github.com/BADnotice/daily-reward/blob/master/src/main/resources/config.yml) - clique para
  acessar o arquivo de configuração.
- [rewards.yml](https://github.com/BADnotice/daily-reward/blob/master/src/main/resources/rewards.yml) - clique para
  acessar o arquivo de recompensas

## :tada: Tecnologias utilizadas

O Projeto foi desenvolvido utilizando as seguintes tecnologias.

- [lombok](https://projectlombok.org/) - Gera getters, setters e outros métodos útils durante a compilação por meio de
  anotações.
- [pdm](https://github.com/knightzmc/pdm) - baixa as dependências de desenvolvimento assim que o plugin é ligado pela
  primeira vez.

### :tada: APIs e Frameworks

- [command-framework](https://github.com/SaiintBrisson/command-framework) - framework para criação e gerenciamento de
  comandos.
- [sql-provider](https://github.com/henryfabio/sql-provider) - provê a conexão com o banco de dados.
- [inventory-api](https://github.com/HenryFabio/inventory-api) - api para criação e o gerenciamento de inventários
  customizados.
- [configuration-inject](https://github.com/HenryFabio/configuration-injector) - injetar valores de configurações
  automaticamente.

## :inbox_tray: Download

Você pode baixar o plugin clicando [AQUI](https://github.com/BADnotice/daily-reward/releases) ou se preferir alterar,
pode clonar o repositório.

## :building_construction: DailyReward API

##### :fire: API

O plugin foi desenvolvido com uma API adequada para desenvolvedores <br>
você pode acessa-la
clicando [AQUI](https://github.com/BADnotice/daily-reward/blob/master/src/main/java/io/github/badnotice/dailyreward/api/DailyRewardAPI.java)

##### :fire: Events

- <b>RewardExpiredEvent</b> - Chamado quando a recompensa coletada do jogador expira.
- <b>RewardRequestEvent</b> - Chamado quando o jogador solicitar uma recompensa. 
