
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MyJDBS {
    private static String[] symbols = {";", ":", "/", "'", "\"", "@",};
    private static char[] ponctuation = {',','.','!','?'};
    public static void base() {
        Random random = new Random();

        //       boolean addPunctuation = "Ponctuation".equals(selectAdd);
        String temp = MainGui.selectWords;
        String language = "engwords";
        if (MainGui.selectLanguage.equals("English")){
            language = "engwords";
        } else if (MainGui.selectLanguage.equals("Deutsch")) {
            language = "deuwords";
        }
        ArrayList<String> words = new ArrayList<>();
//        String[] wordList = {
//                "hand", "exploit", "white", "exclusive", "nut", "dilute", "hay", "birthday", "feminine", "progress", "bin", "safety", "nature", "kettle", "bullet", "veil", "eliminate", "modernize", "draw", "houseplant", "advocate", "paragraph", "ranch", "catch", "meadow", "thinker", "fortune", "entry", "pity", "us", "string", "penetrate", "liberal", "pension", "ballot", "sting", "movement", "apparatus", "edition", "new", "fun", "reckless", "timetable", "excitement", "layer", "tumour", "creep", "lose", "prejudice", "knock", "agenda", "syndrome", "harmony", "crude", "resolution", "established", "evoke", "tribute", "enemy", "fluctuation", "agree", "code", "straw", "beard", "front", "heat", "carve", "penetrate", "wood", "nomination", "preparation", "spot", "pardon", "first", "lost", "fail", "smell", "rescue", "faithful", "side", "pepper", "corruption", "round", "week", "tongue", "halt", "conference", "grain", "extraterrestrial", "journal", "contribution", "chimney", "danger", "meadow", "year", "drop", "preference", "address", "truck", "company", "error", "engineer", "broccoli", "cold", "arrest", "circle", "like", "friendly",
//                "angle", "courage", "industry", "grand", "appoint", "bind", "language", "snake",
//                "firefighter", "sip", "hole", "innocent", "castle", "curtain", "cup", "morsel",
//                "brain", "treatment", "elegant", "justify", "hook", "greeting", "feature",
//                "index", "sea", "ferry", "soup", "possession", "national", "technique", "worm",
//                "congress", "dash", "review", "sight", "guess", "obscure", "horror", "ready",
//                "facade", "diet", "beg", "protest", "last", "move", "secretary", "meat", "spy", "mixed", "depend", "drawer", "question", "wrap", "ground", "tooth", "hapless", "knot", "store", "classy", "teeth", "piquant", "cultured", "secret", "equal", "team", "strange", "cloth", "tricky", "writer", "lettuce", "call", "perpetual", "boil", "blushing", "bird", "permissible", "tremble", "beneficial", "mighty", "left", "efficacious", "real", "powerful", "hang", "bright", "clumsy", "warm", "strong", "steam", "jail", "love", "succinct", "clear", "hypnotic", "rinse", "materialistic", "lazy", "cobweb", "righteous", "loving", "aromatic", "rabbits", "giddy", "shave", "fretful", "smoggy", "arch", "divergent", "duck", "cable", "faded", "tap", "quarter", "reflective", "idea", "trust", "rush", "spiritual", "decisive", "bow", "undress", "green", "scribble", "ceaseless", "whimsical", "half", "crayon", "ski", "warn", "unsuitable", "insurance", "domineering", "oval", "roasted", "ambitious", "motion", "passenger", "cactus", "file", "cellar", "houses", "mind"
//        };
//        String[] wordList = {
//                "Zuflucht", "tapfer", "Rätsel", "düster", "Unwetter", "zerbrechlich", "Fluch", "mysteriös", "Sturz", "unaufhaltsam",
//                "Glut", "verschleiert", "Niederlage", "verstohlen", "Wunder", "vergänglich", "Schatten", "trüb", "Irrlicht", "schwankend",
//                "Geflüster", "schattenhaft", "Omen", "eindringlich", "Verhängnis", "neblig", "Dämmerung", "unheilvoll", "Schweigen", "zögerlich",
//                "Verlust", "sanftmütig", "Abgrund", "launisch", "Täuschung", "schwindend", "Nacht", "rastlos", "Eisberg", "unheimlich",
//                "Sturm", "beständig", "Schicksal", "fahl", "Ruine", "vergessen", "Dorn", "matt", "Zweifel", "seltsam",
//                "Geheimnis", "entzweit", "Riss", "hoffnungslos", "Klippe", "leise", "Finsternis", "zart", "Kristall", "getrieben",
//                "Welle", "umherirrend", "Stille", "schwerelos", "Erinnerung", "rissig", "Gischt", "verborgen", "Ebbe", "verloren",
//                "Ferne", "trügerisch", "Nebel", "einsam", "Erhebung", "brüchig", "Einbruch", "karg", "Seele", "schwermütig",
//                "Echo", "schattenhaft", "Gefahr", "friedlich", "Flüstern", "entfremdet", "Gipfel", "verstimmt", "Kälte", "zitternd",
//                "Dunkelheit", "suchend", "Schlucht", "starr", "Erhebung", "ungewiss", "Windhauch", "gespenstisch", "Abschied", "gedämpft",
//                "Streiflicht", "wandelbar", "Hauch", "nachdenklich", "Wüste", "heimlich", "Trugbild", "schwebend", "Kummer", "flüchtig",
//                "Einsamkeit", "dumpf", "Sog", "verschollen", "Morgenröte", "zerklüftet", "Vertrauen", "verloren", "Lichtschein", "fahl",
//                "Traum", "verwundbar", "Schweif", "nachhallend", "Vergessen", "irrlichternd", "Luftzug", "verhangen", "Hoffnung", "tastend",
//                "Labyrinth", "gedrängt", "Aufbruch", "verwoben", "Melancholie", "durchsichtig", "Ferne", "verstummt", "Stimme", "verwittert", "Protest", "letzte", "bewegen", "Sekretär", "Fleisch", "Spion", "gemischt", "abhängen", "Schublade", "Frage", "wickeln", "Boden", "Zahn", "unglücklich", "Knoten", "Laden", "elegant", "Zähne", "würzig", "kultiviert", "Geheimnis", "gleich", "Team", "seltsam", "Stoff", "knifflig", "Schriftsteller", "Salat", "rufen", "ewig", "kochen", "errötend", "Vogel", "erlaubt", "zittern", "vorteilhaft", "mächtig", "links", "wirksam", "real", "kraftvoll", "hängen", "hell", "ungeschickt", "warm", "stark", "Dampf", "Gefängnis", "Liebe", "prägnant", "klar", "hypnotisch", "spülen", "materialistisch", "faul", "Spinnennetz", "gerecht", "liebevoll", "aromatisch", "Kaninchen", "schwindelig", "rasieren", "reizbar", "smoggy", "Bogen", "abweichend", "Ente", "Kabel", "verblasst", "tippen", "Viertel", "reflektierend", "Idee", "Vertrauen", "Eile", "spirituell", "entschlossen", "Verbeugung", "ausziehen", "grün", "kritzeln", "unaufhörlich", "launisch", "Hälfte", "Buntstift", "Ski", "warnen", "ungeeignet", "Versicherung", "herrschsüchtig", "Oval", "geröstet", "ehrgeizig", "Bewegung", "Passagier", "Kaktus", "Datei", "Keller", "Häuser", "Geist"
//        };dinser

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:words.db");
             Statement statement = connection.createStatement();
        ){
//            statement.executeUpdate("DROP TABLE IF EXISTS deuwords");
//            statement.setQueryTimeout(30);
//            statement.executeUpdate("CREATE TABLE IF NOT EXISTS deuwords (id INTEGER PRIMARY KEY AUTOINCREMENT, word TEXT)");
//            statement.executeUpdate("DELETE FROM deuwords");
//            for (String word : wordList) {
//                statement.executeUpdate("INSERT INTO deuwords (word) VALUES ('" + word + "')");
//            }
            //statement.executeUpdate("create table engwords (id integer, word string)");
            //statement.executeUpdate("insert into engwords(word) values('word')");
            //statement.executeUpdate("update engwords set word = 'some' where id = 1;");



            ResultSet rs;
            if (temp == "words" || temp == null){
                rs = statement.executeQuery("select * from " + language +" order by random() limit 10");

            } else rs = statement.executeQuery("select * from " + language + " order by random() limit " + temp);



            while (rs.next()){

                String word = rs.getString("word");

                // Проверка, есть ли выбранные элементы для добавки
                if (MainGui.selectAdd != null) {
                    for (String add : MainGui.selectAdd) {
                        if (add.equals("Ponctuation") && random.nextBoolean()) {
                            word += ponctuation[random.nextInt(ponctuation.length)];
                        } else if (add.equals("Symbols") && random.nextBoolean()) {
                            String symbol = symbols[random.nextInt(symbols.length)];
                            if (symbol.equals("\"") || symbol.equals("'")) {
                                word = symbol + word + symbol;
                            } else {
                                word += symbol;
                            }
                        } else if (add.equals("Numbers") && random.nextBoolean()) {
                            String number = String.valueOf(1000 + random.nextInt(9000)); // число от 1000 до 9999
                            word = word + " " + number;
                        }
                    }

                    words.add(word);
                    //System.out.println("in while");
                    // System.out.println(words);
                }
            }

            //System.out.println("not in while");

            //System.out.println(words);
            MainGui.outfield.setText(String.join(" ", words));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
