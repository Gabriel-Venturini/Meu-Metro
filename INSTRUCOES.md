# Introdução
Este documento fornece instruções detalhadas sobre como utilizar o programa Sistema de Metrô. Todas as operações principais do sistema estão concentradas na classe Subway.java, que é a única classe que deve ser instanciada diretamente para operar o sistema.

## Estrutura do Sistema
O sistema é composto por três classes principais:

- Subway: A classe central que gerencia o metrô e seus vagões.
- Wagon: Representa um vagão do metrô.
- Door: Gerencia as portas de um vagão.

## Utilizando o Sistema

Instanciando um Objeto Subway<br>
Para começar a usar o sistema, você precisa instanciar um objeto da classe ```Subway``` que requer a inserção de dois valores do tipo String em seu construtor, são eles ```subwayID``` e ```wagonID```. Isso pode ser feito da seguinte forma:
``` java
Subway subway = new Subway('sID', 'wID');
```
<strong>Observação</strong>: Com esses dois parâmetros, é possível definir os valores das variáveis ```subwayID``` e ```wagonID``` e ao mesmo tempo, instanciar um novo vagão para este metrô. Sinta-se à vontade para editar o código e criar um método que seja capaz de instanciar novos vagões.

### Métodos do Objeto Subway<br>
Após instanciar, podemos começar a utilizá-lo. Abaixo há instruções detalhadas de cada método. Se ficar curioso e com preguiça de testar um a um, deixei alguns screenshots na pasta [images](images).<br>

#### Método ```forward()```
1. Este método começa verificando se alguma porta do metrô está aberta e, se estiver, envia uma mensagem dizendo que não é possível movimentar o trem com portas abertas. Neste trecho de código vemos a implementação desta função:
``` java
if (wagon.leftDoorsState == true || wagon.rightDoorsState == true) {
    // avoid the train going forward if any door is open
    System.out.println("You can't go forward with the doors open!\n");
}
```
2. Caso nenhuma porta esteja aberta, o código continua sua execuçaõ e então faz outra verificação, desta vez, se a velocidade não está acima do limite estabelecido (neste caso, escolhi 80KMh, mas é possível alterar e até eliminar este limite). Segue o trecho que indica esta restrição:
``` java
if (getSpeed() >= 80) {
    // in case the pilot reaches the maximum speed
    System.out.println("You reached the maximum speed of 80Kmh!\n");
}
```
3. Se nenhuma das verificações forem verdadeiras, ou seja, nenhuma porta está aberta e o trem não está acima do limite de velocidade, um código que aumenta o valor da variável ```speed``` com um incremento de 10 (novamente, é possível modificar para o valor desejado) é executada:
``` java
else {
    this.speed = this.speed + 10; // adds +10kmh speed
    System.out.println("Your speed: " + getSpeed());
}
```

#### Método ```reverse()```
1. O Reverse serve para desacelerar o trem, portanto, é chamado quando ele está em movimento e, por isso, essa é a primeira verificação feita no código:
``` java
if (getSpeed() == 0) {
    // in case the speed is zero
    System.out.println("You are not fast enough to use reverse!\n");
}
```
2. Caso o trem esteja em movimento, é possível desacelerá-lo e então uma função reduz o valor de ```speed``` em 10 (que repetindo, pode ser alterado, mas para evitar erros, recomendo que seja o mesmo valor de incremento):
``` java
else {
    this.speed = this.speed - 10; // slow down by 10kmh it's speed
    System.out.println("Your speed: " + getSpeed());
}
```

#### Método ```stop()```
1. Atua como um freio de emergência que rapidamente freia o trem, mas como todos os outros, começa com uma verificação. A primeira verificação é para garantir se o veículo realmente está em movimento, se não estiver, então uma mensagem é exibida:
``` java
if (getSpeed() == 0) {
    System.out.println("The train is not moving!\n");
}
```
2. Caso o trem realmente esteja em movimento, os freios são acionados e uma mensagem exibida, após isso, inicia-se a desaceleração por meio de um loop que certifica-se de chegar no valor de 0 na variável ```speed```:
``` java
System.out.println("\nActivating emergency breaks...\n");
while (this.speed >= 10) {
    System.out.println("Speed: " + this.speed);
    this.speed = this.speed - 10;
}
```
3. Após chegar à velocidade 0, uma mensagem de confirmação de parada é exibida para o usuário por meio desta função:
``` java
if (getSpeed() == 0) {
    System.out.println("Train stopped succesfully!\n");
}
```

#### Método ```openDoor()```
1. Inicia verificando se o veículo está em movimento e, se estiver, exibe uma mensagem:
```java
if (this.speed > 0) {
    // avoid the train to open the doors while the  car is moving
    System.out.println("You can't open the doors while moving!\n");
}
```
2. Caso contrário, ativa o método ```openDoor()``` da variável ```wagon``` que é um objeto da classe ```Wagon``` e recebe como parâmetro o lado das portas que serão abertas (left, right ou both - [esquerda, direita, ambos]):
``` java
else {
    this.wagon.openDoors(side);
}
```
3. Após chamar este método, dentro da classe ```Wagon``` é iniciado o método ```openDoor()``` que começa verificando se as portas já não estão abertas (é redundante, pois se as portas não podem estar abertas enquanto o trem move-se, seria desnecessário fazer essa dupla verificação, mas mantive para refatorar depois):
``` java
if (leftDoorsState == true || rightDoorsState == true) {
    // in case its already open
    System.out.println("Doors are already open!");
}
```
4. Se as portas não estiverem abertas, as luzes piscantes do método ```lights()``` são acionadas e representam um alarme visual e, após este método ser chamado, o método verifica qual lado foi escolhido para a abertura e então abre o lado - ou os lados - escolhido(s):
``` java
else {
    // blinds the lights when opening doors
    light(true);

    if (side.equals("left")) {
        // open the left doors
        System.out.println("Left " + Door.open());
        this.leftDoorsState = true;
    } else if (side.equals("right")) {
        // open the right doors
        System.out.println("Right " + Door.open());
        this.rightDoorsState = true;
    } else if (side.equals("both")) {
        // open both sides
        System.out.println("All " + Door.open());
        this.leftDoorsState = true;
        this.rightDoorsState = true;
    }
}
```
5. É importante mencionar que a classe ```Door``` é chamada nesse momento com seu método estático apenas para um maior controle da operação e um código mais limpo, mas poderia ser facilmente descartada, pois não é essencial para o funcionamento das portas. Segue o método ```open()``` escrito nela:
``` java
public static String open() {
    // open doors
    // static methods so we dont need to instance a new object every time
    return "doors are opening...\n";
}
```

#### Método ```closeDoor()```
1. Chama a função ```closeDoor()``` da classe ```Wagon``` que começa verificando se alguma porta já está aberta, caso esteja, envia uma mensagem avisando:
``` java
if (leftDoorsState == false && rightDoorsState == false) {
    // in case its closed already
    System.out.println("Doors are already closed!\n");
}
```
2. No caso da primeira etapa não ser executada, o programa roda o método ```lights()``` da classe ```Wagon``` que representa o alarme visual (luzes piscantes) da abertura e fechamento das portas e, após isso, fecha todas as portas de uma vez, mesmo que já estejam fechadas:
``` java
else {
    // blinds the lights when opening doors
    light(true);

    // closes all doors
    System.out.println("All " + Door.close());
    this.leftDoorsState = false;
    this.rightDoorsState = false;
}
```
3. Da mesma forma que acontece no método ```openDoor()```, a classe ```Door``` participa apenas como uma forma de deixar o código mais limpo e manter um controle maior das operações, mas não é necessária para o funcionamento e acaba servindo apenas para retornar uma mensagem. Segue o trecho de ```Door``` em que aplicamos o método estático ```close()``` que retorna a string que utilizamos no código acima:
``` java
public static String close() {
    // close doors
    return "doors are closing...\n";
}
```

## Exemplo de Uso Completo na ```Main```
``` java
public class Main {
    public static void main(String[] args) {
        Subway newSubway = new Subway("L1", "L256");
        System.out.println("Subway ID: " + newSubway.getSubwayID());
        System.out.println("Wagon ID: " + newSubway.wagon.getWagonID());

        newSubway.openDoor("left");
        newSubway.closeDoor();

        newSubway.forward(true);
        newSubway.reverse(true);

        newSubway.stop(true);
        newSubway.getSpeed();
    }
}
```

## Considerações Finais
Estas instruções cobrem as operações detalhadas do que você pode realizar com o sistema de metrô. Para estender ou modificar a funcionalidade, sinta-se à vontade para explorar e adaptar o código das classes.