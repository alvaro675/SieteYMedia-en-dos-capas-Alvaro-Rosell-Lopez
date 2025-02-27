package sieteymedia;



import recursos.Baraja;
import recursos.Carta;
import java.util.Scanner;

public class InterfaceConsola {
    Baraja baraja;
    Carta[] cartasJugador;
    Carta[] cartasBanca;
    Scanner sc = new Scanner(System.in);
    InterfaceConsola(){
        baraja = new Baraja();
        baraja.barajar();
        // se van pidiendo cartas al jugar pero matemáticamente a partir de 15 siempre
        // nos pasamos
        // hay 12 cartas de medio puntos, si sacara estas 12 luego cartas con valor 1
        // vemos que a partir de 15 cartas siempre se pasas
        cartasJugador = new Carta[15];
        cartasBanca = new Carta[15];
        presentarJuego();
        jugar();
    }
    public static void main(String[] args) {
        new InterfaceConsola();
    }

    SieteYMedia objeto= new SieteYMedia();

    void presentarJuego() {
        System.out.println("- El usuario es el jugador y el ordenador la  banca.");
        System.out.println("- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.");
        System.out.println("- las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.");
        System.out.println(
                "- Hay dos turnos de juego: el turno del jugador y el turno de la banca. Se comienza por el turno del jugador.");
        System.out.println("- El jugador va pidiendo cartas a la banca de una en una.");
        System.out.println("- El jugador puede plantarse en cualquier momento.");
        System.out.print("- Si la suma de los valores de las cartas sacadas es superior ");
        System.out.println("a 7 y medio, el jugador 'se pasa de siete y medio' y  pierde.");
        System.out.println(
                "- Si el jugador no se pasa, comienza a sacar cartas la banca y ésta  está obligada a sacar cartas hasta empatar o superar al jugador.");
        System.out.println(
                "- Si la banca consigue empatar o superar la puntuación del jugador 'sin pasarse de siete y medio', gana la banca.");
        System.out.println(
                "- La banca no se puede plantar y tiene que empatar o superar la puntuación del  jugador sin pasarse.");
        System.out.println(
                "- En este proceso puede ocurrir que la banca 'se pase' y entonces pierde la banca y gana el jugador.");
        System.out.println("\nEmpecemos!!!\n");
    }

    void jugar() {
        turnoJugador();
        turnoBanca();
        System.out.println("Adios");
    }
    void turnoJugador() {
        char opc = 'C';
        // obligamos a que como mínimo se tenga 1 carta
        System.out.println("Como mínimo recibes una carta, luego puedes decidir si seguir o plantarte");
        while (objeto.valorCartas(cartasJugador) < 7.5 && opc == 'C') {
            Carta c = baraja.darCartas(1)[0];
            // insertamos c en las cartas del jugador
            objeto.insertarCartaEnArray(cartasJugador, c);
            // mostramos cartas y su valor, si se pasa se sale del bucle
            System.out.println("Éstas son tus cartas jugador:");
            objeto.mostrarCartas(cartasJugador);
            double valor = objeto.valorCartas(cartasJugador);
            System.out.println("\n\tValor de cartas: " + valor);
            if (valor < 7.5) {
                // suponemos que el usuario teclea bien !!!
                System.out.println("\n¿Pides [C]arta o te [P]lantas?");
                opc = sc.next().trim().toUpperCase().charAt(0);
            }

        }

    }
    void turnoBanca() {
        // lo primero es consultar el valor que alcanzó el jugador en su turno
        double valorCartasJugador = objeto.valorCartas(cartasJugador);
        if (valorCartasJugador > 7.5) {
            System.out.println("Jugador, te has pasado en tu jugada anterior, gana la banca");
            return;
        }
        System.out.println("\n\nTurno de banca ...");

        // juega hasta empatar o superar
        while (objeto.valorCartas(cartasBanca) < valorCartasJugador) {
            Carta c = baraja.darCartas(1)[0];
            objeto.insertarCartaEnArray(cartasBanca, c);
        }
        System.out.println("Éstas son mis cartas:");
        objeto.mostrarCartas(cartasBanca);
        System.out.println("\nValor de  mis cartas(banca): " + objeto.valorCartas(cartasBanca));
        if (objeto.valorCartas(cartasBanca) > 7.5) {
            System.out.println("me pasé, ganas tú,jugador");
        } else {
            System.out.println("Gana la banca");
        }
    }

}
