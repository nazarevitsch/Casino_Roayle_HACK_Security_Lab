import enity.PlayMode;
import enity.Result;

import java.time.Instant;

public class Hack {

    public static void tryToWinMTSimple() {
        ApiEndpoints apiEndpoints = new ApiEndpoints();

        long[] winNumbers = new long[20];
        long startTime = 0;

        for (int i = 0; i < winNumbers.length; i++) {
            Result result = apiEndpoints.play(PlayMode.Mt, 1, 1, (int)(Math.random() * 20)).body().as(Result.class);
            if (startTime == 0) {
                startTime = Instant.now().getEpochSecond();
            }
            winNumbers[i] = result.getRealNumber();
        }

        long finalSeed = 0;
        long finalStart = 0;

        go: while (true) {
            MersenneTwisterRandomizer mersenneTwisterRandomizer = new MersenneTwisterRandomizer(startTime);
            for (int i = 0; i < 800; i++) {
                for (int l = 0; l < winNumbers.length; l++) {
                    if (mersenneTwisterRandomizer.getRandomNumber() == winNumbers[l]) {
                        finalSeed = startTime;
                        finalStart = i;
                        break go;
                    } else {
                        break;
                    }
                }
            }
            startTime--;
        }


        MersenneTwisterRandomizer mersenneTwisterRandomizer = new MersenneTwisterRandomizer(finalSeed);
        for (int i = 0; i <= finalStart + winNumbers.length - 1; i++) {
            System.out.println(mersenneTwisterRandomizer.getRandomNumber());
        }
        for (int i = 0; i < 50; i++) {
            apiEndpoints.play(PlayMode.Mt, 1, 1000, mersenneTwisterRandomizer.getRandomNumber()).body();
        }
    }

    public static void tryToWinMTStrong() {
        ApiEndpoints apiEndpoints = new ApiEndpoints();

        long playerId = 3;
        int lastAmountOfMoney= 0;
        long[] winNumbers = new long[624];

        for (int i = 0; i < winNumbers.length; i++) {
            Result result = apiEndpoints.play(PlayMode.Mt, playerId, 1, (int)(Math.random() * 20)).body().as(Result.class);
            winNumbers[i] = result.getRealNumber();
            lastAmountOfMoney = result.getAccount().getMoney();
        }

        MersenneTwisterRandomizerReverse reverse = new MersenneTwisterRandomizerReverse();
        for (int i = 0; i < winNumbers.length; i++) {
            reverse.put(i , winNumbers[i]);
        }

        MersenneTwisterRandomizer randomizer = new MersenneTwisterRandomizer(reverse.getArray());

        while (true) {
            Result result = apiEndpoints.play(PlayMode.Mt, playerId, lastAmountOfMoney, randomizer.getRandomNumber()).body().as(Result.class);
            lastAmountOfMoney = result.getAccount().getMoney();
            System.out.println(result.getAccount().getMoney());
            if (result.getAccount().getMoney() > 1000000) {
                break;
            }
        }
    }
}
