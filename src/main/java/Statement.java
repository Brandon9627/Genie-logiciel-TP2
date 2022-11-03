import java.util.ArrayList;
import java.util.List;

public class Statement {
    private String customer;
    private final List<EachPlayInList> lines = new ArrayList<>();
    private Integer volumeCredit;
    private Integer totalAmount;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<EachPlayInList> getLines() {
        return lines;
    }

    public Integer getVolumeCredit() {
        return volumeCredit;
    }

    public void setVolumeCredit(Integer volumeCredit) {
        this.volumeCredit = volumeCredit;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}
