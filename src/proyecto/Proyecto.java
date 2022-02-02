/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import entorno.*;
import personajes.*;
import java.util.Scanner;

/**
 *
 * @author pablo.gomez.garciama
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        System.out.print("Escriba el nombre de su jugador: ");
        String nombre=sc.next();
        Mapa m1=new Mapa("Gothor",15,30,30,30);
        m1.addJugador(nombre);
        Persona j=m1.getJugador();
        System.out.println("");
        System.out.println("");
        System.out.println(""+m1.getDescripcion());
        System.out.println("");
        sc.nextLine();
        do{
            m1.impMapa();
            if(j.getVista()>2){
                j.setVista(j.getVista()-1);
            }
            System.out.println("");
            System.out.print(j.getNombre()+"[salud("+j.getSalud()+") energia("+j.getEnergia()+")]>>");
            String comando=sc.nextLine();
            String [] accion=comando.split(" ");
            switch(accion[0]){
                case "mirar":
                    if(accion.length!=2){
                        System.out.println("Ese comando no es correcto consulte los comados con ayuda");
                    }else{
                        m1.mirar(accion[1]);
                    }
                    break;
                case "mover":
                    if(accion.length!=2){
                        System.out.println("Ese comando no es correcto consulte los comados con ayuda");
                    }else{
                        switch(accion[1]){
                            case "norte":
                                m1.movernorte();
                                break;
                            case "sur":
                                m1.moversur();
                                break;
                            case "este":
                                m1.movereste();
                                break;
                            case "oeste":
                                m1.moveroeste();
                                break;
                            default:
                                System.out.println("Ese comando no es correcto consulte los comados con ayuda");
                                break;
                        }
                    }
                    break;
                case "ojearmochila":
                    j.impMochila();
                    break;
                case "coger":
                    if(accion.length!=2){
                        System.out.println("Ese comando no es correcto consulte los comados con ayuda");
                    }else{
                        m1.cogerObjeto(accion[1]);
                    }
                    break;
                case "dejar":
                    if(accion.length!=2){
                        System.out.println("Ese comando no es correcto consulte los comados con ayuda");
                    }else{
                        m1.dejarObjeto(accion[1]);
                    }
                    break;
                case "usar":
                    if(accion.length!=2){
                        System.out.println("Ese comando no es correcto consulte los comados con ayuda");
                    }else{
                        j.activarObjeto(accion[1]);
                    }
                    break;
                case "atacar":
                    if(accion.length!=2){
                        System.out.println("Ese comando no es correcto consulte los comados con ayuda");
                    }else{
                        m1.atacarEnemigo(accion[1]);
                    }
                    break;
                case "pasar":
                    System.out.println(j.getNombre()+" ha pasado el turno");
                    m1.atacarJugador(j);
                    j.reestablecerEnergia();
                    break;
                case "ayuda":
                    System.out.println("Las acciones posibles son: mirar, mover, movercelda, ojearmochila, cogerobjeto, dejarobjeto, usarobjeto, atacar, pasar");
                    System.out.println("");
                    System.out.println("1) mirar: el jugador puede mirar a objetos o a enemigos que tiene a su alrededor");
                    System.out.println("    +Este comado se compone de dos campos (mirar objetos/enemigos)");
                    System.out.println("2) mover: el jugador se mueve por celdas transitables en las direcciones norte, sur, este y oeste");
                    System.out.println("    +Este comando se compone de dos campos (mover direccion)");
                    System.out.println("3) ojearmochila: el jugador ve los objetos que tiene en su mochila");
                    System.out.println("4) coger: el jugador coge un objeto de una celda y lo guarda en su mochila");
                    System.out.println("    +Este comando se compone de dos campos (coger nombreobjeto)");
                    System.out.println("5) dejar: el jugador deja un objeto en la celda en la que se encuentra y lo elimina de su mochila");
                    System.out.println("    +Este comando se compone de dos campos (dejar nombreobjeto)");
                    System.out.println("6) usar: el jugador utiliza un objeto de su mochila");
                    System.out.println("    +Este comando se compone de dos campos (usar nombreobjeto)");
                    System.out.println("7) atacar: el jugador ataca a un enemigo que este en su campo visual especificando su nombre");
                    System.out.println("    +Este comando se compone de dos campos (atacar nombreenemigo)");
                    System.out.println("8) pasar: el jugador pasa su turno y reestablece su energia");
                    System.out.println("9) Mapa");
                    System.out.println("    +X->zona no visible");
                    System.out.println("    +█->muro o pared por la que no es posible transitar");
                    System.out.println("    +☻->enemigo");
                    System.out.println("    +☺->jugador");
                    System.out.println("");
                    System.out.print("Pulse enter para continuar con el juego ");
                    sc.nextLine();
                    break;
                default:
                    System.out.println("Ese comando no existe consulte la lista de comandos posibles con ayuda");
                    break;
            }
            if(j.getEnergia()==0){
                System.out.println("A "+j.getNombre()+" se le ha acabado la energia por lo que le toca pasar el turno");
                m1.atacarJugador(j);
                j.reestablecerEnergia();
            }
            System.out.println("");
        }while(j.getSalud()!=0);
        if(j.getSalud()==0)
            System.out.println("###GAME OVER###");
        else
            System.out.println("###Felicidades "+j.getNombre()+", "+m1.getNombreMapa()+ "ha sido liberada###");
    }
    
}