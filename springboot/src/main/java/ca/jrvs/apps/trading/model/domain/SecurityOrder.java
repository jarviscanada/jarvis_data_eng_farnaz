package ca.jrvs.apps.trading.model.domain;

public class SecurityOrder implements Entity<Integer> {

    private Integer id;
    private Integer accountId;
    private Status status;
    private String ticker;
    private Integer size;
    private Double price;
    private String notes;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status.string;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    @Override
    public String toString() {
        return "SecurityOrder{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", status='" + status + '\'' +
                ", ticker='" + ticker + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", notes='" + notes + '\'' +
                '}';
    }

    private enum Status {
        Filled("Filled"), Rejected("Rejected"), Starting("Starting");
        String string;

        Status(String currentStatus) {
            string = currentStatus;
        }
    }

}
