public class EachPlayInList {
    private final String name;
    private final int amount;
    private final int audience;
    public EachPlayInList(String name, Integer amount, Integer audience) {
        this.name = name;
        this.amount = amount;
        this.audience = audience;
    }
    public String getName() {
        return name;
    }
    public int getAmount() {
        return amount;
    }
    public int getAudience() {
        return audience;
    }

}