package table;

public final class TableRow {

    private final String construction;
    private final String country;
    private final String city;
    private final String height;
    private final String built;
    private final String rank;

    public TableRow(String construction, String country, String city, String height, String built, String rank) {
        this.construction = construction;
        this.country = country;
        this.city = city;
        this.height = height;
        this.built = built;
        this.rank = rank;
    }

    public String getConstruction() {
        return construction;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getHeight() {
        return height;
    }

    public String getBuilt() {
        return built;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "TableRow{" +
                "construction='" + construction + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", height='" + height + '\'' +
                ", built='" + built + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
