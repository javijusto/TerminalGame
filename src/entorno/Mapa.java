/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;

import java.util.ArrayList;
import java.awt.Point;
import java.security.SecureRandom;
import personajes.*;

/**
 *
 * @author javijusto
 */
public class Mapa {

    private String nombre;
    private String descripcion;
    private ArrayList<ArrayList<Celda>> matriz;
    private Persona jugador;
    private ArrayList<Persona> enemigos;

    public Mapa(String nombre, int filas, int columnas, int nobjetos, int numenemigos) {
        this.nombre = new String();
        setNombre(nombre);
        int nobj = 0;
        int p, x, y, op;
        SecureRandom rnd = new SecureRandom();
        matriz = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            ArrayList<Celda> fila = new ArrayList<>();
            for (int j = 0; j < columnas; j++) {
                Celda c;
                if (rnd.nextInt(100) < 5) {
                    c = new Celda(false);
                } else {
                    c = new Celda(true);
                }
                fila.add(c);
            }
            matriz.add(fila);
        }
        enemigos = new ArrayList<>();
        for (int i = 0; i < numenemigos; i++) {
            Persona e;
            do {
                y = rnd.nextInt(filas);
                x = rnd.nextInt(columnas);
            } while (!matriz.get(y).get(x).getPaso());
            e = new Persona("E"+(enemigos.size()+1), x, y,rnd.nextInt(6)+10);
            enemigos.add(e);
        }
        for (int i = 0; i < nobjetos; i++) {
            Objeto obj;
            p = rnd.nextInt(100);
            do {
                y = rnd.nextInt(filas);
                x = rnd.nextInt(columnas);
            } while (!matriz.get(y).get(x).getPaso());
            op = p % 3;
            nobj++;
            switch (op) {
                case 0:
                    obj = new Objeto("Binocular" + nobj, 5, "Binocular", rnd.nextInt(3) + 3);
                    matriz.get(y).get(x).meterObjeto(obj);
                    break;
                case 1:
                    obj = new Objeto("Botiquin" + nobj, 5, "Botiquin", rnd.nextInt(21) + 20);
                    matriz.get(y).get(x).meterObjeto(obj);
                    break;
                case 2:
                    obj = new Objeto("Armadura" + nobj, 0, "Armadura", rnd.nextInt(6)+10);
                    matriz.get(y).get(x).meterObjeto(obj);
                    System.out.println(x+" "+y);
                    break;
            }
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public Persona getJugador() {
        return jugador;
    }
    
    public String getNombreMapa(){
        return this.nombre;
    }

    public void addJugador(String nombre) {
        int x, y;
        SecureRandom rnd = new SecureRandom();
        do {
            y = rnd.nextInt(matriz.size());
            x = rnd.nextInt(matriz.get(0).size());
        } while (!matriz.get(y).get(x).getPaso());
        this.jugador = new Persona(nombre, x, y,0);
        setDescripcion("La milenaria ciudad de "+this.nombre+" ha sido custodiada durante cientos de años por los valerosos guerreros Duhai.\nPero la guerra que se lleva librando años en la región ha debilitado las defensas de la ciudadela.\n"+ jugador.getNombre() + " eres el unico guerrero vivo tras las murallas.\n ¡¡¡DEFIENDE A TU PUEBLO!!!");
    }
    
    public int esPersona(int x,int y){
        Point p=jugador.getPosicon();
        int i=0;
        if(p.x==x && p.y==y){
            i=1;
        }
        for(int j=0;j<enemigos.size();j++){
            Point p2=enemigos.get(j).getPosicon();
            if(esVisible(p2.x,p2.y))
                if(p2.x==x && p2.y==y){
                    i+=2;
                    break;
                }
        }
        return i;
    }

    public boolean esVisible(int x, int y) {
        return (x <= jugador.getPosicon().x + jugador.getVista() && x >= jugador.getPosicon().x-jugador.getVista() && y <= jugador.getPosicon().y + jugador.getVista() && y >= jugador.getPosicon().y-jugador.getVista());
        
    }
    
    public void impMapa() {
        System.out.print("       ");
        for (int j = 0; j < matriz.get(0).size(); j++) {
            System.out.format("%-3d", j+1);
        }
        System.out.println("");
        System.out.print("    ███");
        for (int j = 0; j < matriz.get(0).size(); j++) {
            System.out.print("███");
        }
        System.out.println("███");
        for (int i = 0; i < matriz.size(); i++) {
            System.out.format("%3d ███", i+1);
            for (int j = 0; j < matriz.get(0).size(); j++) {
                if (matriz.get(i).get(j).getPaso()) {
                    if(esVisible(j,i)){
                        int persona=esPersona(j,i);
                        switch(persona){
                            case 0:
                                System.out.print("   ");
                                break;
                            case 1:
                                System.out.print(" ☺ ");
                                break;
                            case 2:
                                System.out.print(" ☻ ");
                                break;
                            case 3:
                                System.out.print("☺ ☻");
                                break;
                        }
                    }else{
                        System.out.print("XXX");
                    }
                } else {
                    System.out.print("███");
                }
            }
            System.out.println("███");
        }
        System.out.print("    ███");
        for (int j = 0; j < matriz.get(0).size(); j++) {
            System.out.print("███");
        }
        System.out.println("███");
    }

    public void mirar(String tipo) {
        Point p;
        boolean encontrado=false;
        p = jugador.getPosicon();
        switch(tipo){
            case "objetos":
                matriz.get(p.y).get(p.x).impObjetos();
                break;
            case "enemigos":
                for(int i=0;i<enemigos.size();i++){
                    if(esVisible(enemigos.get(i).getPosicon().x,enemigos.get(i).getPosicon().y)){
                        System.out.print("El enemigo "+enemigos.get(i).getNombre()+" esta en la posicion ");
                        enemigos.get(i).impPosicion();
                        System.out.println("");
                        encontrado=true;
                    }
                }
                if(!encontrado){
                    System.out.println("No hay ningun enemigo en tu posicion");
                }
                break;
            default:{
                System.out.println("Ese comando no es correcto consulte los comados con ayuda");
                break;
            }
        }
    }

    public void movernorte() {
        Point p;
        p = jugador.getPosicon();
        if(jugador.getEnergia()>=10){
            if ((p.y - 1) >= 0 && matriz.get(p.y - 1).get(p.x).getPaso()) {
                this.jugador.setPosicion(p.x, p.y - 1);
                this.jugador.cambiaEnergia(-10);
            } else {
                System.out.println("La celda norte no es transitable");
            }
        }else{
            System.out.println("No tienes energia suficiente para hacer esa accion");
        }
    }
    
    public void moversur() {
        Point p;
        p = jugador.getPosicon();
        if(jugador.getEnergia()>=10){
            if ((p.y + 1) < matriz.size() && matriz.get(p.y + 1).get(p.x).getPaso()) {
                this.jugador.setPosicion(p.x, p.y + 1);
                this.jugador.cambiaEnergia(-10);
            } else {
                System.out.println("La celda sur no es transitable");
            }
        }else{
            System.out.println("No tienes energia suficiente para hacer esa accion");
        }
    }
    
    public void movereste() {
        Point p;
        p = jugador.getPosicon();
        if(jugador.getEnergia()>=10){
            if ((p.x + 1) < matriz.get(0).size() && matriz.get(p.y).get(p.x + 1).getPaso()) {
                this.jugador.setPosicion(p.x + 1, p.y);
                this.jugador.cambiaEnergia(-10);
            } else {
                System.out.println("La celda este no es transitable");
            }
        }else{
            System.out.println("No tienes energia suficiente para hacer esa accion");
        }
    }
    
    public void moveroeste() {
        Point p;
        p = jugador.getPosicon();
        if(jugador.getEnergia()>=10){
            if ((p.x - 1) >= 0 && matriz.get(p.y).get(p.x - 1).getPaso()) {
                this.jugador.setPosicion(p.x - 1, p.y);
                this.jugador.cambiaEnergia(-10);
            } else {
                System.out.println("La celda oeste no es transitable");
            }
        }else{
            System.out.println("No tienes energia suficiente para hacer esa accion");
        }
    }
    
    public boolean esCritico(){
        int n=100;
        SecureRandom rnd=new SecureRandom();
        for(int i=0;i<10;i++){
            n=rnd.nextInt(100);
        }
        return (n<=24);
    }
    
    public void atacarEnemigo(String e){
        int i;
        boolean encontrado=false;
        if(jugador.getEnergia()>=30){
            for(i=0;i<enemigos.size();i++){
                if(e.compareTo(enemigos.get(i).getNombre())==0){
                    encontrado=true;
                    break;
                }
            }
            if(encontrado){
                if(esVisible(enemigos.get(i).getPosicon().x,enemigos.get(i).getPosicon().y)){
                    if(esCritico()){
                        System.out.println("Ataque critico a "+enemigos.get(i).getNombre());
                        enemigos.get(i).cambiaSalud(jugador.efectoArma()*2+enemigos.get(i).usarArmadura(jugador.efectoArma()*2));
                    }else{
                        System.out.println("Ataque a "+enemigos.get(i).getNombre());
                        enemigos.get(i).cambiaSalud(jugador.efectoArma()+enemigos.get(i).usarArmadura(jugador.efectoArma()));
                    }
                    this.jugador.cambiaEnergia(-30);
                    if(enemigos.get(i).getSalud()==0){
                        System.out.println(enemigos.get(i).getNombre()+" ha muerto");
                        enemigos.remove(i);
                    }
                }
            }else{
                System.out.println("Ese enemigo no se encuentra en su campo de vision");
            }
        }else{
            System.out.println("No tienes energia suficiente para hacer esa accion");
        }
    }
    
    public void atacarJugador(Persona j){ //TODO armadura
        int i;
        for(i=0;i<enemigos.size();i++){
            if(enemigos.get(i).getEnergia()>=30){
                if(esVisible(enemigos.get(i).getPosicon().x,enemigos.get(i).getPosicon().y)){
                        if(esCritico()){
                            System.out.println("Ataque critico de "+enemigos.get(i).getNombre());
                            jugador.cambiaSalud(enemigos.get(i).efectoArma()*2+jugador.usarArmadura(enemigos.get(i).efectoArma()*2));
                        }else{
                            System.out.println("Ataque de "+enemigos.get(i).getNombre());
                            jugador.cambiaSalud(enemigos.get(i).efectoArma()+jugador.usarArmadura(enemigos.get(i).efectoArma()));
                        }
                        this.enemigos.get(i).cambiaEnergia(-30);
                }
            }
        }
        if(jugador.getSalud()==0){
            System.out.println(jugador.getNombre()+" ha muerto");
        }
    }
    
    public void dejarObjeto(String nombreobj){
        Objeto obj=jugador.sacaMochila(nombreobj);
        Point p=jugador.getPosicon();
        if(obj!=null)
            matriz.get(p.y).get(p.x).meterObjeto(obj);
        else
            System.out.println("Ese objeto no se encuentra en su mochila");
    }
    
    public void cogerObjeto(String nombreobj){
        Point p=jugador.getPosicon();
        Objeto obj=matriz.get(p.y).get(p.x).sacarObjeto(nombreobj);
        if(obj!=null){
            if(obj.getTipo().equals("Armadura")){
                jugador.setArmadura(obj);
                System.out.println(jugador.getNombre()+" ha equipado una armadura");
            }else{
                jugador.meteMochila(obj);
                System.out.println(jugador.getNombre()+" ha metido "+nombreobj+" en su mochila");
            }
        }else{
            System.out.println("Ese objeto no se encuentra en su celda");
        }
    }
}
