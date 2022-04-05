package machine;
import java.util.Scanner;

enum Process {
    BUY, FILL_WATTER, FILL_MILK, FILL_COFFEE, FILL_CUPS, DONE, TAKE, REMAINING, EXIT;
}
enum Coffee {
    ESPRESSO (250, 0, 12, 4),
    LATTE (350, 75, 20, 7),
    CAPPUCCINO (200, 100, 12, 6),
    NONE (0, 0, 0, 0);

    public final int neededWater;
    public final int neededMilk;
    public final int neededCoffee;
    public final int price;

    private Coffee(int neededWater, int neededMilk, int neededCoffee, int price) {
        this.neededWater = neededWater;
        this.neededMilk = neededMilk;
        this.neededCoffee = neededCoffee;
        this.price = price;
    }
}

class CoffeeMenu {
    public int cups = 9;
    public int mlOfWater = 400;
    public int mlOfMilk = 540;
    public int gOfCoffee = 120;
    public int money = 550;
    Coffee type = Coffee.NONE;;
    Process status = Process.DONE;

    public CoffeeMenu () {

    }

    void host(String action) {
        switch (action) {
            case "buy":
                this.status = Process.BUY;
                break;
            case "fill":
                this.status = Process.FILL_WATTER;
                break;
            case "take":
                this.status = Process.TAKE;
                break;
            case "remaining":
                this.status = Process.REMAINING;
                break;
            case "exit":
                this.status = Process.EXIT;
                break;
            case "1":
                this.type = Coffee.ESPRESSO;
                break;
            case "2":
                this.type = Coffee.LATTE;
                break;
            case "3":
                this.type = Coffee.CAPPUCCINO;
                break;
            case "back":
                this.type = Coffee.NONE;
                break;
            default:
                addSupplies(action);
                break;
        }
        manager(action);
    }

    private void manager (String data) {
        switch (status) {
            case BUY:
                if (this.type != Coffee.NONE) {
                    buyCoffee();
                }
                break;
            case FILL_WATTER:

                break;
            case TAKE:
                this.money = 0;
                break;
            case REMAINING:
                System.out.println("The coffee machine has:");
                System.out.println(this.mlOfWater + " ml of water");
                System.out.println(this.mlOfMilk + " ml of milk");
                System.out.println(this.gOfCoffee + " g of coffee beans");
                System.out.println(this.cups + " disposable cups");
                System.out.println("$" + this.money + " of money");
                break;
            case EXIT:

                break;
        }
    }

    private void addSupplies(String amount) {
        switch (status) {
            case FILL_WATTER:
                this.mlOfWater += Integer.parseInt(amount);
                this.status = Process.FILL_MILK;
                break;
            case FILL_MILK:
                this.mlOfMilk += Integer.parseInt(amount);
                this.status = Process.FILL_COFFEE;
                break;
            case FILL_COFFEE:
                this.gOfCoffee += Integer.parseInt(amount);
                this.status = Process.FILL_CUPS;
                break;
            case FILL_CUPS:
                this.cups += Integer.parseInt(amount);
                this.status = Process.DONE;
                break;
        }
    }

    private void buyCoffee() {
        switch(type) {
            case ESPRESSO:
                if(this.cups >= 1 && this.mlOfWater >= 250 && this.gOfCoffee >= 16) {
                    System.out.println("I have enough resources, making you a coffee!");
                    this.cups--;
                    this.mlOfWater -= 250;
                    this.gOfCoffee -= 16;
                    this.money += 4;
                } else {
                    if(this.cups < 1) {
                        System.out.println("Sorry, not enough cups!");
                    }
                    else if(this.mlOfWater < 250) {
                        System.out.println("Sorry, not enough water!");
                    }
                    else if(this.gOfCoffee < 16) {
                        System.out.println("Sorry, not enough coffee!");
                    }
                }
                break;
            case LATTE:
                if(this.cups >= 1 && this.mlOfWater >= 350 && this.mlOfMilk >= 75 && this.gOfCoffee >= 20) {
                    System.out.println("I have enough resources, making you a coffee!");
                    this.cups--;
                    this.mlOfWater -= 350;
                    this.mlOfMilk -= 75;
                    this.gOfCoffee -= 20;
                    this.money += 7;
                } else {
                    if(cups < 1) {
                        System.out.println("Sorry, not enough cups!");
                    }
                    else if(this.mlOfWater < 350) {
                        System.out.println("Sorry, not enough water!");
                    }
                    else if(this.mlOfMilk < 75) {
                        System.out.println("Sorry, not enough milk!");
                    }
                    else if(this.gOfCoffee < 20) {
                        System.out.println("Sorry, not enough coffee!");
                    }
                }
                break;
            case CAPPUCCINO:
                if(this.cups >= 1 && this.mlOfWater >= 200 && this.mlOfMilk >= 100 && this.gOfCoffee >= 12) {
                    System.out.println("I have enough resources, making you a coffee!");
                    this.cups--;
                    this.mlOfWater -= 200;
                    this.mlOfMilk -= 100;
                    this.gOfCoffee -= 12;
                    this.money += 6;
                } else {
                    if(this.cups < 1) {
                        System.out.println("Sorry, not enough cups!");
                    }
                    else if(this.mlOfWater < 200) {
                        System.out.println("Sorry, not enough water!");
                    }
                    else if(this.mlOfMilk < 100) {
                        System.out.println("Sorry, not enough milk!");
                    }
                    else if(this.gOfCoffee < 12) {
                        System.out.println("Sorry, not enough coffee!");
                    }
                }
                break;
            default:
                break;
        }
        this.type = Coffee.NONE;
    }
}

public class CoffeeMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option;
        String type;
        String fill;
        boolean keeRunning = true;
        CoffeeMenu parqueDelta = new CoffeeMenu();

        while(keeRunning) {
            System.out.println("Write action (buy, fill, take, remaining, exit)");
            option = scanner.next();
            switch(option) {
                case "buy":
                    parqueDelta.host(option);
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    type = scanner.next();
                    parqueDelta.host(type);
                    break;
                case "fill":
                    parqueDelta.host(option);
                    System.out.println("Write how many ml of water you want to add:");
                    fill = scanner.next();
                    parqueDelta.host(fill);
                    System.out.println("Write how many ml of milk you want to add:");
                    fill = scanner.next();
                    parqueDelta.host(fill);
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    fill = scanner.next();
                    parqueDelta.host(fill);
                    System.out.println("Write how many disposable cups of coffee you want to add: ");
                    fill = scanner.next();
                    parqueDelta.host(fill);
                    break;
                case "take":
                    parqueDelta.host(option);
                    break;
                case "remaining":
                    parqueDelta.host(option);
                    break;
                case "exit":
                    parqueDelta.host(option);
                    keeRunning = false;
                    break;
            }
        }
    }
}
