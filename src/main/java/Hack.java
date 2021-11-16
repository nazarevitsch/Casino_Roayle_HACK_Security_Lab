import enity.Account;
import enity.PlayMode;
import enity.Result;

import java.time.Instant;

public class Hack {

    public static void tryToWinMT() {
        System.out.println("Try to hack and win Mt.");

        ApiEndpoints apiEndpoints = new ApiEndpoints();

        long playerId = getFreeAccountId();
        long[] winNumbers = new long[20];
        int lastAmountOfMoney = 0;
        long startSeed = 0;

        for (int i = 0; i < winNumbers.length; i++) {
            Result result = apiEndpoints.play(PlayMode.Mt, playerId, 1, (int)(Math.random() * 20)).body().as(Result.class);
            if (startSeed == 0) {
                startSeed = Instant.now().getEpochSecond();
            }
            winNumbers[i] = result.getRealNumber();
            lastAmountOfMoney = result.getAccount().getMoney();
        }

        System.out.println("Money after collect data: " + lastAmountOfMoney);

        MersenneTwisterRandomizer mersenneTwisterRandomizer = null;
        go: while (true) {
            mersenneTwisterRandomizer = new MersenneTwisterRandomizer(startSeed);
            for (int i = 0; i < 3500; i++) {
                for (int l = 0; l < winNumbers.length; l++) {
                    if (mersenneTwisterRandomizer.getRandomNumber() == winNumbers[l]) {
                        i++;
                        if (l == winNumbers.length - 1) {
                            break go;
                        }
                    } else {
                        break;
                    }
                }
            }
            startSeed--;
        }

        System.out.println("Seed was found: " + startSeed);

        while (true) {
            Result result = apiEndpoints.play(PlayMode.Mt, playerId, lastAmountOfMoney, mersenneTwisterRandomizer.getRandomNumber()).body().as(Result.class);
            lastAmountOfMoney = result.getAccount().getMoney();

            System.out.println("Successful bet, money after: " + result.getAccount().getMoney());
            if (result.getAccount().getMoney() > 1000000) {
                break;
            }
        }

        System.out.println("MT was hacked!!!\n\n\n");
    }

    public static void tryToWinBetterMT() {
        System.out.println("Try to hack and win Better Mt.");

        ApiEndpoints apiEndpoints = new ApiEndpoints();

        long playerId = getFreeAccountId();
        int lastAmountOfMoney= 0;
        long[] winNumbers = new long[624];

        for (int i = 0; i < winNumbers.length; i++) {
            Result result = apiEndpoints.play(PlayMode.BetterMt, playerId, 1, (int)(Math.random() * 20)).body().as(Result.class);
            winNumbers[i] = result.getRealNumber();
            lastAmountOfMoney = result.getAccount().getMoney();
        }

        System.out.println("Money after collect data: " + lastAmountOfMoney);

        MersenneTwisterRandomizerReverse reverse = new MersenneTwisterRandomizerReverse();
        for (int i = 0; i < winNumbers.length; i++) {
            reverse.put(i , winNumbers[i]);
        }

        MersenneTwisterRandomizer randomizer = new MersenneTwisterRandomizer(reverse.getArray());

        while (true) {
            Result result = apiEndpoints.play(PlayMode.BetterMt, playerId, lastAmountOfMoney, randomizer.getRandomNumber()).body().as(Result.class);
            lastAmountOfMoney = result.getAccount().getMoney();
            System.out.println("Successful bet, money after: " + result.getAccount().getMoney());
            if (result.getAccount().getMoney() > 1000000) {
                break;
            }
        }

        System.out.println("Better MT was hacked!!!\n\n\n");
    }

    private static long getFreeAccountId() {
        ApiEndpoints apiEndpoints = new ApiEndpoints();
        long playerId = 1;
        while (true) {
            try {
                Account account = apiEndpoints.createPlayer(playerId).body().as(Account.class);
                System.out.println("For this round we use account with ID: " + account.getId() + ", with money: " + account.getMoney());
                return account.getId();
            } catch (Exception e) {
                playerId++;            }
        }
    }
}
