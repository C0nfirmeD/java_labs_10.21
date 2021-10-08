public class Country {

    private String name;
    private String description;
    private int population;
    private int square;
    private double density;
    private double coastline;
    private double migration;
    private double infantMortality;
    private int GDP;
    private double literacy;

    public Country(String name, String description, int population, int square, double density, double coastline, double migration, double infantMortality, int GDP, double literacy) {
        this.name = name;
        this.description = description;
        this.population = population;
        this.square = square;
        this.density = density;
        this.coastline = coastline;
        this.migration = migration;
        this.infantMortality = infantMortality;
        this.GDP = GDP;
        this.literacy = literacy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getCoastline() {
        return coastline;
    }

    public void setCoastline(double coastline) {
        this.coastline = coastline;
    }

    public double getMigration() {
        return migration;
    }

    public void setMigration(double migration) {
        this.migration = migration;
    }

    public double getInfantMortality() {
        return infantMortality;
    }

    public void setInfantMortality(double infantMortality) {
        this.infantMortality = infantMortality;
    }

    public int getGDP() {
        return GDP;
    }

    public void setGDP(int GDP) {
        this.GDP = GDP;
    }

    public double getLiteracy() {
        return literacy;
    }

    public void setLiteracy(double literacy) {
        this.literacy = literacy;
    }



    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", population=" + population +
                ", square=" + square +
                ", density=" + density +
                ", coastline=" + coastline +
                ", migration=" + migration +
                ", infantMortality=" + infantMortality +
                ", GDP=" + GDP +
                ", literacy=" + literacy +
                '}';

    }
}
