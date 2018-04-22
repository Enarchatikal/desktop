package map;
import field.*;
import java.util.Random;
import java.util.Scanner;
import character.*;

public class Terkep {

    public myCharacter karakter=new myCharacter();
    public MezoTipus[][] terkep = new MezoTipus[100][100];
    private int terkepNagysag;
    private int tengerOldala;
    private int xkoord;
    private int ykoord;
    char regiTipus='E';
    private boolean ujPalya=false;


    public int getXkoord() {
        return xkoord;
    }

    public void setXkoord(int xkoord) {
        this.xkoord = xkoord;
    }

    public int getYkoord() {
        return ykoord;
    }

    public void setYkoord(int ykoord) {
        this.ykoord = ykoord;
    }

    public int getTerkepNagysag() {
        return terkepNagysag;
    }

    public void setTerkepNagysag(int terkepNagysag) {
        this.terkepNagysag = terkepNagysag;
    }

    public Terkep() {
        Random rand = new Random();
        terkepNagysag = 8;//rand.nextInt(15) + 15;
        tengerOldala = rand.nextInt(2) + 1;


        for (int i = 0; i < terkepNagysag; i++) {
            for (int j = 0; j < terkepNagysag; j++) {
                terkepOsszeallas(i, j);
            }

        }
        while (true) {
            int iRand=rand.nextInt(terkepNagysag);
            int jRand=rand.nextInt(terkepNagysag);
            if(terkep[iRand][jRand] instanceof FuvesMezo){
                terkep[iRand][jRand]=new AranyPiramis();
                break;
            }
        }
        while (true) {
            int iRand=rand.nextInt(terkepNagysag);
            int jRand=rand.nextInt(terkepNagysag);
            if((terkep[iRand][jRand] instanceof Tenger)&&((terkep[iRand+1][jRand] instanceof FuvesMezo)||terkep[iRand][jRand+1] instanceof FuvesMezo)){
                terkep[iRand][jRand]=new ExpediciosHajo();
                xkoord=jRand;
                ykoord=iRand;
                break;
            }
        }
        int toSzamlalo=0;
        while(true){


            int iRand=rand.nextInt(terkepNagysag-5)+2;
            int jRand=rand.nextInt(terkepNagysag-5)+2;


                if((terkep[iRand][jRand] instanceof FuvesMezo)&&!(terkep[iRand-1][jRand] instanceof Tenger)&&
                        !(terkep[iRand][jRand-1] instanceof Tenger)&&!(terkep[iRand-1][jRand-1] instanceof Tenger)){
                    if(iRand+4<terkepNagysag&&jRand+4<terkepNagysag){
                        int toTipus=rand.nextInt(4);
                        toTipus=0;

                        if(toTipus==0){

                            toCsinalo(iRand,jRand);
                            toCsinalo(iRand,(jRand+1));
                            toCsinalo((iRand+1),jRand);
                            toCsinalo((iRand+1),(jRand+1));

                        }
                        else if(toTipus==1){
                            toCsinalo(iRand,jRand);
                            toCsinalo(iRand,jRand+1);
                            toCsinalo(iRand+1,jRand);
                            toCsinalo(iRand+2,jRand);
                        }
                        else if(toTipus==2){
                            toCsinalo(iRand,jRand);
                            toCsinalo(iRand,jRand+1);
                        }
                        else if(toTipus==3){
                            toCsinalo(iRand,jRand);
                        }
                    }

                    toSzamlalo++;
                }
            if(toSzamlalo>=terkepNagysag*terkepNagysag/75){
                break;
            }
        }
        int bozotSzam=0;
        while(true){


            int iRand=rand.nextInt(terkepNagysag);
            int jRand=rand.nextInt(terkepNagysag);

            if(terkep[iRand][jRand] instanceof FuvesMezo||terkep[iRand][jRand] instanceof NedvesTalaj){
                terkep[iRand][jRand]=new Bozot();
                bozotSzam++;
            }
            if(bozotSzam>=terkepNagysag*terkepNagysag/10){
                break;
            }

        }
        int dzsungelSzam=0;
        while(true){


            int iRand=rand.nextInt(terkepNagysag);
            int jRand=rand.nextInt(terkepNagysag);

            if(terkep[iRand][jRand] instanceof FuvesMezo||terkep[iRand][jRand] instanceof NedvesTalaj){
                terkep[iRand][jRand]=new Dzsungel();
                dzsungelSzam++;
            }
            if(dzsungelSzam>=terkepNagysag*terkepNagysag/20){
                break;
            }

        }
        int lavaSzam=0;
        while(true){


            int iRand=rand.nextInt(terkepNagysag);
            int jRand=rand.nextInt(terkepNagysag);

            if(terkep[iRand][jRand] instanceof FuvesMezo||terkep[iRand][jRand] instanceof NedvesTalaj){
                terkep[iRand][jRand]=new Lava();
                lavaSzam++;
            }
            if(lavaSzam>=terkepNagysag*terkepNagysag/75){
                break;
            }

        }

        int hegySzam=0;
        while(true){


            int iRand=rand.nextInt(terkepNagysag);
            int jRand=rand.nextInt(terkepNagysag);

            if(terkep[iRand][jRand] instanceof FuvesMezo||terkep[iRand][jRand] instanceof NedvesTalaj){
                terkep[iRand][jRand]=new Hegy();
                while(true){
                    int hegyVonulat=rand.nextInt(3);
                    if(hegyVonulat>0&&iRand<terkepNagysag-1){
                        if(terkep[iRand+1][jRand] instanceof FuvesMezo||terkep[iRand+1][jRand] instanceof NedvesTalaj){
                            iRand++;
                            terkep[iRand][jRand]=new Hegy();
                        }
                        else{
                            break;
                        }
                    }
                    else{
                        break;}
                }
                while(true){
                    int hegyVonulat=rand.nextInt(3);
                    if(hegyVonulat>0&&jRand<terkepNagysag-1){
                        if(terkep[iRand][jRand+1] instanceof FuvesMezo||terkep[iRand][jRand+1] instanceof NedvesTalaj){
                            jRand++;
                            terkep[iRand][jRand]=new Hegy();
                        }
                        else {
                            break;
                        }
                    }
                    else{
                        break;
                    }
                }

                hegySzam++;

            }
            if(hegySzam>=terkepNagysag*terkepNagysag/75){
                break;
            }

        }



        mozgasEllenorzes(xkoord,ykoord);
    }


    private void toCsinalo(int i,int j){
        for(int a=i-1;a<i+2;a++){
            for(int b=j-1;b<j+2;b++){
                if(terkep[a][b] instanceof FuvesMezo){
                    terkep[a][b]=new NedvesTalaj();

                }
            }
        }
        if(!(terkep[i+1][j] instanceof Tenger)&&!(terkep[i][j+1] instanceof Tenger)&&!(terkep[i+1][j-1] instanceof Tenger)&&!(terkep[i-1][j+1] instanceof Tenger)){
            terkep[i][j]=new To();}
    }

    private void terkepKiiras(){

        for(int i=0;i<terkepNagysag;i++){
            for(int j=0;j<terkepNagysag;j++) {
                System.out.print(terkep[i][j].getTipus()+" ");

            }
            System.out.println();
        }
    }

    private void terkepOsszeallas(int i, int j){

                if(j==0){
                    terkep[i][j] = new Tenger();
                }
                else if((terkepNagysag/3>=j)&&(terkep[i][j-1] instanceof Tenger)){
                    Random rand=new Random();
                    int tengere=rand.nextInt(terkepNagysag/3);
                        if(tengere>0){
                            terkep[i][j] = new Tenger();
                        }
                        else{
                            terkep[i][j] = new FuvesMezo();
                        }
                }else{
                    terkep[i][j]=new FuvesMezo();
                }
                if(tengerOldala>1){
                    if(i==0){
                        terkep[i][j] = new Tenger();
                    }
                    else if((terkepNagysag/3>=i)&&(terkep[i-1][j] instanceof Tenger)){
                        Random rand=new Random();
                        int tengere=rand.nextInt(terkepNagysag/3);
                        if(tengere>0) {
                            terkep[i][j] = new Tenger();
                        }
                    }
                }


    }




    private void mozgasEllenorzes(int x,int y){
        terkep[ykoord][xkoord].setTipus('X');
        terkepKiiras();
        while(!ujPalya) {
            System.out.println("mozgas: w felfelé, a balra, s lefele, d jobbra   e: kilepes");
            System.out.println(x + " " + y);
            Scanner sc = new Scanner(System.in);
            String parancs=sc.nextLine();
            System.out.println(parancs);
            if(parancs.equals("w")&&y>0){
                if(terkep[y-1][x].isJarhato()==true){
                    terkep[y][x].setTipus(regiTipus);
                    y--;
                    regiTipus=terkep[y][x].getTipus();
                    mezoAkcio(x,y);
                    terkep[y][x].setTipus('X');
                    terkepKiiras();
                }
                else{
                    System.out.println("erre nem lehet menni");
                    terkepKiiras();
                }
            }
            else if(parancs.equals("a")&&x>0){
                if(terkep[y][x-1].isJarhato()==true){
                    terkep[y][x].setTipus(regiTipus);
                    x--;
                    regiTipus=terkep[y][x].getTipus();
                    mezoAkcio(x,y);
                    terkep[y][x].setTipus('X');
                    terkepKiiras();
                }
                else{
                    System.out.println("erre nem lehet menni");
                    terkepKiiras();
                }
            }
            else if(parancs.equals("s")&&y<terkepNagysag-1){
                    if(terkep[y+1][x].isJarhato()==true){
                        terkep[y][x].setTipus(regiTipus);
                        y++;
                        regiTipus=terkep[y][x].getTipus();
                        mezoAkcio(x,y);
                        terkep[y][x].setTipus('X');
                        terkepKiiras();
                    }
                    else{
                        System.out.println("erre nem lehet menni");
                        terkepKiiras();
                    }
            }
            else if(parancs.equals("d")&&x<terkepNagysag-1){
                if(terkep[y][x+1].isJarhato()==true){
                    terkep[y][x].setTipus(regiTipus);
                    x++;
                    regiTipus=terkep[y][x].getTipus();
                    mezoAkcio(x,y);
                    terkep[y][x].setTipus('X');
                    terkepKiiras();
                }
                else{
                    System.out.println("erre nem lehet menni");
                    terkepKiiras();
                }
            }
            else if(parancs.equals("e")){
                System.out.println("Jatek befejezese");
                break;
            }
            else{
                System.out.println("Ertelmes karaktert adjon meg!");
                terkepKiiras();
            }
        }

    }

    private void mezoAkcio(int x,int y){
        karakter.ujEgyebTargy("Bozotvago");

        if(terkep[y][x].isBozotvagotHasznal()==true){
            karakter.felhasznalTargy("Bozotvago");
            terkep[y][x]=new FuvesMezo();
            regiTipus='Z';
        }
        Scanner scan=new Scanner(System.in);
        Random rand=new Random();
        if(terkep[y][x].isKereskedik()==true){
            int kotel=rand.nextInt(3);
            int hus=rand.nextInt(3);
            int uveggolyo=rand.nextInt(3);
            int whiskey=0;
            int bozotvago=0;
            int gyumolcs=0;
            int kabitoszer=0;

            if(terkep[y][x].getTipus()!='F'){
                uveggolyo=rand.nextInt(3);
                whiskey=rand.nextInt(3);
                bozotvago=rand.nextInt(3);
            }
            else{
                gyumolcs=rand.nextInt(3);
                kabitoszer=rand.nextInt(3);
            }
            while(true){
                 System.out.println("Akarsz venni valamit? ");
                 String Kereskedele=scan.nextLine();
                 if(Kereskedele.equals("i")) {
                     System.out.println("Ennyi aranyad van: " + karakter.getArany());
                     if(kotel>0){


                             kotel=vesz("Kotel",kotel);

                     }
                     if(hus>0){
                         hus=vesz("Hus",hus);
                     }
                     if(gyumolcs>0){
                         gyumolcs=vesz("Gyumolcs",gyumolcs);
                     }
                     if(kabitoszer>0){
                         kabitoszer=vesz("Kabitoszer",kabitoszer);
                     }
                     if(uveggolyo>0){
                         uveggolyo=vesz("Uveggolyo",uveggolyo);
                     }
                     if(whiskey>0){
                         whiskey=vesz("Whiskey",whiskey);
                     }
                     if(bozotvago>0){
                         bozotvago=vesz("Bozotvago",bozotvago);
                     }
                     if(kabitoszer==0&&gyumolcs==0&&hus==0&&kotel==0){
                         System.out.println("nincs semmi amit vehetnel");
                         break;
                     }

                 }
                 else if(Kereskedele.equals("n")){
                     break;
                 }
                 else{
                     System.out.println("Rossz karaktert adtal meg");
                 }
            }
            kotel=karakter.targyDarab("Kotel");
            hus=karakter.targyDarab("Hus");
            uveggolyo=karakter.targyDarab("Uveggolyo");
            whiskey=karakter.targyDarab("Whiskey");
            bozotvago=karakter.targyDarab("Bozotvago");
            gyumolcs=karakter.targyDarab("Gyumolcs");
            kabitoszer=karakter.targyDarab("Kabitoszer");
            while(true){
                System.out.println("Akarsz eladni valamit?");
                String elade=scan.nextLine();

                if(elade.equals("i")){


                    if(kotel>0){
                        kotel=elad("Kotel");
                    }
                    if(hus>0){
                        kotel=elad("Hus");
                    }
                    if(uveggolyo>0){
                        kotel=elad("Uveggolyo");
                    }
                    if(whiskey>0){
                        kotel=elad("Whiskey");
                    }
                    if(bozotvago>0){
                        kotel=elad("Bozotvago");
                    }
                    if(gyumolcs>0){
                        kotel=elad("Gyumolcs");
                    }
                    if(kabitoszer>0){
                        kotel=elad("Kabitoszer");
                    }
                }else{
                    break;
                }
            }
        }
        if(terkep[y][x].isAjanlCsapattarsat()&&karakter.getViszony()>=2){
            System.out.println("ejutott idaig?");
            int csapattarsAjanlo=rand.nextInt(5);
            if(csapattarsAjanlo>=0){
            if(karakter.csapattarsnakHely()){
                System.out.println("Csapattarsat ajanlanak, elfogadod?(150arany most ennyi van: "+ karakter.getArany() +")");
                String tagfogadas= scan.nextLine();
                if(tagfogadas.equals("i")) {
                    karakter.ujCsapattag("Felderito");
                    int arany = karakter.getArany();
                    arany = arany - 150;
                    karakter.setArany(arany);

                }
            }


            }
        }
        if(terkep[y][x].isHazaMehet()){
            System.out.println("Biztos hazamegy?");
            String hazamegye=scan.nextLine();
            if (hazamegye.equals("i")) {

                System.out.println("Sok bullshit informacio");
                ujPalya=true;



                System.out.println("Vegeredmeny: blabla");
            }
            }
        }


    private int elad(String mit){
        if(regiTipus=='F'){
            if(mit.equals("Uveggolyo")){
                boolean eladta=karakter.felhasznalTargy(mit);
                if(eladta){
                    int osszeg=karakter.getArany();
                    osszeg=osszeg+13;
                    karakter.setArany(osszeg);
                }
            }else if(mit.equals("Mutargy")){
                boolean eladta=karakter.felhasznalTargy(mit);
                if(eladta){
                int osszeg=karakter.getArany();
                osszeg=osszeg+5;
                karakter.setArany(osszeg);}
            }
            else {
                boolean eladta = karakter.felhasznalTargy(mit);
                if (eladta) {
                    int osszeg = karakter.getArany();
                    osszeg = osszeg + 8;
                    karakter.setArany(osszeg);
                }
            }
        }
        else{

            if(mit.equals("Mutargy")){
                boolean eladta=karakter.felhasznalTargy(mit);
                if(eladta){
                    int osszeg=karakter.getArany();
                    osszeg=osszeg+13;
                    karakter.setArany(osszeg);}
            }
            else {
                boolean eladta = karakter.felhasznalTargy(mit);
                if (eladta) {
                    int osszeg = karakter.getArany();
                    osszeg = osszeg + 8;
                    karakter.setArany(osszeg);
                }
            }

        }
        return karakter.targyDarab(mit);

    }

    private int vesz(String mit,int mennyiVan){
        Scanner scan=new Scanner(System.in);
        if(mennyiVan==0){
            return 0;
        }
        System.out.println("Akarsz venni "+mit+" 10 aranyert?(ennyi van meg: "+mennyiVan+") i ha igen, barmi mas ha nem");
        String valasz=scan.nextLine();
        if(valasz.equals("i")) {
            karakter.ujEgyebTargy(mit);
            mennyiVan--;
            int a = karakter.getArany();
            a = a - 10;
            karakter.setArany(a);
        } else{
            return 0;
        }
        return mennyiVan;
    }

    public void start(){


    }

    public int aranyVisszaadas(){
        return karakter.getArany();
    }



}
