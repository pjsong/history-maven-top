package pattern.creational;

class Pizza { // product
    private String dough;
    private String sauce;
    private String topping;
    private String str;

    private Pizza() {}

    public static Builder createBuilder() {
            return new Builder();
    }

    public String getDough() {
            return dough;
    }

    public String getSauce() {
            return sauce;
    }

    public String getTopping() {
            return topping;
    }

    @Override
    public String toString() {
            if (str == null)
                    str = "Dough:" + dough + " Topping:" + topping + " Sauce:" + sauce;
            return str;
    }

    /*
     * Builder
     */

    public static class Builder {
            private final Pizza obj = new Pizza();
            private boolean done;

            private Builder() {}

            public Pizza build() {
                    done = true;
                    return obj;
            }

            public Builder setDough(String dough) {
                    check();
                    obj.dough = dough;
                    return this;
            }

            public Builder setSauce(String sauce) {
                    check();
                    obj.sauce = sauce;
                    return this;
            }

            public Builder setTopping(String topping) {
                    check();
                    obj.topping = topping;
                    return this;
            }

            private void check() {
                    if (done)
                            throw new IllegalArgumentException("Do use other builder to create new instance");
            }
    }
}

public class PizzaBuilderExample {
    public static void main(String[] args) {
            Pizza hawaiianPizza = Pizza.createBuilder()
                                       .setDough("cross")
                                       .setTopping("ham+pineapple")
                                       .setSauce("mild")
                                       .build();

            System.out.println("Hawaiian Pizza: " + hawaiianPizza);
    }
}