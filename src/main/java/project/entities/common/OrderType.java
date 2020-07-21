package project.entities.common;

public enum OrderType {

    R("RESTAURANT"),
    D("DELIVERY");

    private String order;

    OrderType(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
