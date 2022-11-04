
public class Calculator {
    public static final String TRAGEDY = "tragedy";
    public static final String COMEDY = "comedy";
    public final int COMEDY_BASE_AMOUNT = 30000;
    public final int TRAGEDY_BASE_AMOUNT = 40000;
    public int theatreAmount(String type, Performance perf){
        switch (type) {
            case TRAGEDY -> {
                return tragedyAmount(perf.audience);
            }
            case COMEDY -> {
                return comedyAmount(perf.audience);
            }
            default -> throw new Error("unknown type: ${play.type}");
        }
    }
    public int volumeCredit(String type, Performance perf) {
        int credit = 0;
            credit += Math.max(perf.audience - 30, 0);
            if (COMEDY.equals(type)) credit += Math.floor((float)(perf.audience / 5));
        return credit;
    }
    public int tragedyAmount(int audience) {
        int amount = TRAGEDY_BASE_AMOUNT;
        if (audience > 30) {
            amount += 1000 * (audience - 30);
        }
        return amount;
    }
    public int comedyAmount(int audience) {
        int amount = COMEDY_BASE_AMOUNT;
        if (audience > 20) {
            amount += 10000 + 500 * (audience - 20);
        }
        amount += 300 * audience;
        return amount;

    }
}
