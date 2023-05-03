import java.util.Objects;
import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static int [] heroesHealth ={270,260,250,100,};
    public static int [] heroesDamage = {25,20,15,0,};
    public static String [] heroesAttackType = {"Physical","Magical","Telepathic","Medic",};
    public static double luckyChance = 0.2; // задаем шанс уклонения Лакки, 0,2=20%
    public static boolean isBossStunned = false;
    public static String bossDefenceType;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()){//пока метод isGameFinished будет возвращать false цикл работает
            round();
        }
    }
    public static void bossChangesDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose"+" "+bossDefenceType);

    }
    public static void medic(){
        int healthPoint = 20;
        int r = 0;
        for (String name:heroesAttackType ) {
            if (name.equals( "Medic")){
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth [i]<100 && heroesHealth[i] > 0){
                        heroesHealth[i]+=healthPoint;
                        System.out.println("Medic health - "+ heroesHealth[i] +" "+ heroesAttackType[i]);
                        break;
                    }
                }
            }
            r++;
        }
    }
   /* public static void golem(){
        for (int i = 0; i < heroesHealth.length; i++) {
            int damage = bossDamage/5;
        if (heroesAttackType[i].equals("Golem")) {
            heroesDamage[i] = heroesDamage[i] / 5;
            System.out.println("Golem took " + damage + " damage from boss");
            }
        }
    }

    */

    public static void bossHits() {
        //от жизни итого героя[i] героя мы отнимаем дамаг босса
        //и получившийся результат сохраняем обратно как жизнь итого[i] героя
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] - bossDamage<0) {
                bossHealth= bossHealth+heroesHealth[i];
            }else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }
    public static void heroesHits () {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth>0 && heroesHealth[i]>0){
                if (heroesAttackType[i] == bossDefenceType){
                    bossHealth= bossHealth+heroesDamage[i];
                }
            if (bossHealth-heroesDamage[i]<0){
                bossHealth=0;
            }else {
                bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }


        public static boolean isGameFinished(){
        if (bossHealth<=0){
            System.out.println("Heroes WON!!!");
            return true;
        }
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i]>0){
                return false;
            } else{
                System.out.println("Boss WON!!!");
                return true;
            }
        }
        return false;
    }


    public static void round(){
        bossHits();
        bossChangesDefence();
        heroesHits();
        medic();
       // golem();
        printStatistics();


    }
    public static void printStatistics(){
        System.out.println("---------------");
        System.out.println("Boss Health:"+bossHealth+"{"+bossDamage+"}");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println("Hero:"+heroesAttackType[i]+" "+heroesHealth[i]+"{"+heroesDamage[i]+"}");
        }
    }
}
















