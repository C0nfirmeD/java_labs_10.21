import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String PURPLE = "\033[0;35m";  // PURPLE

    public static void main(String[] args) {

        try {

            ArrayList<Country> countries = new ArrayList<>();

            Country country = null;
            Scanner sc = new Scanner(new File(Main.class.getClassLoader().getResource("data_countries_world.txt").getFile()));

            String str = sc.nextLine();


            while (str != null) {
                String buf[] = new String[10];

//                if (buf.length != 10)
//                    throw new FormatException("недостаточно данных об объекте");

//                console = new Book(Integer.parseInt(buf[0]), buf[1], buf[2].split("[|]"), Float.parseFloat(buf[3]), Integer.parseInt(buf[4]), Integer.parseInt(buf[5]), buf[6], Integer.parseInt(buf[7]), Integer.parseInt(buf[8]), Integer.parseInt(buf[9]));

                buf[0] = str;
                for (int i = 1; i < 10; i++) {
                    str = sc.nextLine().replace(',', '.');
                    buf[i] = str;
                }
                country = new Country(buf[0], buf[1], Integer.parseInt(buf[2]), Integer.parseInt(buf[3]),
                        Double.parseDouble(buf[4]), Double.parseDouble(buf[5]), Double.parseDouble(buf[6]),
                        Double.parseDouble(buf[7]), Integer.parseInt(buf[2]), Double.parseDouble(buf[7]));

                countries.add(country);

                if (sc.hasNext()) {
                    str = sc.nextLine();
                } else str = null;
            }

            printByGDP(countries);
            printByCoastLine(countries);


        } catch (NumberFormatException e) {
            System.err.println("неверный формат данных");
            e.printStackTrace();
        } catch (FileNotFoundException fnf) {
            System.out.println("файл не найден");
        }
    }


    private static void printByGDP(ArrayList<Country> countries) {
        try {
            Scanner console = new Scanner(System.in);
            FileWriter writer = new FileWriter(new File("out.txt"));

            System.out.println( PURPLE+"Введите диапозон ВВП (минимальное и максимальное значение через дефис) \n" +
                    "страны из заданного диапазона будут отсортированны по региону и площади"+ANSI_RESET);
            String sc = console.nextLine();
            String[] buf = sc.split("-");

            Arrays.stream(buf).forEach(elem -> Long.parseLong(elem));
            if (buf[1] == null || buf.length > 2) throw new Exception();

            long min = Integer.parseInt(buf[0]);
            long max = Integer.parseInt(buf[1]);

            //список с ввп из диапазона

            if (countries.stream().anyMatch(a -> (a.getGDP() >= min) && (a.getGDP() <= max))) {
                List<Country> list = countries.stream().filter(a -> (a.getGDP() >= min) && (a.getGDP() <= max)).sorted(new Comparator<Country>() {
                    @Override
                    public int compare(Country o1, Country o2) {
                        return Integer.compare(o1.getSquare(), o2.getSquare());
                    }
                }).sorted(new Comparator<Country>() {
                    @Override
                    public int compare(Country o1, Country o2) {
                        return String.CASE_INSENSITIVE_ORDER.compare(o1.getDescription(), o2.getDescription());
                    }
                }).collect(Collectors.toList());

                //вывод списка

                list.stream().forEach(a -> {
                    System.out.println(a.getName() + "  (" + a.getDescription() + "),   ВВП = " + a.getGDP() + "    Площадь = " + a.getSquare());
                    try {
                        writer.write(String.valueOf(a));
                        writer.append('\n');
                    } catch (IOException e) {
                        System.err.println("ошибка записи в файл!");
                    }

                });
                writer.flush();

                float count = list.stream().count();
                double averagePopulation = list.stream().mapToInt(Country::getPopulation).sum() / count;
                System.err.println(ANSI_BLUE+"Средняя численность населения : " + averagePopulation + ANSI_RESET);

                Collections.sort(list, (a, b) -> Double.compare(a.getLiteracy(), b.getLiteracy()));
                Collections.reverse(list);
                ArrayList<String> mostLiterateCountries = new ArrayList<>();
                list.stream().limit(3).forEach(a -> mostLiterateCountries.add(a.getName() + " : " + a.getLiteracy()));
                System.err.println(ANSI_BLUE+"Cтраны с наивысшим уровнем грамотности : " + mostLiterateCountries+ANSI_RESET);
            } else {
                System.err.println("таких стран нет");
            }
        } catch (Exception e) {
            System.err.println("некорректный фонмат ввода!");
            printByGDP(countries);
        }

    }

    public static void printByCoastLine(ArrayList<Country> countries) {


        Country maxCostLineCountry = countries.stream().max((a, b) -> Double.compare(a.getCoastline(), b.getCoastline())).get();
        System.err.println(ANSI_BLUE+"Страна с самым длинным побережьем : " + maxCostLineCountry.getName() +" : "+ maxCostLineCountry.getCoastline()+ANSI_RESET);

        ArrayList<Country> list = (ArrayList<Country>) countries.stream().filter(a -> (a.getPopulation() >= 0.9*maxCostLineCountry.getPopulation() && (a.getPopulation() <= 1.1*maxCostLineCountry.getPopulation()))).collect(Collectors.toList());
        System.err.println(PURPLE+ "Выберите параметр сортировки списка стран, отличающихся от этой страны \n" +
                "по численности населения не более чем на 10% (по убыванию'<' или по возрастанию'<')\n" +
                "список будет отсортирован по ВВП на душу населения"+ANSI_RESET);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        list.sort((a,b) -> Integer.compare(a.getGDP(), b.getGDP()));
        if(str.equals(">"))
            Collections.reverse(list);
        else if(!str.equals("<")) {
            System.err.println("неизвестная команда");
            printByCoastLine(countries);
        }
        list.forEach(a -> System.out.println(a.getName() + "  (" + a.getDescription() + "),   ВВП = " + a.getGDP() + ",  Числ. населения = " + a.getPopulation()));

    }
}
